package com.revature.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Employee;

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
			/*
			 * Hibernate has its own query language called "HQL" - Hibernate Query Language.
			 * HQL allows us to emphasize our Java models rather than the entities in our DB when we
			 * are making queries. It provides a more object-oriented approach to data persistence.
			 * 
			 * I should point out that you have the option to use native SQL (i.e. plain old SQL). You can simply
			 * do so by calling "createNativeQuery".
			 */
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
}
