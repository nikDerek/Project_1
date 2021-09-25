package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.Request;
import com.revature.service.RequestService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class RequestController {

	private RequestService requestService;
	
	public RequestController(Javalin app){
		
		this.requestService = new RequestService();
		
		app.routes(()-> {
			path("/request/all",() -> {
				get(findAllRequests);
			});
			//path("/request/:id",() -> {
				//get(findById);
			//});
			path("/request/new",() -> {
				post(saveRequest);
			});
		});
	}
	
	private Handler findAllRequests = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.requestService.findAllReqs());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};
	
	private Handler saveRequest = ctx -> {
		Request request = new Request(
				Integer.parseInt(ctx.req.getParameter("requestId")),
				ctx.req.getParameter("userName"),
				ctx.req.getParameter("request4"),
				Double.parseDouble(ctx.req.getParameter("requestAmount")),
				Integer.parseInt(ctx.req.getParameter("requestStatus")));
			this.requestService.save(request);
		//ctx.redirect("/home.html");
	};
}
