package com.forrage.modele;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "devis")
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devis")
    private int idDevis;

    @Column(name = "reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypeDevis typeDevis;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "observation")
    private String observation;

    @OneToMany(mappedBy = "devis")
    private List<Detail> details;

    @Transient
    private List<Detail> detailList;

    /*Constructeur*/
    public Devis(){

    }

    /*Setters*/
    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    /*Getters*/
    public int getIdDevis() {
        return idDevis;
    }

    public String getReference() {
        return reference;
    }

    public Demande getDemande() {
        return demande;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public String getObservation() {
        return observation;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }
}
