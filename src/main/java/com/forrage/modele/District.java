package com.forrage.modele;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_district")
    private int idDistrict;

    @Column(name = "libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;

    @OneToMany(mappedBy = "district")
    private List<Commune> communes;

    /*Constructeur*/
    public District(){
    
    }

    /*Setters*/
    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    /*Getters*/
    public int getIdDistrict() {
        return idDistrict;
    }

    public String getLibelle() {
        return libelle;
    }

    public Region getRegion() {
        return region;
    }

    public List<Commune> getCommunes() {
        return communes;
    }
}
