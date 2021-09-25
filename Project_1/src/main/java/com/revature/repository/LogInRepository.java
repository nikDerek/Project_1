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
	
//	public void login(Context ctx) {
//	
//	User attemptUser = ctx.bodyAsClass(User.class);
//	User approvedUser = logInService.logIn(attemptUser.getUserName());
//	
//	if(approvedUser != null) {
//		if(attemptUser.getPassword().equals(approvedUser.getPassword())) {
//			ctx.json(approvedUser);
//			
//			return;
//			
//		}
//	  }
//	//if not successful sends this
//	ctx.status(401);
//   }
	
	
	
	//@SuppressWarnings("null")
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
        
            //while(user != null) {
            if((user.getPassword().equals(password)) && user.getUserType() == "manager") {
            	ctx.req.getSession();
            	//UserController userController = new UserController();
            	ctx.redirect("/managerHome.html");
            }else if((user.getPassword().equals(password)) && user.getUserType() == "employee") {
            	ctx.req.getSession();
            	ctx.redirect("/employeeHome.html");
            }
          //}
        	 //tx.commit();
        }catch(HibernateException e) {
            //tx.rollback();
            e.printStackTrace();
        }
        return user;
    }
  }
	

