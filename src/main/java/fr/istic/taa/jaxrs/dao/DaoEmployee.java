package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import static java.util.Objects.requireNonNull;
import fr.istic.taa.jaxrs.domain.Employee;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DaoEmployee {
    EntityManager manager ;
    private final static String FindEmployee = "SELECT e FROM Employee e where e.name =:name";

    public DaoEmployee() {
        this.manager = EntityManagerHelper.getEntityManager();
    }

    public Employee FindEmployee(String name) {
        requireNonNull(name, "Le nom ne doit pas être vide");
        EntityManagerHelper.beginTransaction();
        Employee employee = new Employee();
        employee = (Employee) manager.createQuery(FindEmployee).setParameter("name", name).getSingleResult();
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        return employee;
    }

    public List<Employee> FindAllEmployee() {
        EntityManagerHelper.beginTransaction();
        List<Employee> employees = new ArrayList<Employee>();
        employees = manager.createQuery(FindEmployee).getResultList();
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        return employees;
    }
/*
    public Utilisateur saveUtilisat(Utilisateur util) {
        requireNonNull(util, "L'Email ne doit jamais �tre vide");
        EntityManagerHelper.beginTransaction();
        try {
            manager.persist(util);
            EntityManagerHelper.commit();
        }catch (Exception e) {}
        manager.close();
        return util;
    }


    public Utilisateur deleteUtilisateur(String email) {
        requireNonNull(email, "L'Email ne doit jamais �tre vide");
        EntityManagerHelper.beginTransaction();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur =  (Utilisateur) manager.createQuery(FindUtilisateurByEmail).setParameter("email", email).getSingleResult();
        EntityManagerHelper.commit();
        manager.remove(utilisateur);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        return utilisateur;
    }

    //get user
    public List<Utilisateur> getListUtilisateur() {

        EntityManagerHelper.beginTransaction();
        List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
        listUtilisateur = manager.createQuery(FindAllLUtilisateur).getResultList();
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        return listUtilisateur;
    }



    public PreferenceAlimentaire addPrefAliment(PreferenceAlimentaire pre, String email) {
        EntityManagerHelper.beginTransaction();
        requireNonNull(pre ,"votre pr�f�rence ne doit pas �tre vide");
        requireNonNull(email ,"L'Email ne doit jamais �tre vide");
        Utilisateur util = manager.find(Utilisateur.class, email);
        util.addPreference(pre);
        manager.persist(util);
        try {
            manager.persist(pre);
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
        }catch (Exception e) {
        }
        System.out.println("F�licitation vous avez bien ajout� votre pr�f�rence alimentaire!");
        return pre;

    }

    public Alergie addAlergie(Alergie al, String email) {
        EntityManagerHelper.beginTransaction();
        requireNonNull(al ,"Votre alergie ne doit pas �tre null");
        requireNonNull(email ,"L'Email ne doit jamais �tre vide");
        Utilisateur util = manager.find(Utilisateur.class, email);
        util.addAlergie(al);
        manager.persist(util);
        try {
            manager.persist(al);
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
        }catch (Exception e) {

        }
        System.out.println("F�licitation vous avez bien ajout� votre alergie!");
        return al;

    }





    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }


*/
    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();

        if(manager != null) {
            String d = "SELECT e FROM Employee as e";

            List<Employee> it = 	manager.createQuery(d).getResultList();

            for( Employee u : it) {

                System.out.println(u);
            }
        }
    }

}

