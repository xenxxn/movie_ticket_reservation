package com.ticket.reservation.domain.seat.dto;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatInput {
  private Long roomId;
  private String row;
  private int number;
  private SeatStatus status;

  //TODO:Room Builder 외에 다른 방법이 없는지 찾아보기
  public static Seat toEntity(SeatInput seatInput) {
    return Seat.builder()
        .room(Room.builder()
            .id(seatInput.getRoomId())
            .build())
        .row(seatInput.getRow())
        .number(seatInput.getNumber())
        .status(SeatStatus.UNRESERVED)
        .build();
  }
}
