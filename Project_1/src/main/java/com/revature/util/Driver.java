package com.revature.util;

import javax.servlet.http.HttpSession;


import com.revature.controller.RequestController;
import com.revature.controller.UserController;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;



public class Driver {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(8080);
		
//		app.config.addStaticFiles("/static", Location.CLASSPATH);
//		Context ctx;
//		ctx.redirect("/Login.html");
		
//		Context ctx;
//		ctx.redirect("/Login.html");
		//app.get()
		
		app.post("/login", ctx -> {
			ctx.req.getSession();
		});
		app.get("/logout",ctx -> {
			HttpSession session = ctx.req.getSession(false);
			if(session!=null)
				session.invalidate();
		});
		app.after(ctx -> {
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
		});
		
		app.config.addStaticFiles("/static", Location.CLASSPATH);
		
		UserController userController = new UserController(app);
		RequestController requestController = new RequestController(app);
		//LogInController logInController = new LogInController(app);
		
	}
}
