package com.BidWheels.demo.DTO;

import com.BidWheels.demo.Model.Bids;
import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Model.Shipper;

public class BidDetailsDTO {
    private Bids bid;
    private Shipment shipment;
    private Shipper shipper;

    // Constructors, getters, setters...

    public BidDetailsDTO(Bids bid, Shipment shipment, Shipper shipper) {
        this.bid = bid;
        this.shipment = shipment;
        this.shipper = shipper;
    }

    // Getters and setters...
}
