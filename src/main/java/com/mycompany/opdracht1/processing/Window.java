/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.JSONArray;

/**
 *
 * @author Kevin
 */
public class Window extends PApplet {

    boolean linkClicked = false;
    PImage image;
    ArrayList<Earthquake> earthquakeList;

    public void setup() {
        try {
            size(799, 649);
            frame.setTitle("Earthquakes");
            image = loadImage("Map_Iceland_799x649.png");
            
            JSONReader reader = new JSONReader();
            //JSONArray data = reader.getData();
            JSONArray localData = reader.getLocalData();
            //earthquakeList = reader.convertToList(data);
            earthquakeList = reader.convertToList(localData);
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void draw() {
        background(image);

        drawTitle();

        drawLegenda();

        drawLocations(earthquakeList);

    }

    public void drawLocations(ArrayList<Earthquake> earthquakeList) {

        for (Earthquake earthquake : earthquakeList) {
            float lattitude = earthquake.convertLat();
            float longitude = earthquake.convertLong();
            float size = earthquake.getSize();
            float depth = earthquake.getDepth();
            int colerp = (int) lerp(255, 0, size);
            this.fill(255, colerp, 0);
            this.ellipse(lattitude, longitude, 5, 5);

            //System.out.println("X: " + mouseX);
            //System.out.println("Y: " + mouseY);
            if ((mouseX > lattitude - 5 && mouseX < lattitude + 5) && (mouseY > longitude - 5 && mouseY < longitude + 5)) {
                float height = lerp(0, -10, depth);
                this.rect(lattitude - 2.5f, longitude - 5, 5, height);

                this.fill(0, 0, 0);
                DecimalFormat df = new DecimalFormat("#.#");
                String newDepth = df.format(depth);
                String depthText = newDepth + " km";
                this.text(depthText, lattitude - 15, longitude + height - 8);

            }
        }
    }

    private void drawLegenda() {
        this.fill(128, 173, 209);
        this.rect(197, 600, 400, 45);

        PFont newfont = this.createFont("legenda", 10);
        this.textFont(newfont);

        this.fill(255, 0, 0);
        this.ellipse(215, 620, 5, 5);
        this.text("= heavy earthquake", 220, 623);

        this.fill(255, 255, 0);
        this.ellipse(215, 630, 5, 5);
        this.text("= weak earthquake", 220, 633);

        this.rect(340, 635, 5, -15);
        this.fill(0, 0, 0);
        this.text("Depth", 330, 615);

        sourceLink();
    }

    private void sourceLink() {
        this.fill(6, 69, 173);
        this.text("Source", 550, 635);

        if ((mouseX > 540 && mouseX < 600) && (mouseY > 610 && mouseY < 650)) {
            if (this.mousePressed && !linkClicked) {
                this.link("http://docs.apis.is/#endpoint-earthquake");
                linkClicked = true;
            }
        }
    }

    private void drawTitle() {
        this.fill(0, 0, 0);
        PFont font = this.createFont("title", 20);
        this.textFont(font);
        this.text("Earthquakes in Iceland", 300, 60);
    }
}
