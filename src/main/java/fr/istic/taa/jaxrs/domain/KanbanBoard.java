package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class KanbanBoard {

    long Id;
    String nom;
    List<Fiche> fiches;
    Employee owner;

    @Id
    @GeneratedValue
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @OneToMany
    @JoinColumn(name = "kanbanId")
    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiches) {
        this.fiches = fiches;
    }

    @ManyToOne
    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }
}
