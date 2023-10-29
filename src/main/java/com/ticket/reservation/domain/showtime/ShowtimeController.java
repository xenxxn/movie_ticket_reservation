package com.ticket.reservation.domain.showtime;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showtime")
@AllArgsConstructor
public class ShowtimeController {
  private final ShowtimeService showtimeService;

  @PostMapping("/add")
  public ResponseEntity<?> showtimeAdd(@RequestBody Showtime request) {
    Showtime showtime = showtimeService.createShowtime(request);
    return ResponseEntity.ok(showtime);
  }
}
