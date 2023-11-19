package com.ticket.reservation.domain.movie.service;

import com.ticket.reservation.domain.exception.CustomException;
import com.ticket.reservation.domain.exception.ErrorCode;
import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import com.ticket.reservation.domain.movie.dto.MovieOutput;
import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.movie.repository.MovieRepository;
import java.util.List;
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
        Movie movie = Movie.toEntityFromInput(movieInput);
        return movieRepository.save(movie);
    }

    public MovieOutput searchMovie(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if (movie == null){
            throw new CustomException(ErrorCode.NOT_EXISTS_MOVIE);
        }
        MovieDto movieDto = MovieDto.fromEntity(movie);
        return MovieOutput.toResponse(movieDto);
    }

    public List<MovieOutput> searchMovieList(String title) {
        List<MovieDto> movies = movieRepository.findByTitleContaining(title);
        if (movies.isEmpty()){
            throw new CustomException(ErrorCode.NOT_EXISTS_MOVIE);
        }
        return MovieOutput.toResponse(movies);
    }


    @Transactional
    public MovieDto editMovie(Long movieId, MovieEditInput movieEditInput) {
        Movie movie = validateMovie(movieId);
        movie.updateMovie(movieEditInput);
        movieRepository.save(movie);
        return MovieDto.fromEntity(movie);
    }

    @Transactional
    public void deleteMovie(Long movieId) {
        Movie movie = validateMovie(movieId);
        movieRepository.delete(movie);
    }

    public Movie validateMovie(Long movieId) {
        return movieRepository.findById(movieId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_MOVIE));
    }
}
