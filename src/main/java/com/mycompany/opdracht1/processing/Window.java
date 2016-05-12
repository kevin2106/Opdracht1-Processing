/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Kevin
 */

public class Window extends PApplet{
    
    PImage image;
    
   public void setup(){
       size(799, 649);
       
       frame.setTitle("Earthquakes");
       
       image = loadImage("Map_Iceland_799x649.png");
               
} 
   public void draw(){
       background(image);      

   }
   
   public void drawLocations(){
       
   }
   
}


