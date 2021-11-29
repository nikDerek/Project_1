package com.revature.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.util.HibernateSessionFactory;


public class EmployeeRepository {
	
	public List<Employee> findAll(){
		List<Employee> employees = null;
		
		// We need a session to do work on our DB
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			employees = s.createQuery("FROM Employee", Employee.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		
		
		return employees;
	}
	
	public void save(Employee employee) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(employee);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public Employee getByUsername(String username){
		Session session = null;
		Transaction tx = null;
		Employee employee = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> rootEntry = cq.from(Employee.class);
			CriteriaQuery<Employee> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("userName"), username));
		
			TypedQuery<Employee> allQuery = session.createQuery(all);
			employee = allQuery.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return employee;
	}
}
