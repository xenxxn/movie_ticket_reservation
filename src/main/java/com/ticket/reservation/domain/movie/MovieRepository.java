package com.ticket.reservation.domain.movie;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

  boolean existsByIdAndTitle(Long id, String title);
  Optional<Movie> findByTitle(String title);

  List<Movie> findByTitleContaining(String title);
}
