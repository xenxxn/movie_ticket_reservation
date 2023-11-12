package com.ticket.reservation.domain.showtime;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  //restful api 를 구현하기 위해 mapping url 수정 및 반환타입 정확하게 하기.
  @PostMapping("/{id}")
  public ShowtimeDto showtimeAdd(@RequestBody ShowtimeDto request, @PathVariable Long id) {
    return showtimeService.createShowtime(Showtime.builder().build());
  }
}
