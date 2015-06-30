package com.fhacust.app.mobile.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SlotEntity {
    
    private String time;
    private String airportCode;
    private Date dateTime;

    private String airportName;
    private String city;
    private String country;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    
    public SlotEntity() {
        super();
    }


    public void setTime(String time) {
        String oldTime = this.time;
        this.time = time;
        
   //     setDateTime (convertStringToDate(time));
        propertyChangeSupport.firePropertyChange("time", oldTime, time);
    }

    public Date convertStringToDate(String theDate){
                                                                  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date();
        try
        {
            date = simpleDateFormat.parse(theDate);
            System.out.println("date : "+simpleDateFormat.format(date));
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
     return date;
    }

    public String getTime() {
        return time;
    }

    public void setAirportCode(String airportCode) {
        String oldAirportCode = this.airportCode;
        this.airportCode = airportCode;
        propertyChangeSupport.firePropertyChange("airportCode", oldAirportCode, airportCode);
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportName(String airportName) {
        String oldAirportName = this.airportName;
        this.airportName = airportName;
        propertyChangeSupport.firePropertyChange("airportName", oldAirportName, airportName);
    }

    public String getAirportName() {
        return airportName;
    }

    public void setCity(String city) {
        String oldCity = this.city;
        this.city = city;
        propertyChangeSupport.firePropertyChange("city", oldCity, city);
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        propertyChangeSupport.firePropertyChange("country", oldCountry, country);
    }

    public String getCountry() {
        return country;
    }
    
    public void setDateTime(Date dateTime) {
        Date oldDateTime = this.dateTime;
        this.dateTime = dateTime;
        propertyChangeSupport.firePropertyChange("dateTime", oldDateTime, dateTime);
    }

    public Date getDateTime() {
        return dateTime;
    }
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
