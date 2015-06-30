package com.fhacust.app.mobile.beans;

import java.util.Timer;

import java.util.TimerTask;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.FeatureContext;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class FlightAppBean {

    private String flightNumber;

    boolean isPopupOpen = false;


    public FlightAppBean() {
        super();
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

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
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
