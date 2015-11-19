package com.generalproject.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.generalproject.entity.Customer;
import com.generalproject.model.LoginForm;
import com.generalproject.model.RestAPIResponse;
import com.generalproject.model.Token;
import com.generalproject.repo.CustomerRepository;
import com.generalproject.util.AuthUtils;
import com.nimbusds.jose.JOSEException;

@Controller
public class HelloController extends BaseController{
	
	@Autowired
	CustomerRepository customerRepository;

    @RequestMapping("/greeting")
    public @ResponseBody RestAPIResponse greeting() {
        return RestAPIResponse.ok("hello");
    }
    
    @RequestMapping( name = "/login", method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("loginForm", new LoginForm());
		return "login";
	}
    
    @RequestMapping( name = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute LoginForm loginForm, Map<String, Object> model) {
		System.out.println(loginForm.getUsername() + " " + loginForm.getPassword());
		return viewLogin(model);
	}
    
    @RequestMapping("/getToken")
    public @ResponseBody RestAPIResponse greeting(@RequestParam("userid") Long userId) throws JOSEException {
    	final Token token = AuthUtils.createToken(request.getRemoteHost(), userId);
    	
    	return RestAPIResponse.ok(token);
    }
    
    @RequestMapping("/secure/greeting")
    public @ResponseBody RestAPIResponse secureGreetings(@RequestParam(value="name") String name) {
    	return RestAPIResponse.ok("hello");
    }
    
    @RequestMapping("/save")
    public @ResponseBody RestAPIResponse greeting(@RequestParam(value="name") String firstName, @RequestParam(value="lastName") String lastName) {
    	Customer customer = new Customer(firstName, lastName);
    	
    	customer = customerRepository.save(customer);
        return RestAPIResponse.ok(customer);
    }
    
    @RequestMapping(value="/getAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RestAPIResponse getAll() throws JsonProcessingException {
    	Iterable<Customer> customers = customerRepository.findAll();
    	
    	return RestAPIResponse.ok(customers);
    }
    
}