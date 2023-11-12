package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public Movie addMovie(MovieInput movieInput) {
        Movie movie = MovieInput.toEntity(movieInput);
        return movieRepository.save(movie);
    }

    public Movie searchMovie(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("검색 결과가 없습니다."));
    }

    public List<MovieDto> searchMovieList(String title) {
        List<Movie> searchResults = movieRepository.findByTitleContaining(title);
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : searchResults) {
            MovieDto movieDto = MovieDto.fromEntity(movie);
            movieDtos.add(movieDto);
        }

        if (movieDtos.isEmpty()) {
            throw new NoResultException("영화 검색 결과가 없습니다.");
        }
        return movieDtos;
    }


    @Transactional
    public MovieDto editMovie(MovieEditInput movieEditInput) {
        movieRepository.findById(movieEditInput.getId())
            .orElseThrow(() -> new NoResultException("해당 영화는 존재하지 않습니다."));
        Movie editMovie = MovieEditInput.toEntity(movieEditInput);
        Movie saved = movieRepository.save(editMovie);
        return MovieDto.fromEntity(saved);
    }
}
