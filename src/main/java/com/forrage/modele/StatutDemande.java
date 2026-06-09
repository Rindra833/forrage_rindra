package com.forrage.modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "statut_demande")
public class StatutDemande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut_demande")
    private int idStatutDemande;

    @ManyToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "observation")
    private String observation;

    @Column(name = "durer_travaille")
    private int durerTravaille;

    @ManyToOne
    @JoinColumn(name = "id_statut")
    private Statut statut;

    /*Constructeur*/
    public StatutDemande(){
    }

    /*Setters*/
    public void setIdStatutDemande(int idStatutDemande) {
        this.idStatutDemande = idStatutDemande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setDurerTravaille(int durerTravaille) {
        this.durerTravaille = durerTravaille;
    }


    /*Getters*/
    public int getIdStatutDemande() {
        return idStatutDemande;
    }

    public Demande getDemande() {
        return demande;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getObservation() {
        return observation;
    }

    public Statut getStatut() {
        return statut;
    }

    public int getDurerTravaille() {
        return durerTravaille;
    }

}
