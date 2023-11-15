package com.ticket.reservation.domain.movie.repository;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.entity.Movie;
import java.util.List;
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
