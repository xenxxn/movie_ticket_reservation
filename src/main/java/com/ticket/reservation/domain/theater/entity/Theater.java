package com.ticket.reservation.domain.theater.entity;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THEATER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theater {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "THEATER_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "LOCATION")
  private String location;

  @Column(name = "OPEN_TIME")
  private LocalTime openTime;

  @Column(name = "CLOSE_TIME")
  private LocalTime closeTime;

  @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
  private List<Room> rooms = new ArrayList<>();

  public static Theater toEntityFromInput(TheaterInput theaterInput) {
    return Theater.builder()
        .name(theaterInput.getName())
        .location(theaterInput.getLocation())
        .openTime(theaterInput.getOpenTime())
        .closeTime(theaterInput.getCloseTime())
        .build();
  }
}
