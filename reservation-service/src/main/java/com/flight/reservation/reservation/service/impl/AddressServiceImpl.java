package com.flight.reservation.reservation.service.impl;

import com.flight.reservation.reservation.domain.Address;
import com.flight.reservation.reservation.exceptions.AirportAddressNotFound;
import com.flight.reservation.reservation.exceptions.NotFoundException;
import com.flight.reservation.reservation.exceptions.SavingFailureException;
import com.flight.reservation.reservation.mapper.AddressMapper;
import com.flight.reservation.reservation.repo.AddressRepo;
import com.flight.reservation.reservation.service.AddressService;
import com.flight.reservation.reservation.service.request.AddressRequest;
import com.flight.reservation.reservation.service.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Page<AddressResponse> getAllAddresses(Pageable pageable) {
        List<AddressResponse> addressResponses = addressRepo.findAll().stream().parallel()
                .map(AddressMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(addressResponses);
    }

    public AddressResponse save(AddressRequest addressRequest) {

        Address address = AddressMapper.map(addressRequest);
        if(addressRepo.save(address) == null) {
            throw new SavingFailureException("Saving failed");
        }
        return AddressMapper.map(address);

    }

    public AddressResponse findOne(int id) {

        return AddressMapper.map(addressRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the Address")));
    }

    public void delete(int id) {

        addressRepo.deleteById(id);
    }

    @Override
    public AddressResponse update(AddressRequest addressRequest, int id) {
        Address address = AddressMapper.map(addressRequest);
        address.setId(addressRepo.findById(id).orElseThrow(AirportAddressNotFound::new).getId());
        if(addressRepo.save(address) == null) {
            throw new SavingFailureException("Updating failed");
        }
        return AddressMapper.map(address);
    }
}
