/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author galstern
 */
public class PersistantDataHelper {
    static java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
    static int carbsInput1;
    static int proteinInput1;
    static int dairyInput1;
    static int fruitsInput1;
    static int fatsInput1;
    
    static int sum;
    
    static boolean hasUserEnteredNutritionDataBefore = false;
    
    //Backend editing for steps and stairs counters
    static int steps = 568;
    static int stairs = 422;
    static double decimal;
    static double decimal2;
    
    //Backend for BMI
    static double bmi;
    static double newBMI = Math.round(bmi*100.0)/100.0;
 
    //Backend for heartrate
    static int heartRate = 75;
    
    //Aerobics
    static double distanceProgress;
    static double timeProgress;
    
    //Resistance
    static double monthlyProgress;
    
    //Ratio
    static double ratio = 0.15;
    static double ratioFinal;
    
    //Sleep
    static double sleep = 6;
    static double sleepFinal;
    
    
}
