package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.User;
import com.revature.util.HibernateSessionFactory;

public class UserRepository {
	public List<User> findAll(){
		List<User> users = null;
		
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
			users = s.createQuery("FROM User", User.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		
		
		return users;
	}
	
	public void save(User user) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public User findUserById(String userId) {
        Session s = null;
        Transaction tx = null;
        User user = null;

        try {
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();

            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("userId"), userId));
            Query<User> query = s.createQuery(cq);
            user = query.getSingleResult();
            tx.commit();
        }catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            s.close();
        }
        return user;
    }
}
