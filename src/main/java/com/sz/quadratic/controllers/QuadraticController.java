package com.sz.quadratic.controllers;

import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import com.sz.quadratic.services.DecimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class QuadraticController {
	private static final String COEFFICIENTS_TEXT = "The coefficients are: A=%f, B=%f, C=%f";
	private static final String RESULT_TEXT = "X1=%f; X2=%f";
	private static final String WITH_OUT_RESULT_TEXT = "Discriminant < 0";
	private static final String ERROR = "An error occurred in input data.";

	@Autowired
	private IQuadraticService quadraticService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/result")
    public String result(
    		@RequestParam(value="a") String aValue,
    		@RequestParam(value="b") String bValue,
    		@RequestParam(value="c") String cValue, Model model) {
    	
		Quadratic quadratic = new Quadratic();
    	if(DecimalService.isDecimal(aValue) 
    			&& DecimalService.isDecimal(bValue) 
    			&& DecimalService.isDecimal(cValue)) {
        	quadratic.setA(Double.valueOf(aValue));
        	quadratic.setB(Double.valueOf(bValue));
        	quadratic.setC(Double.valueOf(cValue));
    	} else {
    		model.addAttribute("result", ERROR);
    		return "result";
    	}
    	
    	if(quadraticService.isResult(quadratic)) {
    		List<Quadratic> quadratics = quadraticService.getQuadraticsByCoefficients(quadratic);
			if (!quadratics.isEmpty()) {
				quadratic = quadratics.get(0);
			} else {
    			quadraticService.calculateResult(quadratic);
    			quadraticService.saveQuadratic(quadratic);
			}
			model.addAttribute("coefficients",
					String.format(COEFFICIENTS_TEXT, quadratic.getA(), quadratic.getB(), quadratic.getC()));
    		model.addAttribute("result", String.format(RESULT_TEXT, quadratic.getX1(), quadratic.getX2()));
    	} else {
    		model.addAttribute("result", WITH_OUT_RESULT_TEXT);
    	}
    	
        return "result";
    }

    @RequestMapping(value = "/all")
    public String all(Model model) {
        model.addAttribute("result", quadraticService.getAllQuadratics());
        return "all";
	}

    @RequestMapping(value = "/clearCache")
    public String cacheUpdate(Model model) {
	    quadraticService.clearCache();
		model.addAttribute("result", "Cache is reloaded");
	    return "clearCache";
    }
}
