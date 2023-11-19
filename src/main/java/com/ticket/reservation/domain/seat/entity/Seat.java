package com.ticket.reservation.domain.seat.entity;

import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatEditInput;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import com.ticket.reservation.domain.seat.dto.SeatOutput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "SEAT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEAT_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ROOM_ID")
  private Room room;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "SHOWTIME_ID")
  private Showtime showtime;

  @Column(name = "ROW")
  private String row;

  @Column(name = "NUMBER")
  private int number;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private SeatStatus status;

  @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> reservations = new ArrayList<>();

  public void setSeat(Room room) {
    this.room = room;
  }

  public void updateSeat(SeatEditInput seatEditInput) {
    if (seatEditInput.getRow() != null) {
      this.row = seatEditInput.getRow();
    }
    if (seatEditInput.getNumber() > 0) {
      this.number = seatEditInput.getNumber();
    }
    if (seatEditInput.getStatus() != null) {
      this.status = seatEditInput.getStatus();
    }
    if (seatEditInput.getRoomId() != null) {
      this.room = Room.builder().id(seatEditInput.getRoomId()).build();
    }
  }

  public void setStatus(SeatStatus seatStatus) {
    this.status = seatStatus;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public static Seat toEntityFromDto(SeatDto seatDto) {
    return Seat.builder()
        .id(seatDto.getId())
        .row(seatDto.getRow())
        .number(seatDto.getNumber())
        .status(seatDto.getStatus())
        .build();
  }

  public static Seat toEntityFromOutput(SeatOutput seatOutput) {
    return Seat.builder()
        .row(seatOutput.getRow())
        .number(seatOutput.getNumber())
        .status(seatOutput.getStatus())
        .build();
  }

  public static Seat toEntityFromInput(SeatInput seatInput) {
    return Seat.builder()
        .room(Room.builder()
            .id(seatInput.getRoomId())
            .build())
        .row(seatInput.getRow())
        .number(seatInput.getNumber())
        .status(SeatStatus.UNRESERVED)
        .build();
  }

  public static Seat toEntityFromEditInput(SeatEditInput seatEditInput) {
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
