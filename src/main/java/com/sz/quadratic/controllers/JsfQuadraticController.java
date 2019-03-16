package com.sz.quadratic.controllers;

import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "jsfQuadraticController", eager = true)
public class JsfQuadraticController {
    @ManagedProperty(value = "#{quadratic}")
    private Quadratic quadratic;

    @ManagedProperty(value = "#{quadraticService}")
    private IQuadraticService quadraticService;

    public JsfQuadraticController() {
        System.out.println("JsfQuadraticController started!");
    }

    public String count() {
        if (quadratic.isResult()) {
            double firstResult = quadraticService.getFirstResult(quadratic);
            double secondResult = quadraticService.getSecondResult(quadratic);
        }

//        result = "Результат:" + quadratic.getA() + quadratic.getB() + quadratic.getC();
        return "home";
    }

    public String clearCoefficients() {
        quadratic = new Quadratic();
        return "home";
    }

    public Quadratic getQuadratic() {
        return quadratic;
    }

    public void setQuadratic(Quadratic quadratic) {
        this.quadratic = quadratic;
    }

    public IQuadraticService getQuadraticService() {
        return quadraticService;
    }

    public void setQuadraticService(IQuadraticService quadraticService) {
        this.quadraticService = quadraticService;
    }
}
