package com.flight.reservation.reservation.mapper;

import com.flight.reservation.reservation.domain.Address;
import com.flight.reservation.reservation.service.request.AddressRequest;
import com.flight.reservation.reservation.service.response.AddressResponse;

public class AddressMapper {
    public static AddressResponse map(Address address) {
        if (address == null) return null;
        return new AddressResponse(address.getId(), address.getStreet(), address.getCity(), address.getState(), address.getZip());
    }

    public static Address map(AddressRequest addressRequest){
        if (addressRequest == null) return null;
        return new Address(addressRequest.getStreet(), addressRequest.getCity(), addressRequest.getState(), addressRequest.getZip());
    }
}
