package com.flight.reservation.reservation.controller;

import com.flight.reservation.reservation.service.AddressService;
import com.flight.reservation.reservation.service.request.AddressRequest;
import com.flight.reservation.reservation.service.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<AddressResponse> getAllAddresses(Pageable pageable) {
        Page<AddressResponse> result = addressService.getAllAddresses(pageable);
        return result;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AddressResponse addAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.save(addressRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PASSENGER') or hasAuthority('ROLE_AGENT') or hasAuthority('ROLE_ADMIN')")
    public AddressResponse getAddress(@PathVariable int id) {
        return addressService.findOne(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AddressResponse updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable int id) {
        return addressService.update(addressRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAddress(@PathVariable int id) {
        addressService.delete(id);
    }
}
