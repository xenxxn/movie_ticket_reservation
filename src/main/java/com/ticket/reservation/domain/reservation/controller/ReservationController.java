package com.ticket.reservation.domain.reservation.controller;

import com.ticket.reservation.domain.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
  private final ReservationService reservationService;
}
