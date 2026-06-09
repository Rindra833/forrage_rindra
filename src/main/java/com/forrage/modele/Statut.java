package com.forrage.modele;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut")
    private int idStatut;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "sigle")
    private String sigle;

    @OneToMany(mappedBy = "statut")
    private List<StatutDemande> historiqueStatut;


    /*Constructeur*/
    public Statut(){
    
    }

    /*Setters*/
    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setHistoriqueStatut(List<StatutDemande> historiqueStatut) {
        this.historiqueStatut = historiqueStatut;
    }

    /*Getters*/
    public int getIdStatut() {
        return idStatut;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getSigle() {
        return sigle;
    }

    public List<StatutDemande> getHistoriqueStatut() {
        return historiqueStatut;
    }

}
