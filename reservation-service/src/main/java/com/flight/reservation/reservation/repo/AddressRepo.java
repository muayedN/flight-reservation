package com.flight.reservation.reservation.repo;

import com.flight.reservation.reservation.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

}
