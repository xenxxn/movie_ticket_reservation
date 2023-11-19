package com.ticket.reservation.domain.reservation.entity;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.dto.ReservationDto;
import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.reservation.dto.ReservationOutput;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;

import java.time.LocalDateTime;
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

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setReservationSeatStatus(SeatStatus status) {
        if (this.seat != null) {
            this.seat.setStatus(status);
        }
    }



    public static Reservation toEntityFromDto(ReservationDto reservationDto) {
        Showtime showtime = Showtime.builder()
            .id(reservationDto.getShowtimeId())
            .startTime(reservationDto.getStartTime())
            .endTime(reservationDto.getEndTime())
            .build();
        Seat seat = Seat.builder().id(reservationDto.getSeatId()).build();
        LocalDateTime startTime = showtime.getStartTime();
        LocalDateTime endTime = showtime.getEndTime();
        System.out.println("endTime = " + endTime);
        return Reservation.builder()
            .id(reservationDto.getReservationId())
            .showtime(showtime)
            .seat(seat)
            .reservationStatus(reservationDto.getReservationStatus())
            .startTime(startTime)
            .endTime(endTime)
            .build();
    }

    public static Reservation toEntityFromInput(ReservationInput reservationInput) {
        Showtime showtime = Showtime.builder()
            .id(reservationInput.getShowtimeId())
            .startTime(reservationInput.getStartTime())
            .endTime(reservationInput.getEndTime())
            .build();
        System.out.println("showtime = " + showtime.getId());
        System.out.println("showtime.getStartTime = " + showtime.getStartTime());

        Seat seat = Seat.builder().id(reservationInput.getSeatId()).build();
        return Reservation.builder()
            .id(reservationInput.getReservationId())
            .showtime(showtime)
            .seat(seat)
            .reservationStatus(ReservationStatus.UNUSED)
            .startTime(reservationInput.getStartTime())
            .endTime(reservationInput.getEndTime())
            .build();
    }

}
