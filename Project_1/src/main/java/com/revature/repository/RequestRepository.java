package com.revature.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.Request;
import com.revature.model.User;
import com.revature.util.HibernateSessionFactory;

public class RequestRepository {
	
	public void deny(int requestId) {
		Session session = null;
		Transaction tx = null;
		Request decision = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> rootEntry = cq.from(Request.class);
			CriteriaQuery<Request> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("requestId"), requestId));
		
			TypedQuery<Request> allQuery = session.createQuery(all);
			decision = allQuery.getSingleResult();
			
			decision.setRequestStatus("Denied");
			
			session.saveOrUpdate(decision);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void pending(int requestId) {
		Session session = null;
		Transaction tx = null;
		Request decision = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> rootEntry = cq.from(Request.class);
			CriteriaQuery<Request> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("requestId"), requestId));
		
			TypedQuery<Request> allQuery = session.createQuery(all);
			decision = allQuery.getSingleResult();
			
			decision.setRequestStatus("Pending");
			
			session.saveOrUpdate(decision);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void approve(int requestId) {
		Session session = null;
		Transaction tx = null;
		Request decision = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Request> cq = cb.createQuery(Request.class);
			Root<Request> rootEntry = cq.from(Request.class);
			CriteriaQuery<Request> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("requestId"), requestId));
		
			TypedQuery<Request> allQuery = session.createQuery(all);
			decision = allQuery.getSingleResult();
			
			decision.setRequestStatus("Approved");
			
			session.saveOrUpdate(decision);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public List<Request> findAllReqs(){
		List<Request> requests = null;
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			requests = s.createQuery("FROM Request", Request.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		
		
		return requests;
	}
	
	public void saveRequest(Request request) {
		Session session = null;
		Transaction tx = null;
	
		
		request.setRequestStatus("pending");

		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(request);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public List<Request> requestsByUserName(String userName) {
        Session s = null;
        Transaction tx = null;
        List<Request> requests = null;

        try {
            s = HibernateSessionFactory.getSession();
            tx = s.beginTransaction();

            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Request> cq = cb.createQuery(Request.class);
            Root<Request> root = cq.from(Request.class);
            
            CriteriaQuery<Request> all = cq.select(root).where(cb.equal(root.get("userName"), userName));
            TypedQuery<Request> query = s.createQuery(all);
            requests = query.getResultList();
            tx.commit();
        }catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            s.close();
        }
        return requests;
    }

}
