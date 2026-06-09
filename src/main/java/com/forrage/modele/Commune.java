package com.forrage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "commune")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commune")
    private int idCommune;

    @Column(name = "libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "id_district")
    private District district;

    /*Constructeur*/
    public Commune(){

    }

    /*Setters*/
    public void setIdCommune(int idCommune) {
        this.idCommune = idCommune;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    /*Getters*/
    public int getIdCommune() {
        return idCommune;
    }

    public String getLibelle() {
        return libelle;
    }

    public District getDistrict() {
        return district;
    }
}
