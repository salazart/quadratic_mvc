package com.sz.quadratic.controllers;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import com.sz.quadratic.services.DecimalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuadraticController {
	
	private Logger logger = LogManager.getLogger(getClass());
	
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
    	if(DecimalService.isDecimal(aValue) 
    			&& DecimalService.isDecimal(bValue) 
    			&& DecimalService.isDecimal(cValue)) {
        	quadratic.setA(Double.valueOf(aValue));
        	quadratic.setB(Double.valueOf(bValue));
        	quadratic.setC(Double.valueOf(cValue));
    	} else {
    		model.addAttribute("result", "Error");
    		return "result";
    	}
    	
    	if(quadratic.isResult()) {
    		double x1 = quadraticService.getFirstResult(quadratic);
    		double x2 = quadraticService.getSecondResult(quadratic);
    		model.addAttribute("coefficients", "The coefficients are: A=" + aValue + ", B=" + bValue + ", C=" + cValue);
    		model.addAttribute("result", "X1=" + x1 + ", X2=" + x2);
    		try {
				quadraticService.create(quadratic);
			} catch (QuadraticException e) {
				model.addAttribute("result", "Error:" + e.getMessage());
			}
    	} else {
    		model.addAttribute("result", "Discriminant < 0");
    	}
    	
        return "result";
    }

    @RequestMapping(value = "/all")
    public String all(Model model) {
        model.addAttribute("result", quadraticService.getAllQuadratics());
        return "all";
	}

    @RequestMapping(value = "/updateCache")
    public String cacheUpdate(Model model) {
//	    quadraticService.updateCash();
	    return "cacheUpdated";
    }
}
