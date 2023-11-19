package com.ticket.reservation.domain.showtime.entity;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.showtime.dto.ShowtimeEditInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SHOWTIME")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHOWTIME_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "MOVIE_ID")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "ROOM_ID")
  private Room room;

  @Column(name = "START_TIME")
  private LocalDateTime startTime;

  @Column(name = "END_TIME")
  private LocalDateTime endTime;

  @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Seat> seats = new ArrayList<>();

  @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Reservation> reservations = new ArrayList<>();

  public void setShowtime(Movie movie, Room room) {
    this.movie = movie;
    this.room = room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public static Showtime toEntityFromOutput(ShowtimeOutput showtimeOutput) {
    return Showtime.builder()
        .id(showtimeOutput.getShowtimeId())
        .movie(Movie.builder()
            .id(showtimeOutput.getMovieId())
            .build())
        .room(Room.builder()
            .id(showtimeOutput.getRoomId())
            .build())
        .startTime(showtimeOutput.getStartTime())
        .endTime(showtimeOutput.getEndTime())
        .build();
  }


  public static Showtime toEntityFromInput(ShowtimeInput showtimeInput) {
    Movie movie = Movie.builder().id(showtimeInput.getMovieId()).build();
    Room room = Room.builder().id(showtimeInput.getRoomId()).build();

    return Showtime.builder()
        .startTime(showtimeInput.getStartTime())
        .endTime(showtimeInput.getEndTime())
        .movie(movie)
        .room(room)
        .build();
  }

  public static Showtime toEntityFromEditInput(ShowtimeEditInput showtimeEditInput) {
    Movie movie = Movie.builder()
        .id(showtimeEditInput.getMovieId())
        .build();
    Room room = Room.builder()
        .id(showtimeEditInput.getRoomId())
        .build();

    return Showtime.builder()
        .id(showtimeEditInput.getId())
        .movie(movie)
        .room(room)
        .startTime(showtimeEditInput.getStartTime())
        .endTime(showtimeEditInput.getEndTime())
        .build();
  }

}
