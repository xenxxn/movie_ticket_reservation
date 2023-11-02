package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import java.util.List;
import java.util.Optional;
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

    public MovieDto addMovie(MovieInput movieInput) {
        Movie movie = MovieInput.toEntity(movieInput);
        movie = movieRepository.save(movie);
        return MovieDto.fromEntity(movie);
    }

    public Movie searchMovie(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("검색 결과가 없습니다."));
    }

    public List<Movie> searchMovieList(String title) {
        List<Movie> searchResults = movieRepository.findByTitleContaining(title);
        if (searchResults.isEmpty()) {
            throw new RuntimeException("검색 결과가 없습니다2.");
        }
        return searchResults;
    }


}
