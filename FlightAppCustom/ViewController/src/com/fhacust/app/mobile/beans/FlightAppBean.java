package com.fhacust.app.mobile.beans;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.Timer;

import java.util.TimerTask;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.DeviceManager;
import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class FlightAppBean {

    private String flightNumber;
    private String signature = "";
    private String image;

    boolean isPopupOpen = false;




    public FlightAppBean() {
        super();
    }


    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }


    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void sendEmail(ActionEvent actionEvent) {
        // Add event code here...
        String content = "";

        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.textForComplaint}", String.class);
        String textForComplaint = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());

        content = "A complaint for customer " + textForComplaint + " wil be filed. Please make sure to follow up.";
        DeviceManagerFactory.getDeviceManager().sendEmail("customerservices@airline.com", null,
                                                          "A complaint will be filed", content, "lucbors@gmail.com",
                                                          null, null);
    }


    public void takeSituationPicture(ActionEvent actionEvent) {
        // Add event code here...


        DeviceManager dm = DeviceManagerFactory.getDeviceManager(); 

        String myImageDataBase64 = dm.getPicture(50,                                    
             DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL,                                    
             DeviceManager.CAMERA_SOURCETYPE_PHOTOLIBRARY, 
             false,  
             DeviceManager.CAMERA_ENCODINGTYPE_JPEG, 200, 200);

            try {
                myImageDataBase64 =
                    URLEncoder.encode(myImageDataBase64, java.nio.charset.StandardCharsets.UTF_8.toString());
                
                setImage(myImageDataBase64);
            } catch (UnsupportedEncodingException e) {
            }
    }


    public void fileComplaint(ActionEvent actionEvent) {
        // Add event code here...


        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "adf.mf.api.amx.doNavigation", new Object[] {
                                                                  "complain" });
    }

    public void setFlightStatusToBoardingComplete(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.updateFlightStatus.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });

    }


    public void flightSelectionChange(ValueChangeEvent valueChangeEvent) {

        // Add event code here...
        MethodExpression me =
            AdfmfJavaUtilities.getMethodExpression("#{bindings.findFlightByNumber.execute}", Object.class, new Class[] {
                                                   });
        me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] { });


    }

    public void submitComplaint(ActionEvent actionEvent) {
        // Add event code here...


        //temporary off

        //    MethodExpression me =
        //    AdfmfJavaUtilities.getMethodExpression("#{bindings.createCustomerComplaint.execute}",Object.class, new Class[] {});
        //    me.invoke(AdfmfJavaUtilities.getAdfELContext(), new Object[] {});

        ValueExpression ve0 =
            AdfmfJavaUtilities.getValueExpression("#{bindings.complaintFiled.inputValue}", Boolean.class);
        ve0.setValue(AdfmfJavaUtilities.getAdfELContext(), true);


        ValueExpression ve1 =
            AdfmfJavaUtilities.getValueExpression("#{applicationScope.currentCarrierCode}", String.class);
        ValueExpression ve2 =
            AdfmfJavaUtilities.getValueExpression("#{applicationScope.currentFlightNumber}", String.class);


        String text =
            "Complaint Filed for: " + (String) ve1.getValue(AdfmfJavaUtilities.getAdfELContext()) + "-" +
            (String) ve2.getValue(AdfmfJavaUtilities.getAdfELContext());


        onPopupShowHideAction(text);


    }

    public void onPopupShowHideAction(String text) {
        if (!isPopupOpen) {

            //the error object is filled with the reason for a JS exception.
            //If the variable is empty, no exception has occured. Note that
            //usually you use the return object to pass information from the
            //JS function back to MAF. In this case, the function doesn't
            //return a value and so its only used here to allow debugging
            //of exceptions


            Object errorMsg =
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                          "popupUtilsShowPopup", new Object[] {
                                                                          "_popShowId", text
            });

            System.out.println(errorMsg);
            isPopupOpen = true;

            //wait a few seconds and then close the popup
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    closePopup();
                    this.cancel();
                }

            }, 3000);

        }
    }


    private void closePopup() {

        if (isPopupOpen) {

            Object errorMsg =
                AdfmfContainerUtilities.invokeContainerJavaScriptFunction(FeatureContext.getCurrentFeatureId(),
                                                                          "popupUtilsHidePopup", new Object[] {
                                                                          "_popCloseId" });
            System.out.println(errorMsg);

            isPopupOpen = false;

            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                      "adf.mf.api.amx.doNavigation", new Object[] {
                                                                      "__back" });


        }

    }




}
