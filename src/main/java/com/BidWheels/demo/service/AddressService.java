package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Address;
import com.BidWheels.demo.Repositry.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Integer id, Address addressDetails) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreetAddress(addressDetails.getStreetAddress());
            existingAddress.setCity(addressDetails.getCity());
            existingAddress.setState(addressDetails.getState());
            existingAddress.setPostalCode(addressDetails.getPostalCode());
            return addressRepository.save(existingAddress);
        } else {
            return null;
        }
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}