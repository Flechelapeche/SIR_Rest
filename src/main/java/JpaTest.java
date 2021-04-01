import fr.istic.taa.jaxrs.domain.Department;
import fr.istic.taa.jaxrs.domain.Employee;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaTest {

	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		System.out.println("Liste des employés :");
		test.listEmployees();
		System.out.println("Liste des employés (Criteria Query) :");
		test.listEmployeesByCriteriaQuery();
		System.out.println("Liste des départements :");
		test.listDepartments();

		manager.close();
		System.out.println(".. DONE");
	}

	private void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
		if (numOfEmployees == 0) {
			Department department = new Department("java");
			manager.persist(department);

			manager.persist(new Employee("Martin Matin", department));
			manager.persist(new Employee("Captain Nemo", department));
		}
	}

	private void listEmployees() {
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employees:" + resultList.size());

		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}
	}

	private void listEmployeesByCriteriaQuery() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
		Root<Employee> root = cr.from(Employee.class);
		cr.select(root);
		TypedQuery<Employee> query = manager.createQuery(cr);
		List<Employee> results = query.getResultList();

		for (Employee next : results) {
			System.out.println("next employee: " + next);
		}
	}

	private void listDepartments() {
		Query q = manager.createNamedQuery("Department.findAll");
		List<String> a = q.getResultList();

		for (String next : a) {
			System.out.println("next département: " + next);
		}
	}
}
