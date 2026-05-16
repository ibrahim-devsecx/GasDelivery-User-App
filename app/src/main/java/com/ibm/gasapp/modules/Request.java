package com.ibm.gasapp.modules;

import com.google.android.gms.maps.model.LatLng;

public class Request {
    private String requestId;
    private String mobileNumber;
    private Double latitude;
    private Double longitude;
    private String date;
    private String time;
    private String requestCase;

    public Request() {
    }

    public Request(String requestId, String mobileNumber, Double latitude, Double longitude, String date, String time, String requestCase) {
        this.requestId = requestId;
        this.mobileNumber = mobileNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.time = time;
        this.requestCase = requestCase;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequestCase() {
        return requestCase;
    }

    public void setRequestCase(String requestCase) {
        this.requestCase = requestCase;
    }
}
