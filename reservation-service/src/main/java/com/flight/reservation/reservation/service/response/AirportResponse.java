package com.flight.reservation.reservation.service.response;

public class AirportResponse {
    private long id;

    private String name;

    private String code;

    private AddressResponse address;

    public AirportResponse(){}

    public AirportResponse(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public AirportResponse(long id,
                           String name,
                           String code,
                           AddressResponse address) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

}
