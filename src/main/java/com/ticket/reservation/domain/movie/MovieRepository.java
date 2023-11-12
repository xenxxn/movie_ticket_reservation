package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

  boolean existsByIdAndTitle(Long id, String title);
  Movie findByTitle(String title);
  List<MovieDto> findByTitleContaining(String title);

  //TODO : 추후 List -> Page 로 변경할 때 사용 예정
  //Page<Movie> findByTitleContaining(String title);
}
