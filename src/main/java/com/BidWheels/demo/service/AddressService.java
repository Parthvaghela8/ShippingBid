package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Address;
import com.BidWheels.demo.Repositry.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Address existingAddress = addressRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            existingAddress.setStreetAddress(updatedAddress.getStreetAddress());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setPostalCode(updatedAddress.getPostalCode());

            return addressRepository.save(existingAddress);
        } else {
            return null; // Handle address not found
        }
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
