package service;


import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ReservationService {
    public static List<IRoom> roomList = new ArrayList<>();
    public static List<Reservation> reservationList = new ArrayList<>();

    public void addRoom(IRoom room) {
        roomList.add(room);
    }

    public static IRoom getARoom(String roomId) {
        for (IRoom room : roomList) {
            if (room.getRoomNumber().equals(roomId)) return room;
        }
        System.out.println("Room not found");
        return null;
    }

    // ?
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }

    public static List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        try {
            // 先找到已预定的房间列表
            List<IRoom> resveredRooms = getResveredRooms(checkInDate, checkOutDate);
            for (IRoom room : roomList) {
                // 如果room不在已预订房间的列表里，则加入available里
                if (!resveredRooms.contains(room)) {
                    availableRooms.add(room);
                }
            }
        } catch (Exception ex) {
            if (availableRooms.isEmpty()) return null;
        }
        return availableRooms;
    }

    // 查找指定时间内所有预定的房间
    private static List<IRoom> getResveredRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> reversedRooms = new ArrayList<>();
        try {
            for (Reservation reservation : reservationList) {
                if (reservation.cheakInDate.getTime() <= checkInDate.getTime() &&
                        reservation.checkOutDate.getTime() >= checkOutDate.getTime()) {
                    reversedRooms.add(reservation.room);
                }
            }
        } catch (Exception ex) {
            if (reversedRooms.isEmpty()) return null;
        }
        return reversedRooms;
    }

    public static List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> tempReservationList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (reservation.customer.equals(customer)) tempReservationList.add(reservation);
        }
        return tempReservationList;
    }

    public static void printAllResversation() {
        for (Reservation reservation : reservationList) System.out.println(reservation);
    }
}
