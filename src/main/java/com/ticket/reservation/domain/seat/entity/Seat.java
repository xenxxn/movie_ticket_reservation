package com.ticket.reservation.domain.seat.entity;

import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.dto.SeatEditInput;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(name = "ROW")
    private String row;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public void setSeat(Room room) {
        this.room = room;
    }

    public void updateSeat(SeatEditInput seatEditInput) {
        if (seatEditInput.getRow() != null) {
            this.row = seatEditInput.getRow();
        }
        if (seatEditInput.getNumber() > 0) {
            this.number = seatEditInput.getNumber();
        }
        if (seatEditInput.getStatus() != null) {
            this.status = seatEditInput.getStatus();
        }
        if (seatEditInput.getRoomId() != null) {
            this.room = Room.builder().id(seatEditInput.getRoomId()).build();
        }
    }

    public void setStatus(SeatStatus seatStatus) {
        this.status = seatStatus;
    }
}
