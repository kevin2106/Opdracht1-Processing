/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

/**
 *
 * @author KevinPC
 */
public class Converter {

    Earthquake earthquake;

    public double[] RDToDecimal(int[] coords) {

        int X0 = 155000;
        int Y0 = 463000;
        double phi0 = 52.15517440;
        double lam0 = 5.38720621;

        int[] Kp = {0, 2, 0, 2, 0, 2, 1, 4, 2, 4, 1};
        int[] Kq = {1, 0, 2, 1, 3, 2, 0, 0, 3, 1, 1};
        double[] Kpq = {3235.65389, -32.58297, -0.24750, -0.84978, -0.06550, -0.01709, -0.00738, 0.00530, -0.00039, 0.00033, -0.00012};

        int[] Lp = {1, 1, 1, 3, 1, 3, 0, 3, 1, 0, 2, 5};
        int[] Lq = {0, 1, 2, 0, 3, 1, 1, 2, 4, 2, 0, 0};
        double[] Lpq = {5260.52916, 105.94684, 2.45656, -0.81885, 0.05594, -0.05607, 0.01199, -0.00256, 0.00128, 0.00022, -0.00022, 0.00026};

        double dX = 0.00001 * (coords[0] - X0);
        double dY = 0.00001 * (coords[1] - Y0);

        double phi = 0;
        double lam = 0;
        
        for (int i = 0; i < Kpq.length; i++) {
            phi = phi + (Kpq[i] * Math.pow(dX, Kp[i])* Math.pow(dY, Kq[i]));
        }
        phi = phi0 + phi / 3600;
        
        for (int i = 0; i < Lpq.length; i++) {
            lam = lam + (Lpq[i] * Math.pow(dX, Lp[i])* Math.pow(dY, Lq[i]));
        }
        lam = lam0 + lam / 3600;
        
        double[] latLong = {phi, lam};
        return latLong;

    }
    
    public double[] DecimalToRD(double[] coords) {

        int X0 = 155000;
        int Y0 = 463000;
        double phi0 = 52.15517440;
        double lam0 = 5.38720621;

        int[] Rp = {0, 1, 2, 0, 1, 3, 1, 0, 2};
        int[] Rq = {1, 1, 1, 3, 0, 1, 3, 2, 3};
        double[] Rpq = {190094.945, -11832.228, -114.221, -32.391, -0.705, -2.340, -0.608, -0.008, 0.148};

        int[] Sp = {1, 0, 2, 1, 3, 0, 2, 1, 0, 1};
        int[] Sq = {0, 2, 0, 2, 0, 1, 2, 1, 4, 4};
        double[] Spq = {309056.544, 3638.893, 73.077, -157.984, 59.788, 0.433, -6.439, -0.032, 0.092, -0.054};

        double dPhi = 0.36 * (coords[0] - phi0);
        double dLam = 0.36 * (coords[1] - lam0);

        double X = 0;
        double Y = 0;

        for (int i = 0; i < Rpq.length; i++) {
            X = X + (Rpq[i] * Math.pow(dPhi, Rp[i]) * Math.pow(dLam, Rq[i]));
        }
        X = X0 + X;

        for (int i = 0; i < Spq.length; i++) {
            Y = Y + (Spq[i] * Math.pow(dPhi, Sp[i]) * Math.pow(dLam, Sq[i]));
        }
        Y = Y0 + Y;

        double[] rdCoords = {X, Y};
        
        return rdCoords;
    }
    
     public double[] DMStoDecimal(int degree, int minutes, double seconds, int degree2, int minutes2, double seconds2){
         double decimal = degree + (minutes / 60) + (seconds / 3600);
         double decimal2 = degree + (minutes / 60) + (seconds / 3600);
         double[] decimals = {decimal, decimal2};
         return decimals;
     }
     
     public int[] DecimalToDMS (double decimal){
         int degree = (int) Math.floor(decimal);
         int minutes = (int) (Math.floor(decimal * 60) % 60);
         int seconds = (int) (Math.abs(decimal) * 3600) % 60;
         
         int[] dms = {degree, minutes, seconds};
         return dms;
     }
     
     public void RDToDMS(int[] coords){
         double[] latLong = RDToDecimal(coords);
         double[] decimals = {latLong[0], latLong[1]};
         
         int[][] dms1 = {DecimalToDMS(decimals[0]), DecimalToDMS(decimals[1])};
     }
     
     public void DMSToRD(int degree, int minutes, double seconds, int degree2, int minutes2, double seconds2){
         double[] decimal = DMStoDecimal(degree, minutes, seconds, degree2, minutes2, seconds2);
        double[] RD = DecimalToRD(decimal);
        
     }
}
