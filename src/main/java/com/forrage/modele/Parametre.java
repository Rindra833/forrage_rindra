package com.forrage.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "parametre")
public class Parametre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametre")
    private int idParametre;

    @ManyToOne
    @JoinColumn(name = "id_statut1")
    private Statut statut1;

    @ManyToOne
    @JoinColumn(name = "id_statut2")
    private Statut statut2;

    @Column(name = "durer")
    private int durer;

    @Column(name = "alerte")
    private String alerte;

    /*Constructeur*/
    public Parametre(){
    
    }

    /*Setters*/
    public void setIdParametre(int idParametre) {
        this.idParametre = idParametre;
    }

    public void setStatut1(Statut statut1) {
        this.statut1 = statut1;
    }

    public void setStatut2(Statut statut2) {
        this.statut2 = statut2;
    }

    public void setDurer(int durer) {
        this.durer = durer;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

    /*Getters*/
    public int getIdParametre() {
        return idParametre;
    }

    public Statut getStatut1() {
        return statut1;
    }

    public Statut getStatut2() {
        return statut2;
    }

    public int getDurer() {
        return durer;
    }

    public String getAlerte() {
        return alerte;
    }
}
