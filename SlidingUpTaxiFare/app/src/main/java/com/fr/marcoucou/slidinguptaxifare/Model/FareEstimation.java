package com.fr.marcoucou.slidinguptaxifare.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Marc on 02/03/2016.
 */
public class FareEstimation {

    private int estimatedDistance;
    private long estimatedPrice;
    private int estimatedTime;
    private String locale;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }


    public FareEstimation(int distance, long price, int time, String locale){
        this.estimatedDistance = distance;
        this.estimatedPrice = price;
        this.estimatedTime = time;
        this.locale = locale;
    }

    //Setter of the attributes
    public void setEstimatedDistance(int estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public void setEstimatedPrice(long estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }


    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    // getter of the attributes of the class
    public long getEstimatedPrice() {
        return estimatedPrice;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getEstimatedDistance() {
        return estimatedDistance;
    }




}
