package com.ticket.reservation.domain.reservation.repository;

import com.ticket.reservation.domain.reservation.ReservationStatus;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.seat.SeatStatus;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  List<Reservation> findReservationsByShowtime(Showtime showtime);

  List<Reservation> findByEndTimeBeforeAndSeatStatus(LocalDateTime endTime, SeatStatus status);

}
