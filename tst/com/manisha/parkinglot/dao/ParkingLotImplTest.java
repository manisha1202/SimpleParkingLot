package com.manisha.parkinglot.dao;


import com.manisha.parkinglot.models.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.mockito.Mockito.mock;

public class ParkingLotImplTest {

    @Test
    public void testParkingLotShouldCreateWithRequiredSize(){
        ParkingLotImpl p = new ParkingLotImpl(10);
        Assert.assertEquals(10, p.getNumberOfAvaliableSlots());
    }

    @Test
    public void test(){
        Set<Ticket> ticketSet = mock(Set.class);
    }
}

