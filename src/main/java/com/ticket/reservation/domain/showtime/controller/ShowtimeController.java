package com.ticket.reservation.domain.showtime.controller;

import com.ticket.reservation.domain.showtime.dto.ShowtimeEditInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.dto.ShowtimeOutput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.service.ShowtimeService;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showtime")
@AllArgsConstructor
public class ShowtimeController {
  private final ShowtimeService showtimeService;

  @PostMapping()
  public ResponseEntity<ShowtimeDto> addShowtime(@RequestBody ShowtimeInput showtimeInput) {
    Showtime makeShowtime = showtimeService.addShowtime(showtimeInput);
    ShowtimeDto showtimeDto = ShowtimeDto.fromEntity(makeShowtime);
    return ResponseEntity.ok(showtimeDto);
  }

  @GetMapping("/movies/{movieId}")
  public ResponseEntity<List<ShowtimeOutput>> searchShowtimeListByMovie(@PathVariable Long movieId) {
    return ResponseEntity.ok(showtimeService.searchShowtimeListByMovie(movieId));
  }

  @GetMapping("/theaters/{roomId}")
  public ResponseEntity<List<ShowtimeOutput>> searchShowtimeListByRoom(@PathVariable Long roomId) {
    return ResponseEntity.ok(showtimeService.searchShowtimeListByRoom(roomId));
  }

  @GetMapping("/movies-theaters/{movieId}/{roomId}")
  public ResponseEntity<List<ShowtimeOutput>> searchShowtimeByMovieAndRoom(@PathVariable Long movieId, @PathVariable Long roomId) {
    return ResponseEntity.ok(showtimeService.searchShowtimeByMovieAndRoom(movieId, roomId));
  }

  @GetMapping("/detail/{showtimeId}")
  public ResponseEntity<ShowtimeOutput> searchShowtimeDetail(@PathVariable Long showtimeId) {
    return ResponseEntity.ok(showtimeService.searchShowtimeDetail(showtimeId));
  }

  @PutMapping("/modification/{showtimeId}")
  public ResponseEntity<ShowtimeDto> editShowtime(@PathVariable Long showtimeId, @RequestBody
      ShowtimeEditInput showtimeEditInput) {
    return ResponseEntity.ok(showtimeService.editShowtime(showtimeEditInput));
  }

  @DeleteMapping("/{movieId}/{roomId}")
  public ResponseEntity<String> deleteAllShowtime(@PathVariable Long movieId, @PathVariable Long roomId) {
    showtimeService.deleteAllShowtime(movieId, roomId);
    return ResponseEntity.ok("모든 상영회차가 삭제되었습니다.");
  }

  @DeleteMapping("/{movieId}/{roomId}/{showtimeId}")
  public ResponseEntity<String> deleteSpecificShowtime(@PathVariable Long movieId, @PathVariable Long roomId, @PathVariable Long showtimeId) {
    showtimeService.deleteSpecificShowtime(movieId, roomId, showtimeId);
    return ResponseEntity.ok("해당 상영회차가 삭제되었습니다.");
  }

}
