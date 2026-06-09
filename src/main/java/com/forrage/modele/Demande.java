package com.forrage.modele;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "demande")
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private int idDemande;

    @Column(name = "reference")
    private String reference;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "demandeur")
    private Client demandeur;

    @Column(name = "lieu")
    private String lieu;

    @ManyToOne
    @JoinColumn(name = "commune")
    private Commune commune;

    @OneToMany(mappedBy = "demande")
    private List<StatutDemande> historiqueDemande;

    @OneToMany(mappedBy = "demande")
    private List<Devis> devis;
    
    /*Constructeur*/
    public Demande(){

    }

    /*Setters*/
    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDemandeur(Client demandeur) {
        this.demandeur = demandeur;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public void setHistoriqueDemande(List<StatutDemande> historiqueDemande) {
        this.historiqueDemande = historiqueDemande;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    /*Getters*/
    public int getIdDemande() {
        return idDemande;
    }

    public String getReference() {
        return reference;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Client getDemandeur() {
        return demandeur;
    }

    public String getLieu() {
        return lieu;
    }

    public Commune getCommune() {
        return commune;
    }

    public List<StatutDemande> getHistoriqueDemande() {
        return historiqueDemande;
    }

    public List<Devis> getDevis() {
        return devis;
    }
}
