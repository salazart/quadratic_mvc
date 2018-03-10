package com.sz.quadratic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

@Controller
public class QuadraticController {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private IQuadraticService quadraticService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/result")
    public String result(
    		@RequestParam(value="a", required=true) String aValue,
    		@RequestParam(value="b", required=true) String bValue,
    		@RequestParam(value="c", required=true) String cValue, Model model) {
    	
		Quadratic quadratic = new Quadratic();
    	if(isDecimal(aValue) && isDecimal(bValue) && isDecimal(cValue)) {
        	quadratic.setA(Double.valueOf(aValue));
        	quadratic.setB(Double.valueOf(bValue));
        	quadratic.setC(Double.valueOf(cValue));
    	} else {
    		model.addAttribute("result", "�������");
    		return "result";
    	}
    	
    	if(quadratic.isResult()) {
    		model.addAttribute("coefficients", "The coefficients are: A=" + aValue + ", B=" + bValue + ", C=" + cValue);
    		model.addAttribute("result", "X1=" + quadratic.getFirstResult() + ", X2=" + quadratic.getSecondResult());
    		try {
				quadraticService.create(quadratic);
			} catch (QuadraticException e) {
				model.addAttribute("result", e);
			}
    	} else {
    		model.addAttribute("result", "Discriminant < 0");
    	}
    	
        return "result";
    }
    	
    private boolean isDecimal(String value) {
    	return value.matches("-?\\d+(\\.\\d+)?");
    }
	
	@RequestMapping(value = "/a", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("employeeHome", "employee", new Quadratic());
    }

	@RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
	public String findOwner(@PathVariable("user") String userName, Model model) {
		model.addAttribute("message", "Hello " + userName + "!");
		return "index";
	}

	// @RequestMapping(value = "/new", method = RequestMethod.GET,
	// produces="application/json")
	// @ResponseBody
	// public Quadratic getNewForm() {
	// return new Quadratic(1, 2, 3);
	// }

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public @ResponseBody Quadratic getShopInJSON(@PathVariable String name) {

		return new Quadratic();

	}

	@RequestMapping(value = "/new/{name}", method = RequestMethod.GET)
	public @ResponseBody Quadratic getQuadratic(@PathVariable String name) {

		Quadratic quadratic = new Quadratic();

		return quadratic;
	}
	//
	// @RequestMapping(value="/new", method = RequestMethod.GET)
	// public AppointmentForm getNewForm() {
	// return new AppointmentForm();
	// }

}
