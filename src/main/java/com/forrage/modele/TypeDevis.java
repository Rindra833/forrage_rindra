package com.forrage.modele;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_devis")
public class TypeDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int idType;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "sigle")
    private String sigle;

    @OneToMany(mappedBy = "typeDevis")
    private List<Devis> devis;

    /*Constructeur*/
    public TypeDevis(){
    
    }

    /*Setters*/
    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDevis(List<Devis> devis) {
        this.devis = devis;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /*Getters*/
    public int getIdType() {
        return idType;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getSigle() {
        return sigle;
    }

    public List<Devis> getDevis() {
        return devis;
    }
}
