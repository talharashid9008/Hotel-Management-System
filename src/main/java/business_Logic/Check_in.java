package business_Logic;

import java.io.Serializable;

public class Check_in implements Serializable {


    public String date;
    String comments;
    Reservation reservation;
    String reservation_id;

    public Check_in(String date, String comments, Reservation reservation) {
        super();
        this.date = date;
        this.comments = comments;
        this.reservation = reservation;
        this.reservation_id=this.reservation.getReservation_ID();
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Reservation getRoom_no() {
        return reservation;
    }

    public void setRoom_no(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }


}
