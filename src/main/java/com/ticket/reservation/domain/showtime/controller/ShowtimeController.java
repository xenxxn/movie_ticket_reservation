package com.ticket.reservation.domain.showtime.controller;

import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.service.ShowtimeService;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @DeleteMapping("/{movieId}/{theaterId}")
  public ResponseEntity<String> deleteAllShowtime(@PathVariable Long movieId, @PathVariable Long theaterId) {
    showtimeService.deleteAllShowtime(movieId, theaterId);
    return ResponseEntity.ok("모든 상영회차가 삭제되었습니다.");
  }

  @DeleteMapping("/{movieId}/{theaterId}/{showtimeId}")
  public ResponseEntity<String> deleteSpecificShowtime(@PathVariable Long movieId, @PathVariable Long theaterId, @PathVariable Long showtimeId) {
    showtimeService.deleteSpecificShowtime(movieId, theaterId, showtimeId);
    return ResponseEntity.ok("해당 상영회차가 삭제되었습니다.");
  }
}
