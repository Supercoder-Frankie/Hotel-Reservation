package model;

public class FreeRoom extends Room{
    public FreeRoom() {
        this.price = 0;
    }

    @Override
    public String toString() {
        return "model.FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }
}
