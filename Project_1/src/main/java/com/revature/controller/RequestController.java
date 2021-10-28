package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.model.Request;
import com.revature.model.User;
import com.revature.service.RequestService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class RequestController {

	private RequestService requestService;
	
	public RequestController(Javalin app){
		
		this.requestService = new RequestService();
		
		app.routes(()-> {
			path("/request", () -> {
				
				path("/all",() -> {
				get(findAllRequests);
				});
				
				path("/username",() -> {
				get(requestsByUserName);
				});
				
				path("/new",() -> {
				post(saveRequest);
				});
				
				path("/approve", () -> {
					post(approve);
				});
				path("/deny", () -> {
					post(deny);
				});
			});
		});
	}
	
	private Handler approve = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		
		String requestId = ctx.req.getParameter("requestId");
		//System.out.println(requestId);
		this.requestService.approve(Integer.parseInt(requestId));
		ctx.redirect("/managerHome.html");
		//System.out.println(requestId);
	};
	
	private Handler deny = ctx -> {
		
		HttpSession session = ctx.req.getSession(false);
		this.requestService.deny(Integer.parseInt(ctx.req.getParameter("requestId")));
		ctx.redirect("/managerHome.html");
	};
	
	private Handler requestsByUserName = ctx -> {
		HttpSession session = ctx.req.getSession(false);	
	
		List<Request> requests = this.requestService.requestsByUserName((String) session.getAttribute("userName"));
		System.out.println(requests.toString());
		ctx.json(requests);
	};
	
	private Handler findAllRequests = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.requestService.findAllReqs());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};
	
	private Handler saveRequest = ctx -> {
		HttpSession s = ctx.req.getSession(false);
		String userName = (String) s.getAttribute("userName");
		Request request = new Request(1,
				//Integer.parseInt(ctx.req.getParameter("requestId")),
				userName,
				//ctx.req.getParameter("userName"),
				//s.getAttribute("userName"),
				ctx.req.getParameter("request4"),
				Double.parseDouble(ctx.req.getParameter("requestAmount")),
				ctx.req.getParameter("requestStatus"));
				//new User(s.getAttribute("userName"), "NA")),
			this.requestService.save(request);
		ctx.redirect("/employeeHome.html");
	};
}
