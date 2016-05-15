/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

import processing.data.JSONArray;

/**
 *
 * @author Kevin
 */
import java.util.ArrayList;
public class Main {
    public static void main(String[] args){
        Converter c = new Converter();
        
        double lattitude = 51.92510;
        double longitude = 4.46868;
        double[] latLongCoords = {51.92510, 4.46868};
        int[] RDcoords = {91819, 437802};
        /**
         * All the converter functions are executed here.
         */
        c.RDToDecimal(RDcoords);
        c.DecimalToRD(latLongCoords);

        c.DMStoDecimal(38, 53, 23 ,38, 53, 23);
        c.DecimalToDMS(51.92510);

        c.RDToDMS(RDcoords);
        c.DMSToRD(38, 53, 23 ,38, 53, 23);
        
        Window.main("com.mycompany.opdracht1.processing.Window");
        
        
    }
}
