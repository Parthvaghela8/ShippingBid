package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Address;
import com.BidWheels.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getdata")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);

        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.createAddress(address);
        return ResponseEntity.ok(createdAddress);
    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
//        Address address = addressService.updateAddress(id, updatedAddress);
//
//        if (address != null) {
//            return ResponseEntity.ok(address);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {
//        addressService.deleteAddress(id);
//        return ResponseEntity.ok().build();
//    }
}
