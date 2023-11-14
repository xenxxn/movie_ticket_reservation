package com.ticket.reservation.domain.seat.dto;

import com.ticket.reservation.domain.room.Room;
import com.ticket.reservation.domain.seat.Seat;
import com.ticket.reservation.domain.seat.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatEditInput {
  private Long id;
  private Long roomId;
  private String row;
  private int number;
  private SeatStatus status;

  public static Seat toEntity(SeatEditInput seatEditInput) {
    Room room = Room.builder().id(seatEditInput.getRoomId()).build();
    return Seat.builder()
        .id(seatEditInput.getId())
        .room(room)
        .row(seatEditInput.getRow())
        .number(seatEditInput.getNumber())
        .status(seatEditInput.getStatus())
        .build();
  }
}
