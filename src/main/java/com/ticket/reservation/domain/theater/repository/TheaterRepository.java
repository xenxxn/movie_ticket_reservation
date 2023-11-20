package com.ticket.reservation.domain.theater.repository;

import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

  List<TheaterDto> findByNameContaining(String name);

  Theater findByName(String name);

}
