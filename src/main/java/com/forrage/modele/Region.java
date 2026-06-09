package com.forrage.modele;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private int idRegion;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "region")
    private List<District> districts;

    /*Constructeur*/
    public Region(){

    }

    /*Setters*/
    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    /*Getters*/
    public int getIdRegion() {
        return idRegion;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<District> getDistricts() {
        return districts;
    }
}
