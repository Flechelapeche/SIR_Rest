package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.DaoEmployee;
import fr.istic.taa.jaxrs.domain.Department;
import fr.istic.taa.jaxrs.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @GET
    @Path("/getEmployee/{name}")
    public Employee getUser(@PathParam("name") String name) {
        DaoEmployee daoEmployee = new DaoEmployee();
        return daoEmployee.FindEmployee(name);
    }

    @POST
    @Path("/add")
    public Response addUser(@FormParam("name") String name, @FormParam("dept") String dept) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            List<String> depts = manager.createQuery("Select d.name From Department d").getResultList();

            if (depts.contains(dept)) {
                Department dept2 = manager.createQuery("Select d2 From Department d2 where d2.name = :deptName", Department.class)
                        .setParameter("deptName", dept).getSingleResult();
                manager.persist(dept2);
                manager.persist(new Employee(name,dept2));
            } else {
                Department department = new Department(dept);
                manager.persist(department);
                manager.persist(new Employee(name,department));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        manager.close();

        return Response.status(200)
                .entity("<HTML>\n<BODY>\n" +
                        "<H1>C'est fait</H1>\n" +
                        "</BODY></HTML>")
                .build();
    }
}