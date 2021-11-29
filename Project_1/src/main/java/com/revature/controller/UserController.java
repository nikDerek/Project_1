package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.repository.UserRepository;

import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController {
	
	private UserService userService;
	public User user;

	public UserController(Javalin app){
		
		this.userService = new UserService();
		
		app.routes(()-> {
			path("/user", () ->{
				path("/:userName",() -> {
					get(logIn);
				});
				path("/logIn",() -> {
					post(logIn1);
				});
			});
			path("/logIn",() -> {
				post(logIn1);
			});
			path("/all",() -> {
				get(findAllUsers);
			});
			path("/userbyId/:id",() -> {
				get(findById);
			});
			path("/new",() -> {
				post(saveUser);
			});
			path("/byUserName/:userName", () ->{
				get(findByUserName);
			});
		});
	}
	
		private Handler logIn = ctx -> {
		
		//To check for the existence of a session:
		HttpSession session = ctx.req.getSession(false);
		
		user = this.userService.findByUserName(ctx.pathParam("userName"));
		ctx.json(user);
		};
	
	private Handler logIn1 = ctx ->{

		HttpSession session = ctx.req.getSession();
		String userName = ctx.req.getParameter("userName");
		String password = ctx.req.getParameter("password");
		
		System.out.println(userName);
		session.setAttribute("userName", userName);
		
		
		user = this.userService.findByUserName(userName);
		
		System.out.println("3.1");	
			
		
				 System.out.println("6");
				 System.out.println(userName);
				 System.out.println(password);
				 System.out.println(user);

				
	            if(user.getPassword().equals(password) && user.getUserType().equals("manager")) {
	            ctx.redirect("/managerHome.html");
  
	            }else if(user.getPassword().equals(password) && user.getUserType().equals("employee")) {
            	ctx.redirect("/employeeHome.html");
            	
	            }else {
				ctx.redirect("/Login.html");
	            }
	            };



	private Handler findAllUsers = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.userService.findAll());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};

	private Handler saveUser = ctx -> {
		User user = new User(
				
				ctx.req.getParameter("userId"),
				ctx.req.getParameter("userType"),
				ctx.req.getParameter("name"),
				ctx.req.getParameter("userName"),
				ctx.req.getParameter("password"));
		this.userService.save(user);
		ctx.redirect("/home.html");
	};
	
	private Handler findById = ctx -> {
		
		User user = (User) this.userService.findById(ctx.pathParam("id"));
		ctx.json(user);
	};
	
	private Handler findByUserName = ctx -> {
	
		User user = (User) this.userService.findByUserName(ctx.pathParam("userName"));
		ctx.json(user);
	};
}
