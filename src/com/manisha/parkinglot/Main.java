package com.manisha.parkinglot;

import com.manisha.parkinglot.managers.ParkingLotManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        ParkingLotManager parkingLotManager = new ParkingLotManager(1);
        while(flag) {
            System.out.println("\nPlease enter your choice!!!");
            System.out.println("1: Issue ticket");
            System.out.println("2: return ticket");
            System.out.println("3: get Registration Numbers By Age");
            System.out.println("4: get Slots By Age");
            System.out.println("5: get Slot Of Vehicle");
            System.out.println("6: Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Issue ticket");
                    System.out.println("Enter vehicle registration number and driver age.");
                    String registrationNum = sc.next();
                    int age = sc.nextInt();
                    try{
                        int slotIdForIssueTicket = parkingLotManager.issueTicket(registrationNum, age);
                        System.out.println("Your slot num is: " + slotIdForIssueTicket);
                    }catch (IllegalArgumentException e){
                        System.out.println("no slot available");
                        continue;
                    }
                    break;
                case 2:
                    System.out.println("Return ticket");
                    System.out.println("Enter slot number");
                    int slotIdForReturnTicket = sc.nextInt();
                    parkingLotManager.returnTicket(slotIdForReturnTicket);
                    System.out.println("Thank you for parking here!!!");
                    break;
                case 3:
                    System.out.println("Get Registration Numbers By Age");
                    System.out.println("Enter age: ");
                    int ageForRegistrationNum = sc.nextInt();
                    List<String> listOfRegistrationNumbers =
                            parkingLotManager.getRegistrationNumbersByAge(ageForRegistrationNum);
                    System.out
                            .println("Registration Numbers for age " + ageForRegistrationNum +
                                    " is: ");
                    for (String num : listOfRegistrationNumbers) {
                        System.out.print(num + " ");
                    }
                    break;
                case 4:
                    System.out.println("Get Slots By Age");
                    System.out.println("Enter age: ");
                    int ageForSlots = sc.nextInt();
                    List<Integer> listOfSlots =
                            parkingLotManager.getSlotsByAge(ageForSlots);
                    System.out.println("Slots booked for age " + ageForSlots + " is: ");
                    for (Integer num : listOfSlots) {
                        System.out.print(num + " ");
                    }
                    break;
                case 5:
                    System.out.println("Get Slot Of Vehicle");
                    System.out.println("Enter registration number: ");
                    String registrationNumber = sc.next();
                    int slot = parkingLotManager.getSlotOfVehicle(registrationNumber);
                    System.out.println(
                            "Slot number for vehicle " + registrationNumber + " is " + slot);
                    break;
                case 6:
                    flag=false;
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    System.out.println("Please try again.");
            }
        }
    }
}
