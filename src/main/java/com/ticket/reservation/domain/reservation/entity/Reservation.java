package com.ticket.reservation.domain.reservation.entity;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.time.LocalDateTime;
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
import javax.persistence.Table;
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


  public static Reservation toEntityFromInput(ReservationInput reservationInput) {
    Showtime showtime = Showtime.builder()
        .id(reservationInput.getShowtimeId())
        .startTime(reservationInput.getStartTime())
        .endTime(reservationInput.getEndTime())
        .build();

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
