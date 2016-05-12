/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

import java.util.Date;
import static processing.core.PApplet.map;

/**
 *
 * @author Kevin
 */
public class Earthquake {
    private Date timestamp;
    private float latitude;
    private float longitude;
    private float depth;
    private float size;
    private float quality;
    private String humanReadableLocation;

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the depth
     */
    public float getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(float depth) {
        this.depth = depth;
    }

    /**
     * @return the size
     */
    public float getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(float size) {
        this.size = size;
    }

    /**
     * @return the quality
     */
    public float getQuality() {
        return quality;
    }

    /**
     * @param quality the quality to set
     */
    public void setQuality(float quality) {
        this.quality = quality;
    }

    /**
     * @return the humanReadableLocation
     */
    public String getHumanReadableLocation() {
        return humanReadableLocation;
    }

    /**
     * @param humanReadableLocation the humanReadableLocation to set
     */
    public void setHumanReadableLocation(String humanReadableLocation) {
        this.humanReadableLocation = humanReadableLocation;
    }
    
    public void convertLat(){
        float mapLat = map(getLatitude(), 63.1f, 66.8f, 0, 649);
        float mapLong = map(getLongitude(), -25.0f, -13.0f, 0, 799);
    }
    
}
