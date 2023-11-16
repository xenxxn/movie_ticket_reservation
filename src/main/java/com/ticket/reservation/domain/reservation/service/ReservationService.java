package com.ticket.reservation.domain.reservation.service;

import com.ticket.reservation.domain.reservation.repository.ReservationRepository;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final ShowtimeRepository showtimeRepository;
  private final SeatRepository seatRepository;
}
