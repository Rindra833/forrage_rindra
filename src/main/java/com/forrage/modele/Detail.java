package com.forrage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private int idDetail;

    @ManyToOne
    @JoinColumn(name = "id_devis")
    private Devis devis;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "unite")
    private String unite;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;

    /*Constructeur*/
    public Detail(){

    }

    /*Setters*/
    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    /*Getters*/
    public int getIdDetail() {
        return idDetail;
    }

    public Devis getDevis() {
        return devis;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getUnite() {
        return unite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public double getMontant() {
        return quantite * prixUnitaire;
    }
}
