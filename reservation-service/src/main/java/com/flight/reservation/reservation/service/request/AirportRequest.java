package com.flight.reservation.reservation.service.request;

public class AirportRequest {

    private int id;

    private String name;

    private String code;

    private AddressRequest addressRequest;

    public AirportRequest(){}

    public AirportRequest(String name, String code) {

        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }
}
