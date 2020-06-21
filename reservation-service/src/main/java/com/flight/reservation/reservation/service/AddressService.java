package com.flight.reservation.reservation.service;

import com.flight.reservation.reservation.service.request.AddressRequest;
import com.flight.reservation.reservation.service.response.AddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

	public Page<AddressResponse> getAllAddresses(Pageable pageable);

	public AddressResponse save(AddressRequest addressRequest);

	public AddressResponse findOne(int id);

	public void delete(int id);

	public AddressResponse update(AddressRequest address, int id);

}
