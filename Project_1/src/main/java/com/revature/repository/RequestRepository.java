package com.revature.repository;

import java.util.List;

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
        User user = null;
        List<Request> requests = null;

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
        }finally {
            s.close();
        }
        return requests;
    }

}
