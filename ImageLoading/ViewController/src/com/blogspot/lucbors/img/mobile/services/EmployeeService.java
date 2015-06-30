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
        s_employees.add(new Employee(130, "Mary", "Atkinson"));
        s_employees.add(new Employee(105, "David", "Austin"));
        s_employees.add(new Employee(116, "Shelli", "Baida"));
        s_employees.add(new Employee(151, "Dannica", "Bernstein"));
        s_employees.add(new Employee(129, "Laura", "Bissot"));
        s_employees.add(new Employee(148, "Gerald", "Cambrault"));
        s_employees.add(new Employee(154, "Nina", "Cambrault"));
        s_employees.add(new Employee(110, "John", "Chen"));
        s_employees.add(new Employee(119, "Karen", "Colmenares"));
        s_employees.add(new Employee(142, "Curtis", "Davies"));
        s_employees.add(new Employee(102, "Lex", "De Haan"));
        s_employees.add(new Employee(104, "Bruce", "Ernst"));
        s_employees.add(new Employee(147, "Allison", "Errazuriz"));
        s_employees.add(new Employee(109, "Daniel", "Faviet"));
        s_employees.add(new Employee(121, "Adam", "Fripp"));
        s_employees.add(new Employee(135, "Ki", "Gee"));
        s_employees.add(new Employee(183, "Girard", "Geoni"));
        s_employees.add(new Employee(108, "Nancy", "Greenberg"));
        s_employees.add(new Employee(152, "Patricia", "Hall"));
        s_employees.add(new Employee(118, "Guy", "Himuro"));
        s_employees.add(new Employee(103, "Alexander", "Hunold"));
        s_employees.add(new Employee(122, "Payam", "Kaufling"));
        s_employees.add(new Employee(115, "Alexander", "Khoo"));
        s_employees.add(new Employee(100, "Steven", "King"));
        s_employees.add(new Employee(101, "Neena", "Kochhar"));
        s_employees.add(new Employee(137, "Renske", "Ladwig"));
        s_employees.add(new Employee(127, "James", "Landry"));
        s_employees.add(new Employee(107, "Diana", "Lorentz"));
        s_employees.add(new Employee(133, "Jason", "Mallin"));
        s_employees.add(new Employee(128, "Steven", "Markle"));
        s_employees.add(new Employee(131, "James", "Marlow"));
        s_employees.add(new Employee(143, "Randall", "Matos"));
        s_employees.add(new Employee(126, "Irene", "Mikkilineni"));
        s_employees.add(new Employee(124, "Kevin", "Mourgos"));
        s_employees.add(new Employee(125, "Julia", "Nayer"));
        s_employees.add(new Employee(153, "Christine", "Olsen"));
        s_employees.add(new Employee(132, "TJ", "Olson"));
        s_employees.add(new Employee(146, "Karen", "Partners"));
        s_employees.add(new Employee(106, "Valli", "Pataballa"));
        s_employees.add(new Employee(140, "Joshua", "Patel"));
        s_employees.add(new Employee(136, "Hazel", "Philtanker"));
        s_employees.add(new Employee(113, "Luis", "Popp"));
        s_employees.add(new Employee(141, "Trenna", "Rajs"));
        s_employees.add(new Employee(114, "Deb", "Raphaely"));
        s_employees.add(new Employee(134, "Michael", "Rogers"));
        s_employees.add(new Employee(145, "Joanne", "Russell"));
        s_employees.add(new Employee(111, "Ismael", "Sciarra"));
        s_employees.add(new Employee(139, "John", "Seo"));
        s_employees.add(new Employee(138, "Stephen", "Stiles"));
        s_employees.add(new Employee(157, "Patrice", "Sully"));
        s_employees.add(new Employee(117, "Sigal", "Tobias"));
        s_employees.add(new Employee(150, "Polly", "Tucker"));
        s_employees.add(new Employee(155, "Ophelia", "Tuvault"));
        s_employees.add(new Employee(112, "Jose Manuel", "Urman"));
        s_employees.add(new Employee(144, "Perly", "Vargas"));
        s_employees.add(new Employee(123, "Shanta", "Vollman"));
        s_employees.add(new Employee(120, "Matthew", "Weiss"));

        setEmpImage();
    }


    public Employee[] getEmployees() {
        return (Employee[]) s_employees.toArray(new Employee[s_employees.size()]);
    }


    public int getEmployeeCount() {
        return s_employees.size();
    }


    public void setEmpImage() {
        for (int x = 0; x < s_employees.size(); x++) {
            Employee e = (Employee) s_employees.get(x);

            if (1 == 0) {
                e.setImageById(e.getEmpId());
                providerChangeSupport.fireProviderRefresh("employees");
            }

        }

        
        if (1 == 1) {
            startImageLoader();
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

    private BackgroundLoader loader = new BackgroundLoader(this);
    private Thread worker = new Thread(loader);
    private boolean loaderStarted = false;



  

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
