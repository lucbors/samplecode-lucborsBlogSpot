package com.blogspot.lucbors.img.mobile.services;


public class BackgroundLoader implements Runnable {
    public BackgroundLoader() {
        super();
    }

    EmployeeService empServ = null;

    public BackgroundLoader(EmployeeService empServ) {
        this.empServ = empServ;
    }

    boolean go = false;

    public void run() {
        while (true) {
            if (go) {
                empServ.loadImage();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
            }
        }
    }
}
