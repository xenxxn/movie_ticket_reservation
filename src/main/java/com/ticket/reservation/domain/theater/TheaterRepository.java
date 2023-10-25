package com.ticket.reservation.domain.theater;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
  List<Theater> findByNameContaining (String name);

  Optional<Theater> searchByLocation (String location);


}
