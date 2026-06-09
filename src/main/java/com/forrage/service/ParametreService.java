package com.forrage.service;

import com.forrage.repository.*;
import com.forrage.modele.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametreService {
    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private StatutDemandeService statutDemandeService;

    @Autowired
    private StatutService statutService;


    public List<Parametre> getAllDemandeAlerte(Demande demande){
        List<Parametre> resultat = new ArrayList<>();
        
        List<StatutDemande> statutDemandes = statutDemandeService.getStatutDemandeByDemandeReference(demande.getReference());
        
        List<ParametreProjection> param = parametreRepository.getParametres();
        
        for (ParametreProjection parametre : param) {
            Parametre result = new Parametre();
            
            Statut statut1 = new Statut();
            statut1.setIdStatut(parametre.getIdStatut1());
            Statut statut1Complet = statutService.getStatutById(parametre.getIdStatut1());
            if (statut1Complet != null) {
                statut1.setLibelle(statut1Complet.getLibelle());
                statut1.setSigle(statut1Complet.getSigle());
            }
            result.setStatut1(statut1);

            Statut statut2 = new Statut();
            statut2.setIdStatut(parametre.getIdStatut2());
            Statut statut2Complet = statutService.getStatutById(parametre.getIdStatut2());
            if (statut2Complet != null) {
                statut2.setLibelle(statut2Complet.getLibelle());
                statut2.setSigle(statut2Complet.getSigle());
            }
            result.setStatut2(statut2);
            
            List<StatutDemande> statutDemandeFiltre = statutDemandes.stream().filter(s -> s.getStatut().getIdStatut() >= parametre.getIdStatut1() && s.getStatut().getIdStatut() <= parametre.getIdStatut2()).toList();
            
            int idStatut2=statutDemandeFiltre.get(statutDemandeFiltre.size()-1).getStatut().getIdStatut();
            
            if (idStatut2!=parametre.getIdStatut2()) {
                continue;
            }

            int durerTotal = 0;
            for (StatutDemande statutDemande : statutDemandeFiltre) {
                System.out.println(statutDemande.getStatut().getIdStatut());
                durerTotal += statutDemande.getDurerTravaille();
            }
            
            result.setDurer(durerTotal);
            
            List<Parametre> parametres = parametreRepository.findByStatut1IdStatutAndStatut2IdStatutOrderByDurerAsc(parametre.getIdStatut1(), parametre.getIdStatut2());

            for (Parametre p : parametres) {
                if (durerTotal >= p.getDurer()) {
                    result.setAlerte(p.getAlerte());
                } else {
                    break;
                }
            }
            
            if (result.getAlerte()!=null && !result.getAlerte().isEmpty()) {
                resultat.add(result);
            }
        }

        return resultat;
    }
}
