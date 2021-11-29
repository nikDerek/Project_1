package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.User;
import com.revature.util.HibernateSessionFactory;

import io.javalin.http.Context;

public class UserRepository {
	
	public User logIn(String userName) {
		System.out.println("5");
        Session s = null;
        Transaction tx = null;
        User user = null;
        Context ctx = null;

        try {
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();

            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("userName"), userName));
            Query<User> query = s.createQuery(cq);
            user = query.getSingleResult();
        	tx.commit();
        	
        }catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return user;
    }
  
	
	public List<User> findAll(){
		List<User> users = null;
		
				Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
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
            //tx.commit();
        }catch(HibernateException e) {
           // tx.rollback();
            e.printStackTrace();
        }finally {
            s.close();
        }
        return user;
    }
	
	public User findByUserName(String userName) {
        Session s = null;
        Transaction tx = null;
        User user = null;

        try {
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();

            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            CriteriaQuery<User> all = cq.select(root).where(cb.equal(root.get("userName"), userName));
           
            TypedQuery<User> query = s.createQuery(all);
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
