package com.manisha.parkinglot.managers;

import com.manisha.parkinglot.dao.ParkingLot;
import com.manisha.parkinglot.dao.ParkingLotImpl;
import com.manisha.parkinglot.models.Slot;
import com.manisha.parkinglot.models.Ticket;
import com.manisha.parkinglot.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingLotManager {
    private ParkingLot parkingLot;

    public ParkingLotManager(int size) {
        parkingLot=new ParkingLotImpl(size);
    }

    public int issueTicket(String registrationNum, int age) {
        Vehicle vehicle=new Vehicle();
        vehicle.setRegistrationNo(registrationNum);
        vehicle.setDriverAge(age);
        Ticket ticket=generateTicket(vehicle);
        parkingLot.saveTicket(ticket);
        return ticket.getSlot().getSlotId();
    }

    private Ticket generateTicket(Vehicle vehicle) {
        Optional<Slot> slot = parkingLot.getAvailableSlot(vehicle);
        if(!slot.isPresent())
            throw new IllegalArgumentException("no slot availabe");
        Ticket ticket=new Ticket();
        String ticketId=UUID.randomUUID().toString();
        ticket.setTicketId(ticketId);
        ticket.setSlot(slot.get() );
        ticket.setVehicle(vehicle);
        return ticket;
    }

    public void returnTicket(int slotId) {
        parkingLot.clearSlot(slotId);
    }

    public List<String> getRegistrationNumbersByAge(int age) {
        return parkingLot.getRegistrationNumbersByAge(age);
    }

    public List<Integer> getSlotsByAge(int age) {
        return parkingLot.getSlotsByAge(age).stream().map(Slot::getSlotId).collect(Collectors.toList());
    }

    public int getSlotOfVehicle(String registrationNo) {
        return parkingLot.getSlotOfVehicle(registrationNo);
    }
}
