package com.ticket.reservation.domain.room.dto;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.theater.entity.Theater;
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
  private String theaterName;
  private String name;
  private int totalSeat;

  public static RoomDto fromEntity(Room room) {
    Theater theater = room.getTheater();
    return RoomDto.builder()
        .id(room.getId())
        .theaterId(room.getTheater().getId())
        .theaterName(theater.getName())
        .name(room.getName())
        .totalSeat(room.getTotalSeat())
        .build();
  }

  public static List<RoomDto> toResponseList(List<Room> rooms) {
    return rooms.stream()
        .map(RoomDto::fromEntity)
        .collect(Collectors.toList());
  }
}
