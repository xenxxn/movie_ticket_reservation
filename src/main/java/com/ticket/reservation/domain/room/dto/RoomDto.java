package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
  private Long id;
  private Long theaterId;
  private String name;

  public static RoomDto fromEntity(Room room) {
    return RoomDto.builder()
        .id(room.getId())
        .theaterId(room.getTheaterId())
        .name(room.getName())
        .build();
  }

  public static Room toEntity(RoomDto roomDto) {
    return Room.builder()
        .id(roomDto.getId())
        .build();
  }

  public static List<RoomDto> toResponseList(List<Room> rooms) {
    return rooms.stream()
        .map(RoomDto::fromEntity)
        .collect(Collectors.toList());
  }
}
