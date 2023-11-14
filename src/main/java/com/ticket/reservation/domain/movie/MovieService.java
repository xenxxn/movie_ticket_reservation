package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import com.ticket.reservation.domain.movie.dto.MovieOutput;
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
        Movie movie = MovieInput.toEntity(movieInput);
        return movieRepository.save(movie);
    }

    public MovieOutput searchMovie(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if (movie == null){
            throw new NoResultException("검색 결과가 없습니다.");
        }
        MovieDto movieDto = MovieDto.fromEntity(movie);
        return MovieOutput.toResponse(movieDto);
    }

    public List<MovieOutput> searchMovieList(String title) {
        List<MovieDto> movies = movieRepository.findByTitleContaining(title);
        if (movies.isEmpty()){
            throw new NoResultException("검색 결과가 없습니다.");
        }
        return MovieOutput.toResponse(movies);
    }


    @Transactional
    public MovieDto editMovie(Long movieId, MovieEditInput movieEditInput) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new NoResultException("해당 영화를 찾을 수 없습니다."));
        movie.updateMovie(movieEditInput);
        movieRepository.save(movie);
        return MovieDto.fromEntity(movie);
    }
}
