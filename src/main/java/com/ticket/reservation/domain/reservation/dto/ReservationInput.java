package com.ticket.reservation.domain.reservation.dto;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationInput {
  private Long reservationId;
  private Long showtimeId;
  private Long seatId;
  private ReservationStatus reservationStatus;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static ReservationInput toInputTest (ReservationDto reservationDto) {
    Showtime showtime = Showtime.builder()
        .id(reservationDto.getReservationId())
        .startTime(reservationDto.getStartTime())
        .endTime(reservationDto.getEndTime())
        .build();
    System.out.println("getStartTime = " + showtime.getStartTime());

    return ReservationInput.builder()
        .reservationId(reservationDto.getReservationId())
        .showtimeId(reservationDto.getShowtimeId())
        .seatId(reservationDto.getSeatId())
        .reservationStatus(reservationDto.getReservationStatus())
        .startTime(reservationDto.getStartTime())
        .endTime(reservationDto.getEndTime())
        .build();
  }

}
