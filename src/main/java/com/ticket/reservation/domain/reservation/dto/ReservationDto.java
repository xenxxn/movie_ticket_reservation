package com.ticket.reservation.domain.reservation.dto;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReservationDto {
  private Long reservationId;
  private Long showtimeId;
  private Long seatId;
  private String theaterName;
  private String roomName;
  private ReservationStatus reservationStatus;
  private String seatRow;
  private int seatNumber;
  private String movieTitle;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static ReservationDto fromEntity(Reservation reservation) {
    Showtime showtime = reservation.getShowtime();
    Seat seat = reservation.getSeat();
    Theater theater = reservation.getSeat().getRoom().getTheater();
    Room room = reservation.getSeat().getRoom();
    return ReservationDto.builder()
        .reservationId(reservation.getId())
        .showtimeId(showtime.getId())
        .seatId(seat.getId())
        .theaterName(theater.getName())
        .roomName(room.getName())
        .reservationStatus(reservation.getReservationStatus())
        .seatRow(seat.getRow())
        .seatNumber(seat.getNumber())
        .movieTitle(showtime.getMovie().getTitle())
        .startTime(showtime.getStartTime())
        .endTime(showtime.getEndTime())
        .build();
  }


  public static List<ReservationDto> toResponseList(List<Reservation> reservations) {
    return reservations.stream()
        .map(ReservationDto::fromEntity)
        .collect(Collectors.toList());
  }
}
