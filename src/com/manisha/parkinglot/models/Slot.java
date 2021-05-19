package com.manisha.parkinglot.models;


public class Slot {
    private Integer slotId;

    public Slot(Integer id) {
        this.slotId = id;
    }

    public Integer getSlotId() {
        return slotId;
    }
    public String toString(){
        return slotId.toString();
    }
}
