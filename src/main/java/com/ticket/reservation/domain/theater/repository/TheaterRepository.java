package com.ticket.reservation.domain.theater.repository;

import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.dto.TheaterDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
  List<TheaterDto> findByNameContaining (String name);

  Theater findByName (String name);

  Optional<Theater> searchByLocation (String location);


}
