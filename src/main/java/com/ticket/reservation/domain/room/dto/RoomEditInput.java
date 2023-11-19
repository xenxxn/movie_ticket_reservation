package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEditInput {
  private Long id;
  private Long theaterId;
  private Long showtimeId;
  private Long seatId;
  private String name;

  public static Room toEntity(RoomEditInput roomEditInput) {
    Theater theater = Theater.builder().id(roomEditInput.theaterId).build();
    return Room.builder()
        .id(roomEditInput.getId())
        .theater(theater)
        .name(roomEditInput.getName())
        .build();
  }
}
