package com.ticket.reservation.domain.showtime.service;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.movie.repository.MovieRepository;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import com.ticket.reservation.domain.showtime.dto.ShowtimeEditInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeOutput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final RoomRepository roomRepository;

  @Transactional
  public Showtime addShowtime(ShowtimeInput showtimeInput) {
    Showtime showtime = ShowtimeInput.toEntity(showtimeInput);
    validateShowtimeTimes(showtime);
    validateMovie(showtime.getMovie().getId());
    validateRoom(showtime.getRoom().getId());
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
    Showtime showtime = ShowtimeEditInput.toEntity(showtimeEditInput);
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
      throw new RuntimeException("상영 시작 시간은 종료 시간보다 이전이어야 합니다.");
    }
  }

  public Movie validateMovie(Long movieId) {
    return movieRepository.findById(movieId)
        .orElseThrow(() -> new NoResultException("해당 영화는 존재하지 않습니다."));
  }

  public Room validateRoom(Long roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow(() -> new NoResultException("해당 상영관은 존재하지 않습니다."));
  }

  public Showtime validateShowtime(Long showtimeId) {
    return showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new NoResultException("해당 상영회차는 존재하지 않습니다."));
  }
}
