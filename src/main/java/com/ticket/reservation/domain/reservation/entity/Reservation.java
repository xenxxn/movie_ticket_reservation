package com.ticket.reservation.domain.reservation.entity;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.dto.ReservationDto;
import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.reservation.dto.ReservationOutput;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RESERVATION")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SHOWTIME_ID")
    private Showtime showtime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public static Reservation toEntity(ReservationDto reservationDto) {
        Showtime showtime = Showtime.builder().id(reservationDto.getShowtimeId()).build();
        Seat seat = Seat.builder().id(reservationDto.getSeatId()).build();
        return Reservation.builder()
            .id(reservationDto.getReservationId())
            .showtime(showtime)
            .seat(seat)
            .reservationStatus(reservationDto.getReservationStatus())
            .build();
    }

    public static Reservation toEntity(ReservationInput reservationInput) {
        Showtime showtime = Showtime.builder().id(reservationInput.getShowtimeId()).build();
        Seat seat = Seat.builder().id(reservationInput.getSeatId()).build();

        return Reservation.builder()
            .id(reservationInput.getReservationId())
            .showtime(showtime)
            .seat(seat)
            .reservationStatus(ReservationStatus.UNUSED)
            .build();
    }

    public static Reservation toEntity(ReservationOutput reservationOutput) {
        Showtime showtime = Showtime.builder().id(reservationOutput.getShowtimeId()).build();
        Seat seat = Seat.builder().id(reservationOutput.getSeatId()).build();

        return Reservation.builder()
            .id(reservationOutput.getReservationId())
            .showtime(showtime)
            .seat(seat)
            .reservationStatus(reservationOutput.getReservationStatus())
            .build();
    }
}
