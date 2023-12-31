package com.ticket.reservation.domain.room.entity;

import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomEditInput;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.room.dto.RoomOutput;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROOM")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROOM_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "THEATER_ID")
  private Theater theater;

  @Column(name = "NAME")
  private String name;

  @Column(name = "TOTAL_SEAT")
  private int totalSeat;

  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  private List<Seat> seats = new ArrayList<>();

  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  private List<Showtime> showtimeList = new ArrayList<>();

  public void setRoom(Theater theater) {
    this.theater = theater;
  }

  public static Room toEntity(RoomDto roomDto) {
    return Room.builder()
        .id(roomDto.getId())
        .name(roomDto.getName())
        .totalSeat(roomDto.getTotalSeat())
        .build();
  }

  public static Room toEntityFromInput(RoomInput roomInput) {
    Theater theater = Theater.builder()
        .id(roomInput.getTheaterId())
        .build();
    return Room.builder()
        .theater(theater)
        .name(roomInput.getName())
        .totalSeat(roomInput.getTotalSeat())
        .build();
  }

  public static Room toEntityFromEditInput(RoomEditInput roomEditInput) {
    Theater theater = Theater.builder().id(roomEditInput.getTheaterId()).build();
    return Room.builder()
        .id(roomEditInput.getId())
        .theater(theater)
        .name(roomEditInput.getName())
        .totalSeat(roomEditInput.getTotalSeat())
        .build();
  }

}
