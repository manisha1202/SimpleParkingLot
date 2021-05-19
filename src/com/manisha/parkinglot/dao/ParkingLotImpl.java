package com.manisha.parkinglot.dao;

import com.manisha.parkinglot.models.Slot;
import com.manisha.parkinglot.models.Ticket;
import com.manisha.parkinglot.models.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotImpl implements ParkingLot {
    private int size;
    private PriorityQueue<Slot> pq;
    private HashMap<Vehicle, Slot> vehicleSlotHashMap = new HashMap<>();
    private HashMap<Slot, Vehicle> slotVehicleHashMap = new HashMap<>();
    private HashSet<Ticket> ticketSet = new HashSet<>();

    public ParkingLotImpl(int size) {
        this.size = size;
        pq = new PriorityQueue<>(new Comparator<Slot>() {
            @Override
            public int compare(Slot slot, Slot t1) {
                return slot.getSlotId().compareTo(t1.getSlotId());
            }
        });
        createSlots();
    }
    public int getNumberOfAvaliableSlots(){
        return pq.size();
    }


    private void createSlots() {
        for (int i = 1; i <=size; i++) {
            Slot slot = new Slot(i);
            pq.add(slot);
        }
    }

    @Override
    public boolean isSlotAvailable() {
        if (pq.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Slot> getAvailableSlot(Vehicle vehicle) {
        Slot slot = null;
        if (!pq.isEmpty()) {
            slot = pq.poll();

            vehicleSlotHashMap.put(vehicle, slot);
            slotVehicleHashMap.put(slot, vehicle);
        }
        return Optional.ofNullable(slot);
    }

    @Override
    public void clearSlot(int slotId) {
        for (Ticket t : ticketSet) {
            if (t.getSlot().getSlotId() == slotId) {
                pq.add(t.getSlot());
                slotVehicleHashMap.remove(t.getSlot());
                vehicleSlotHashMap.remove(t.getVehicle());
                ticketSet.remove(t);
                break;
            }
        }

    }

    @Override
    public List<String> getRegistrationNumbersByAge(int age) {
//        ArrayList<String> al = new ArrayList<>();
//        for (Map.Entry<Slot, Vehicle> entry : slotVehicleHashMap.entrySet()) {
//            if (entry.getValue().getDriverAge() == age) {
//                al.add(entry.getValue().getRegistrationNo());
//            }
//        }
//        return al;
        return slotVehicleHashMap.values().stream().filter(vehicle -> vehicle.getDriverAge() == age)
                .map(Vehicle::getRegistrationNo).collect(
                Collectors.toList());
    }

    @Override
    public List<Slot> getSlotsByAge(int age) {
//        ArrayList<Slot> al = new ArrayList<>();
//        for (Map.Entry<Vehicle, Slot> entry : vehicleSlotHashMap.entrySet()) {
//            if (entry.getKey().getDriverAge() == age) {
//                al.add(entry.getValue());
//            }
//        }
//        return al;
        return vehicleSlotHashMap.entrySet().stream().filter(t -> t.getKey().getDriverAge() == age)
                .map(Map.Entry::getValue).collect(
                        Collectors.toList());

    }

    @Override
    public int getSlotOfVehicle(String registrationNo) {
        int slot = -1;
        for (Map.Entry<Vehicle, Slot> entry : vehicleSlotHashMap.entrySet()) {
            String registrationNum = entry.getKey().getRegistrationNo();
            if (registrationNum.equals(registrationNo)) {
                slot = entry.getValue().getSlotId();
            }
        }
        return slot;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketSet.add(ticket);
    }


}
