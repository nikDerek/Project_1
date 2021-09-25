package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController {
	
	private UserService userService;

	public UserController(Javalin app){
		
		this.userService = new UserService();
		
		app.routes(()-> {
			path("/all",() -> {
				get(findAllUsers);
			});
			path("/user/:id",() -> {
				get(findById);
			});
			path("/new",() -> {
				post(saveUser);
			});
		});
	}

	private Handler findAllUsers = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.userService.findAll());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};

	private Handler saveUser = ctx -> {
		User user = new User(
				//Integer.parseInt(ctx.req.getParameter("userId")),
				ctx.req.getParameter("userId"),
				ctx.req.getParameter("userType"),
				ctx.req.getParameter("name"),
				ctx.req.getParameter("userName"),
				ctx.req.getParameter("password"));
		this.userService.save(user);
		ctx.redirect("/home.html");
	};
	
	private Handler findById = ctx -> {
		//String id = ctx.pathParam("id");
		//User user = new User(this.userService.findById(ctx.pathParam("id")));
		//User user = new User();
		//user = user.this.userService.findById(id);
		User user = (User) this.userService.findById(ctx.pathParam("id"));
		ctx.json(user);
	};
}
