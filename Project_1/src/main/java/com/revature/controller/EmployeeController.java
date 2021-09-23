package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import javax.servlet.http.HttpSession;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;


public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(Javalin app){
		this.employeeService = new EmployeeService();
		app.routes(()-> {
			path("/all",() -> {
				get(findAllEmployees);
			});
			path("/new",() -> {
				post(saveEmployee);
			});
		});
	}

	private Handler findAllEmployees = ctx -> {
		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.employeeService.findAll());
		else
			ctx.res.getWriter().write("you do not have a session.");
	};

	private Handler saveEmployee = ctx -> {
		Employee employee = new Employee(1,
				ctx.req.getParameter("name"),
				ctx.req.getParameter("employeeUserName"),
				ctx.req.getParameter("employeePassword"));
		this.employeeService.save(employee);
		ctx.redirect("/home.html");
	};
}
