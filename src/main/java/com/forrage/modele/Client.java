package com.forrage.modele;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int idClient;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "demandeur")
    private List<Demande> demandes;

    /*Constructeur*/
    public Client(){
    
    }

    /*Setters*/
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }

    /*Getters*/
    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getContact() {
        return contact;
    }

    public List<Demande> getDemandes() {
        return demandes;
    }

}
