package com.revature.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.controller.UserController;
import com.revature.model.User;
import com.revature.util.HibernateSessionFactory;

import io.javalin.http.Context;

public class LogInRepository {	
	
	public Object logIn(String userName, String password) {
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
        
         
            if((user.getPassword().equals(password)) && user.getUserType() == "manager") {
            	ctx.req.getSession();
            	ctx.redirect("/managerHome.html");
            }else if((user.getPassword().equals(password)) && user.getUserType() == "employee") {
            	ctx.req.getSession();
            	ctx.redirect("/employeeHome.html");
            }
        }catch(HibernateException e) {
            //tx.rollback();
            e.printStackTrace();
        }
        return user;
    }
  }
	

