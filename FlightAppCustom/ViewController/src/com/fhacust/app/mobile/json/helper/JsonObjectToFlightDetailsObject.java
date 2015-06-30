package com.fhacust.app.mobile.json.helper;

import com.fhacust.app.mobile.entities.FlightDetailsEntity;

import java.util.logging.Level;

import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.util.logging.Trace;

public class JsonObjectToFlightDetailsObject {
    public JsonObjectToFlightDetailsObject() {
        super();
    }

    public static FlightDetailsEntity getFlightsObject(String jsonObjectAsString) {

        FlightDetailsEntity flightsResult = null;
        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();
        try {
            flightsResult = (FlightDetailsEntity) jbsh.fromJSON(FlightDetailsEntity.class, jsonObjectAsString);

        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, JsonObjectToFlightDetailsObject.class, "getFlightsObject",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }
        return flightsResult;
    }
}
