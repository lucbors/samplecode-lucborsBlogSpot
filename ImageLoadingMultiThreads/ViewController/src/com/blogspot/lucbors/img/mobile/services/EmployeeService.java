package com.blogspot.lucbors.img.mobile.services;

import com.blogspot.lucbors.img.mobile.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;


public class EmployeeService {
    protected static List s_employees = new ArrayList();
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public EmployeeService() {
        s_employees.add(new Employee(130, "Mary", "Atkinson",true));
        s_employees.add(new Employee(105, "David", "Austin",true));
        s_employees.add(new Employee(116, "Shelli", "Baida",true));
        s_employees.add(new Employee(151, "Dannica", "Bernstein",false));
        s_employees.add(new Employee(129, "Laura", "Bissot",true));
        s_employees.add(new Employee(148, "Gerald", "Cambrault",true));
        s_employees.add(new Employee(154, "Nina", "Cambrault",true));
        s_employees.add(new Employee(110, "John", "Chen",true));
        s_employees.add(new Employee(119, "Karen", "Colmenares",true));
        s_employees.add(new Employee(142, "Curtis", "Davies",false));
        s_employees.add(new Employee(102, "Lex", "De Haan",true));
        s_employees.add(new Employee(104, "Bruce", "Ernst",true));
        s_employees.add(new Employee(147, "Allison", "Errazuriz",true));
        s_employees.add(new Employee(109, "Daniel", "Faviet",false));
        s_employees.add(new Employee(121, "Adam", "Fripp",true));
        s_employees.add(new Employee(135, "Ki", "Gee",true));
        s_employees.add(new Employee(183, "Girard", "Geoni",true));
        s_employees.add(new Employee(108, "Nancy", "Greenberg",true));
        s_employees.add(new Employee(152, "Patricia", "Hall",true));
        s_employees.add(new Employee(118, "Guy", "Himuro",true));
        s_employees.add(new Employee(103, "Alexander", "Hunold",false));
        s_employees.add(new Employee(122, "Payam", "Kaufling",true));
        s_employees.add(new Employee(115, "Alexander", "Khoo",true));
        s_employees.add(new Employee(100, "Steven", "King",true));
        s_employees.add(new Employee(101, "Neena", "Kochhar",true));
        s_employees.add(new Employee(137, "Renske", "Ladwig",true));
        s_employees.add(new Employee(127, "James", "Landry",true));
        s_employees.add(new Employee(107, "Diana", "Lorentz",true));
        s_employees.add(new Employee(133, "Jason", "Mallin",true));
        s_employees.add(new Employee(128, "Steven", "Markle",true));
        s_employees.add(new Employee(131, "James", "Marlow",true));
        s_employees.add(new Employee(143, "Randall", "Matos",true));
        s_employees.add(new Employee(126, "Irene", "Mikkilineni",true));
        s_employees.add(new Employee(124, "Kevin", "Mourgos",true));
        s_employees.add(new Employee(125, "Julia", "Nayer",true));
        s_employees.add(new Employee(153, "Christine", "Olsen",true));
        s_employees.add(new Employee(132, "TJ", "Olson",true));
        s_employees.add(new Employee(146, "Karen", "Partners",true));
        s_employees.add(new Employee(106, "Valli", "Pataballa",true));
        s_employees.add(new Employee(140, "Joshua", "Patel",true));
        s_employees.add(new Employee(136, "Hazel", "Philtanker",true));
        s_employees.add(new Employee(113, "Luis", "Popp",true));
        s_employees.add(new Employee(141, "Trenna", "Rajs",true));
        s_employees.add(new Employee(114, "Deb", "Raphaely",true));
        s_employees.add(new Employee(134, "Michael", "Rogers",true));
        s_employees.add(new Employee(145, "Joanne", "Russell",true));
        s_employees.add(new Employee(111, "Ismael", "Sciarra",true));
        s_employees.add(new Employee(139, "John", "Seo",true));
        s_employees.add(new Employee(138, "Stephen", "Stiles",true));
        s_employees.add(new Employee(157, "Patrice", "Sully",true));
        s_employees.add(new Employee(117, "Sigal", "Tobias",true));
        s_employees.add(new Employee(150, "Polly", "Tucker",true));
        s_employees.add(new Employee(155, "Ophelia", "Tuvault",true));
        s_employees.add(new Employee(112, "Jose Manuel", "Urman",true));
        s_employees.add(new Employee(144, "Perly", "Vargas",true));
        s_employees.add(new Employee(123, "Shanta", "Vollman",true));
        s_employees.add(new Employee(120, "Matthew", "Weiss",true));

        setEmpImages();
    }


