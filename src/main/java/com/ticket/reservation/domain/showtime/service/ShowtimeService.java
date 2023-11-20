package com.ticket.reservation.domain.showtime.service;

import com.ticket.reservation.global.exception.CustomException;
import com.ticket.reservation.global.exception.ErrorCode;
import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.movie.repository.MovieRepository;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import com.ticket.reservation.domain.showtime.dto.ShowtimeEditInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeOutput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final RoomRepository roomRepository;
  private final SeatRepository seatRepository;
  private final String[] ROOM_ROM = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

  @Transactional
  public Showtime addShowtime(ShowtimeInput showtimeInput) {
    Showtime showtime = Showtime.toEntityFromInput(showtimeInput);
    validateShowtimeTimes(showtime);
    validateMovie(showtime.getMovie().getId());
    Room room = validateRoom(showtime.getRoom().getId());
    int seatSize = room.getTotalSeat();

    int numCount = 1;
    int rowCount = 0;

    for (int i = 0; i < seatSize; i++) {
      Seat seat = Seat.builder()
          .room(room)
          .row(ROOM_ROM[rowCount])
          .number(numCount)
          .status(SeatStatus.UNRESERVED)
          .showtime(showtime)
          .build();
      seatRepository.save(seat);

      if (numCount == 10) {
        numCount = 1;
        rowCount++;
      } else {
        numCount++;
      }
    }
    return showtimeRepository.save(showtime);
  }

  public List<ShowtimeOutput> searchShowtimeListByMovie(Long movieId) {
    validateMovie(movieId);
    List<Showtime> showtimeList = showtimeRepository.findAllByMovieId(movieId);
    List<ShowtimeDto> showtimeDtoList = ShowtimeDto.toResponseList(showtimeList);
    return ShowtimeOutput.toResponseList(showtimeDtoList);
  }

  public List<ShowtimeOutput> searchShowtimeListByRoom(Long roomId) {
    validateRoom(roomId);
    List<Showtime> showtimeList = showtimeRepository.findAllByRoomId(roomId);
    List<ShowtimeDto> showtimeDtoList = ShowtimeDto.toResponseList(showtimeList);
    return ShowtimeOutput.toResponseList(showtimeDtoList);
  }

  public List<ShowtimeOutput> searchShowtimeByMovieAndRoom(Long movieId, Long roomId) {
    validateMovie(movieId);
    validateRoom(roomId);
    List<Showtime> showtimeList = showtimeRepository.findAllByMovieIdAndRoomId(movieId, roomId);
    List<ShowtimeDto> showtimeDtoList = ShowtimeDto.toResponseList(showtimeList);
    return ShowtimeOutput.toResponseList(showtimeDtoList);
  }

  public ShowtimeOutput searchShowtimeDetail(Long showtimeId) {
    Showtime showtime = validateShowtime(showtimeId);
    ShowtimeDto showtimeDto = ShowtimeDto.fromEntity(showtime);
    return ShowtimeOutput.toResponse(showtimeDto);
  }

  @Transactional
  public ShowtimeDto editShowtime(ShowtimeEditInput showtimeEditInput) {
    Showtime showtime = Showtime.toEntityFromEditInput(showtimeEditInput);
    validateMovie(showtime.getMovie().getId());
    validateRoom(showtime.getRoom().getId());
    showtimeRepository.save(showtime);
    return ShowtimeDto.fromEntity(showtime);
  }


  @Transactional
  public void deleteSpecificShowtime(Long movieId, Long roomId, Long showtimeId) {
    Movie movie = validateMovie(movieId);
    Room room = validateRoom(roomId);
    Showtime showtime = validateShowtime(showtimeId);

    movie.getShowtimeList().remove(showtime);
    room.getShowtimeList().remove(showtime);
    movieRepository.save(movie);
    roomRepository.save(room);
  }

  @Transactional
  public void deleteAllShowtime(Long movieId, Long roomId) {
    Movie movie = validateMovie(movieId);
    Room room = validateRoom(roomId);

    List<Showtime> showtimeList = showtimeRepository.findAllByMovieIdAndRoomId(movieId, roomId);

    for (Showtime showtime : showtimeList) {
      showtime.setShowtime(null, null);
      showtimeRepository.save(showtime);
    }

    movie.getShowtimeList().clear();
    room.getShowtimeList().clear();
    movieRepository.save(movie);
    roomRepository.save(room);
  }

  private void validateShowtimeTimes(Showtime showtime) {
    LocalDateTime startTime = showtime.getStartTime();
    LocalDateTime endTime = showtime.getEndTime();
    if (startTime.isAfter(endTime)) {
      throw new CustomException(ErrorCode.INVALID_SHOWTIME_TIMINGS);
    }
  }

  public Movie validateMovie(Long movieId) {
    return movieRepository.findById(movieId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_MOVIE));
  }

  public Room validateRoom(Long roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ROOM));
  }

  public Showtime validateShowtime(Long showtimeId) {
    return showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_SHOWTIME));
  }
}
