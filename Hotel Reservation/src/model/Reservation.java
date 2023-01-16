package model;

import java.util.Date;

public class Reservation {
    public Customer customer;
    public IRoom room;
    public Date cheakInDate;
    public Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date cheakInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.cheakInDate = cheakInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "model.Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", cheakInDate=" + cheakInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