    public Employee[] getEmployees() {
        return (Employee[]) s_employees.toArray(new Employee[s_employees.size()]);
    }


    public int getEmployeeCount() {
        return s_employees.size();
    }


    public void setEmpImages() {
        for (int x = 0; x < s_employees.size(); x++) {
            Employee e = (Employee) s_employees.get(x);

            if (1 == 0) {
                e.setImageById(e.getEmpId());
                providerChangeSupport.fireProviderRefresh("employees");
            }
        }
        if (1 == 1) {
            startImageLoader();
            startOtherImageLoader();
        }

    }

    public synchronized void loadImage() {
        int i = 0;
        long time = 1000;
        while (i < s_employees.size()) {
            Employee e = (Employee) s_employees.get(i);

            int id = e.getEmpId();
            e.setImageById(id);
          try {
                wait(time);
                // the essentail part that makes the refresh
              AdfmfJavaUtilities.flushDataChangeEvent();
            } catch (InterruptedException f) {
                //catch exception here
            }
            i++;
        }
    }




    public synchronized void loadActiveImage() {
        int i = 0;
        long time = 2000;
        while (i < s_employees.size()) {
            Employee e = (Employee) s_employees.get(i);

            boolean isActive = e.isActive();
            e.setActiveImageByStatus(isActive);
          try {
                wait(time);
                // the essentail part that makes the refresh
              AdfmfJavaUtilities.flushDataChangeEvent();
            } catch (InterruptedException f) {
                //catch exception here
            }
            i++;
        }
    }
    /*
     * How it was done



     */


    /*
     * Starts the Refersher thread which invokes the loadImage method to load the images in
     * a background process.
     */

    public void startImageLoader() {
        setLoaderStarted(true);
        loader.go = true;
        if (!worker.isAlive()) {
            worker.start();
        }
        setLoaderStarted(loader.go);
    }

    /*
     *
     * Stop the Refresher thread
     *
     */

    public void stopImageLoader() {
        if (loader.go == true) {
            loader.go = false;
            setLoaderStarted(false);
        }
    }

    public void setLoaderStarted(boolean loaderStarted) {
        boolean oldLoaderStarted = this.loaderStarted;
        this.loaderStarted = loaderStarted;
        propertyChangeSupport.firePropertyChange("loaderStarted", oldLoaderStarted, loaderStarted);
    }

    public boolean isLoaderStarted() {
        return loaderStarted;
    }
    
    /* for second thread */
        public void startOtherImageLoader() {
            setOtherLoaderStarted(true);
            otherLoader.go = true;
            if (!otherWorker.isAlive()) {
                otherWorker.start();
            }
            setOtherLoaderStarted(loader.go);
        }
       
    public void stopOtherImageLoader() {
        if (otherLoader.go == true) {
            otherLoader.go = false;
            setOtherLoaderStarted(false);
        }
    }


    public void setOtherLoaderStarted(boolean otherLoaderStarted) {
        boolean oldOtherLoaderStarted = this.otherLoaderStarted;
        this.otherLoaderStarted = otherLoaderStarted;
        propertyChangeSupport.firePropertyChange("otherLoaderStarted", oldOtherLoaderStarted, otherLoaderStarted);
    }

    public boolean isOtherLoaderStarted() {
        return otherLoaderStarted;
    }
    /* end second thread */



    private BackgroundLoader loader = new BackgroundLoader(this);
    private Thread worker = new Thread(loader);
    private boolean loaderStarted = false;
    
    /* for second thread */
    private OtherBackgroundLoader otherLoader = new OtherBackgroundLoader(this);
    private Thread otherWorker = new Thread(otherLoader);
    private boolean otherLoaderStarted = false;
    /* end second thread */



  

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }


}
