package com.techverito.bookmycinema.model;

public class Revenue {
    public double totalRevenue;
    public double serviceTax;
    public double swachhBharatCess;
    public double krishiKalyanCess;

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public double getSwachhBharatCess() {
        return swachhBharatCess;
    }

    public void setSwachhBharatCess(double swachhBharatCess) {
        this.swachhBharatCess = swachhBharatCess;
    }

    public double getKrishiKalyanCess() {
        return krishiKalyanCess;
    }

    public void setKrishiKalyanCess(double krishiKalyanCess) {
        this.krishiKalyanCess = krishiKalyanCess;
    }
}
