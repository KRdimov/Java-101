package week11;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeManager {

	private Session session;

	public EmployeeManager(Session session) {
		this.session = session;
	}

	public void listEmployees() {
		List employees = session.createQuery("FROM Employee").list();
		for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
	}

	public Integer addEmployee(String fname, String lname, int salary) {
		Integer employeeID = null;
		Employee e = new Employee(fname, lname, salary);
		employeeID = (Integer) session.save(e);
		return employeeID;
	}

	public void updateEmployee(Integer empID, int salary) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Employee employee = (Employee) session.get(Employee.class, empID);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	public void deleteEmployee(Integer empID) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, empID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
