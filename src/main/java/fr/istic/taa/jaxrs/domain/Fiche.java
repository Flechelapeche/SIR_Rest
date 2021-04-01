package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fiche {

    long id;
    String nom;
    int dureeEnMinute;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDureeEnMinute() {
        return dureeEnMinute;
    }

    public void setDureeEnMinute(int dureeEnMinute) {
        this.dureeEnMinute = dureeEnMinute;
    }
}
