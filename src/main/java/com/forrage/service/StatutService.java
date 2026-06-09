package com.forrage.service;

import com.forrage.repository.*;
import com.forrage.modele.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatutService {
    @Autowired
    private StatutRepository statutRepository;

    public void sauvegarder(Statut statut){
        statutRepository.save(statut);
    }

    public Statut getStatutById(int idStatut){
        return statutRepository.findById(idStatut).orElse(null);
    }

    public List<Statut> getAllStatuts(){
        return statutRepository.findAll();
    }
}
