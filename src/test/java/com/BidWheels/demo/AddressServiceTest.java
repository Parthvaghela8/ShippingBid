package com.BidWheels.demo;

import com.BidWheels.demo.Model.Address;
import com.BidWheels.demo.Repositry.AddressRepository;
import com.BidWheels.demo.service.AddressService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void getAllAddresses() {
        // Prepare data
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address());
        addressList.add(new Address());

        // Mock repository method
        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        // Call service method
        List<Address> result = addressService.getAllAddresses();

        // Verify
        assertEquals(addressList.size(), result.size());
    }

    @Test
    void getAddressById() {
        // Prepare data
        Address address = new Address();
        address.setAddressId(1L);

        // Mock repository method
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        // Call service method
        Address result = addressService.getAddressById(1L);

        // Verify
        assertEquals(address, result);
    }

    @Test
    void createAddress() {
        // Prepare data
        Address address = new Address();

        // Mock repository method
        Mockito.when(addressRepository.save(any(Address.class))).thenReturn(address);

        // Call service method
        Address result = addressService.createAddress(address);

        // Verify
        assertEquals(address, result);
    }


//    @Test
//    void updateAddress_AddressNotFound() {
//        // Prepare data
//        Address updatedAddress = new Address();
//
//        // Mock repository method
//        Mockito.when(addressRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Call service method
//        Address result = addressService.updateAddress(1L, updatedAddress);
//
//        // Verify
//        assertNull(result);
//    }
//
//    @Test
//    void deleteAddress() {
//        // Call service method
//        addressService.deleteAddress(1L);
//
//        // Verify
//        verify(addressRepository, times(1)).deleteById(1L);
//    }
}
