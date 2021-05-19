package com.manisha.parkinglot.dao;

import com.manisha.parkinglot.models.Slot;
import com.manisha.parkinglot.models.Ticket;
import com.manisha.parkinglot.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingLot {

    boolean isSlotAvailable();

    Optional<Slot> getAvailableSlot(Vehicle vehicle);

    void clearSlot(int slotId);

    List<String> getRegistrationNumbersByAge(int age);

    List<Slot> getSlotsByAge(int age);

    int getSlotOfVehicle(String registrationNo);

    void saveTicket(Ticket ticket);
}
