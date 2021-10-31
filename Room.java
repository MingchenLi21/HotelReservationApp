package model;

import java.util.Objects;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private  RoomType roomType;

    public Room (String roomNumber, RoomType roomType, Double price){
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "The room number: " + roomNumber + ". \n  The price: " + price + ".\n Room type: " + roomType + "\n";
    }
}
