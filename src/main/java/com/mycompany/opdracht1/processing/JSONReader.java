/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.opdracht1.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.String.format;
import static java.lang.String.format;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import processing.data.JSONArray;

import processing.data.JSONObject;
/**
 *
 * @author Kevin
 */
public class JSONReader {
    public JSONArray getLocalData() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException{
        JSONArray array = new JSONArray();

        ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("test.json").getFile());
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        String jsonText = br.readLine();
        
        JSONObject jsonObj = JSONObject.parse(jsonText);
        
        array = jsonObj.getJSONArray("results");
        
        return array;
    }
    
    public JSONArray getData(){
        JSONArray array = new JSONArray();
        try {
            URL url = new URL("http://apis.is/earthquake/is");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            
            String jsonText = br.readLine();
            
            JSONObject jsonObj = JSONObject.parse(jsonText);
            array = jsonObj.getJSONArray("results");            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        }    
      return array;
    }
    
    public ArrayList<Earthquake> convertToList(JSONArray array){
        ArrayList<Earthquake> list = new ArrayList();
        System.out.println(array.size());
        if(array != null){
            for(int i =0; i < array.size(); i++){
                Earthquake earthquake = new Earthquake();
                earthquake.setDepth(array.getJSONObject(i).getFloat("depth"));
                earthquake.setHumanReadableLocation(array.getJSONObject(i).getString("humanReadableLocation"));
                earthquake.setLatitude(array.getJSONObject(i).getFloat("latitude"));
                earthquake.setLongitude(array.getJSONObject(i).getFloat("longitude"));
                earthquake.setQuality(array.getJSONObject(i).getFloat("quality"));
                earthquake.setSize(array.getJSONObject(i).getFloat("size"));
                
                String dateString = array.getJSONObject(i).getString("timestamp");
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date date;
                try {
                    date = dateFormat.parse(dateString);
                    earthquake.setTimestamp(date);
                } catch (ParseException ex) {
                    Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                list.add(earthquake);
            }         
        }
        return list;
    }
}
