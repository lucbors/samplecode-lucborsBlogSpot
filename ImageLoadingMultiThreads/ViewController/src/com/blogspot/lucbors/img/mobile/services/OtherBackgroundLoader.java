package com.blogspot.lucbors.img.mobile.services;


public class OtherBackgroundLoader implements Runnable {
    public OtherBackgroundLoader() {
        super();
    }

    EmployeeService empServ = null;

    public OtherBackgroundLoader(EmployeeService empServ) {
        this.empServ = empServ;
    }

    boolean go = false;

    public void run() {
        while (true) {
            if (go) {
                empServ.loadActiveImage();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
            }
        }
    }
}
