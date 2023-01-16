package model;

import java.util.Objects;

public class Room implements IRoom{
    public String roomNumber;
    public double price;
    public RoomType roomType;

    public Room() {}

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public boolean isFree() {
        return false;
    }

    public String toString() {
        return "{" +
                "roomNumber: '" + roomNumber + '\'' +
                ", price: " + price +
                ", Type: " + roomType +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Double.compare(room.price, price) == 0 && roomNumber.equals(room.roomNumber) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType);
    }
}
