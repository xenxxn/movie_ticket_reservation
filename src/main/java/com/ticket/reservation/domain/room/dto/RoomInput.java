package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.theater.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RoomInput {
  private Long theaterId;
  private String name;
  private int totalSeat;

  public static Room toEntity(RoomInput roomInput) {
    Theater theater = Theater.builder()
        .id(roomInput.getTheaterId())
        .build();
    return Room.builder()
        .theater(theater)
        .name(roomInput.getName())
        .totalSeat(roomInput.getTotalSeat())
        .build();
  }
}
