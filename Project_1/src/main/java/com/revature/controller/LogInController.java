package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.service.LogInService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogInController {
	
	private LogInService logInService;
	
    public LogInController(Javalin app){
		
		this.logInService = new LogInService();
		
		app.routes(()-> {
			path("/login/userName/:userName/password/:password",() -> {
				post(logIn);
			});
			
		});
	}
	
	private Handler logIn = ctx ->{
		HttpSession session = ctx.req.getSession(false);
		if(session != null)
		
			this.logInService.logIn(ctx.pathParam("userName"),
				ctx.pathParam("password"));
	};

}
