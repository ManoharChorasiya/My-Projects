package com.info.emicalc.util;

public class EMICalculator {

    
    public static double calculateEMI(double principal, double annualRatePercent, int tenureMonths) {
        double monthlyRate = annualRatePercent / 12.0 / 100.0;
        if (monthlyRate == 0) return principal / tenureMonths;
        double r = monthlyRate;
        double n = tenureMonths;
        double emi = (principal * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        return emi;
    }

    public static double totalPayment(double emi, int months) {
        return emi * months;
    }

    public static double totalInterest(double totalPayment, double principal) {
        return totalPayment - principal;
    }
}
