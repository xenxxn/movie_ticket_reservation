package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RoomInput {
  private String name;

  public static Room toEntity(RoomInput roomInput) {
    return Room.builder()
        .name(roomInput.getName())
        .build();
  }
}
