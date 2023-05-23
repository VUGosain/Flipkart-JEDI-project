package com.dropwizard;

/**
 * Hello world!
 *
 */

import com.flipkart.rest.CRSApplicationRestController;
import com.dropwizard.rest.EmployeeRESTController;
import com.flipkart.bean.Admin;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dropwizard.rest.HelloRestController;
import com.flipkart.restcontroller.*;

public class App extends Application<Configuration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Override
	public void initialize(Bootstrap<Configuration> b) {
	}

	@Override
	public void run(Configuration c, Environment e) throws Exception {
		LOGGER.info("Registering REST resources");
		// e.jersey().register(new EmployeeRESTController(e.getValidator()));
		e.jersey().register(new HelloRestController());
//        e.jersey().register(new EmployeeRESTController(e.getValidator()));
		e.jersey().register(new UserRestAPI());
		e.jersey().register(new AdminRestAPI());
		e.jersey().register(new ProfessorRestAPI());
		e.jersey().register(new CRSApplicationRestController());
		e.jersey().register(new EmployeeRESTController(e.getValidator()));
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}