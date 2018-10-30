package com.techverito.bookmycinema.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApplicationConstants {
    public static final Set<String> platiNumSeats = new HashSet<>(Arrays.asList("A1","A2","A3","A4","A5","A6","A7"));
    public static final Set<String> goldSeats = new HashSet<>(Arrays.asList("B1","B2","B3","B4","B5","B6"));
    public static final Set<String> silverSeats = new HashSet<>(Arrays.asList("C1","C2","C3","C4","C5","C6","C7","C8","C9"));
    public static final Set<String> totalSeats = new HashSet<>(Arrays.asList("A1","A2","A3","A4","A5","A6","A7","B1","B2","B3","B4","B5","B6","C1","C2","C3","C4","C5","C6","C7","C8","C9"));
    public static final double platinumTicketCost = 320;
    public static final double goldTicketCost = 280;
    public static final double silverTicketCost = 240;
    public static final double serviceTax = 14; //percent
    public static final  double swachhBharatCess = 0.5; //percent
    public static final double krishiKalyanCess = 0.5; //percent
}
