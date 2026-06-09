package com.forrage.service;

import com.forrage.repository.*;
import com.forrage.modele.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DevisService {
    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    @Autowired
    private StatutDemandeService statutDemandeService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private Util util;

    @Autowired
    private DemandeService demandeService;

    @Transactional
    public void save(Devis devis){
        Demande demande = demandeService.getByReference(devis.getDemande().getReference());

        devis.setDemande(demande);
        
        TypeDevis typeDevis = typeDevisRepository.findById(devis.getTypeDevis().getIdType()).orElse(null);
        devis.setTypeDevis(typeDevis);

        devisRepository.save(devis);

        
        StatutDemande statutDemande = new StatutDemande();
        statutDemande.setDemande(devis.getDemande());

        int idStatut = util.getIdStatutBySigle(devis.getTypeDevis().getSigle() + "C");

        Statut statut = statutService.getStatutById(idStatut);

        statutDemande.setStatut(statut);
        statutDemande.setDate(devis.getDate());

        statutDemandeService.save(statutDemande);

        List<Detail> details = devis.getDetailList();
        for (Detail detail : details) {
            detail.setDevis(devis);
            detailService.save(detail);
        }
    }

    public List<Devis> getAll(){
        return devisRepository.findAll();
    }
}
