package com.forrage.service;

import com.forrage.repository.*;
import com.forrage.modele.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private StatutDemandeService statutDemandeService;

    @Autowired
    private StatutService statutService;

    @Transactional
    public void save(Demande demande){
        demandeRepository.save(demande);

        StatutDemande statutDemande = new StatutDemande();
        statutDemande.setDemande(demande);

        Statut statut=statutService.getStatutById(1);

        statutDemande.setStatut(statut);
        statutDemande.setDate(demande.getDate());

        statutDemandeService.save(statutDemande);
    }

    public List<Demande> getAll() {
        return demandeRepository.findAll();
    }

    public Demande getByReference(String reference) {
        return demandeRepository.findByReferenceContainingIgnoreCase(reference);
    }

    public Demande getById(int idDemande) {
        return demandeRepository.findById(idDemande).orElse(null);
    }
}
