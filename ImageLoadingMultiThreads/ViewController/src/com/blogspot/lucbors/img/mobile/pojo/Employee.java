package com.blogspot.lucbors.img.mobile.pojo;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private boolean active = false;

    private String image;
    private String activeImage;


    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Employee() {
        super();
    }

    public Employee(int empId, String firstName, String lastName, Boolean active) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }


    public void setEmpId(int empId) {
        int oldEmpId = this.empId;
        this.empId = empId;
        propertyChangeSupport.firePropertyChange("empId", oldEmpId, empId);
    }

    public int getEmpId() {
        return empId;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertyChangeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertyChangeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setImage(String image) {
        String oldImage = this.image;
        this.image = image;
        propertyChangeSupport.firePropertyChange("image", oldImage, image);
    }

    public String getImage() {
        return image;
    }

    public void setImageById(int id) {
        Integer i = new Integer(id);
        String image = i.toString() + ".png";
        setImage(image);
    }

    private String getEmpImage(int id) {
        String image = "nopic.png";

        if (1 == 0) {

            return image;
        }

        Integer i = new Integer(id);
        if (i.intValue() < 158) {
            image = i.toString() + ".png";
        }
        return image;
    }


    public void setActiveImageByStatus(boolean status) {
        String image =  ( status?"active":"inactive" ) + ".jpeg";
        setActiveImage(image);
    }


    public void setActiveImage(String activeImage) {
        String oldActiveImage = this.activeImage;
        this.activeImage = activeImage;
        propertyChangeSupport.firePropertyChange("activeImage", oldActiveImage, activeImage);
    }

    public String getActiveImage() {
        return activeImage;
    }

    public void setActive(boolean active) {
        boolean oldActive = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldActive, active);
    }

    public boolean isActive() {
        return active;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}
