package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.theater.entity.Theater;
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
  private String name;

  public static Room toEntity(RoomEditInput roomEditInput) {
    Theater theater = Theater.builder().id(roomEditInput.theaterId).build();

    return Room.builder()
        .id(roomEditInput.getId())
        .theater(theater)// 상영관 ID를 사용하여 Theater 엔티티를 생성
        .name(roomEditInput.getName())
        .build();
  }
}
