package com.forrage.service;

import com.forrage.repository.*;

import jakarta.transaction.Transactional;

import com.forrage.modele.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class StatutDemandeService {
    @Autowired
    private StatutDemandeRepository statutDemandeRepository;

    public void save(StatutDemande statutDemande){
        if (!statutDemande.getStatut().getSigle().equalsIgnoreCase("DC")) {
            calculerDureeTravaille(statutDemande);
        }
        
        statutDemandeRepository.save(statutDemande);
    }

    public List<StatutDemande> getAll(){
        return statutDemandeRepository.findAll();
    }

    public List<StatutDemande> getStatutDemandeByDemandeReference(String reference) {
        return statutDemandeRepository.findStatutDemandeByDemandeReference(reference);
    }

    public StatutDemande getStatutDemandeByDemandeReferenceAndStatutId(String reference, int idStatut) {
        return statutDemandeRepository.findStatutDemandeByDemandeReferenceAndStatutIdStatut(reference, idStatut);
    }

    @Transactional
    public void update(StatutDemande statutDemande){
        if (!statutDemande.getStatut().getSigle().equalsIgnoreCase("DC")) {
            calculerDureeTravaille(statutDemande);
        }

        statutDemandeRepository.save(statutDemande);

        calculerDureeTravailleApres(statutDemande);
        
        
    }

    public void calculerDureeTravaille(StatutDemande statutDemande){
        StatutDemande statutDemandePrecedent = statutDemandeRepository.findFirstByDemandeReferenceAndDateLessThanOrderByDateDesc(statutDemande.getDemande().getReference(),statutDemande.getDate());

        int minutes = calculerDureeEnMinute(statutDemandePrecedent.getDate(), statutDemande.getDate());
        
        statutDemande.setDurerTravaille(minutes);
    }

    public void calculerDureeTravailleApres(StatutDemande statutDemande){
        StatutDemande voisinDessous = statutDemandeRepository.findFirstByDemandeReferenceAndDateGreaterThanOrderByDateAsc(statutDemande.getDemande().getReference(),statutDemande.getDate());

        if(voisinDessous!=null){
            int minutes = calculerDureeEnMinute(statutDemande.getDate(),voisinDessous.getDate());
        
            voisinDessous.setDurerTravaille(minutes);

            statutDemandeRepository.save(statutDemande);
        }
    }

    public int calculerDureeEnMinute(LocalDateTime dateDebut, LocalDateTime dateFin){
        long totalMinutes = 0;
        
        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(16, 0);
      
        LocalDate jour = dateDebut.toLocalDate();
        LocalDate jourFin = dateFin.toLocalDate();

        while(!jour.isAfter(jourFin)){
            DayOfWeek day = jour.getDayOfWeek();

            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                jour = jour.plusDays(1);
                continue;
            }

            LocalDateTime debutJour = LocalDateTime.of(jour,heureDebut);
            LocalDateTime finJour = LocalDateTime.of(jour, heureFin);

            if(dateDebut.isAfter(debutJour)){
                debutJour = dateDebut;
            }

            if (finJour.isAfter(dateFin)) {
                finJour = dateFin;
            }

            if(!debutJour.isAfter(finJour)){
                Duration durer=Duration.between(debutJour, finJour);

                totalMinutes+=durer.toMinutes();
            }
                
            jour=jour.plusDays(1);
        }
            
        return (int) totalMinutes;
    }
}
