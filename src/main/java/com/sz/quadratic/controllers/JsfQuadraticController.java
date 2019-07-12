package com.sz.quadratic.controllers;

import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "jsfQuadraticController", eager = true)
public class JsfQuadraticController {
    @ManagedProperty(value = "#{quadraticService}")
    private IQuadraticService quadraticService;

    @ManagedProperty(value = "")
    private String result;

    @ManagedProperty(value = "")
    private String a;
    @ManagedProperty(value = "")
    private String b;
    @ManagedProperty(value = "")
    private String c;

    public String count() {
        Quadratic q = getQuadraticFromForm();
        result = q == null || q.getX1() == null
                ? "The quadratic equation does't have result"
                : String.format("X1=%.2f, X2=%.2f", q.getX1(), q.getX2());
        return "home";
    }

    private Quadratic getQuadraticFromForm(){
        return a == null || a.isEmpty() || b == null || b.isEmpty() || c == null || c.isEmpty()
                ? null
                : new Quadratic(Double.parseDouble(a), Double.parseDouble(b), Double.parseDouble(c));
    }

    public String clearCoefficients() {
        a = "";
        b = "";
        c = "";
        result = "";
        return "home";
    }

    public IQuadraticService getQuadraticService() {
        return quadraticService;
    }

    public void setQuadraticService(IQuadraticService quadraticService) {
        this.quadraticService = quadraticService;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
