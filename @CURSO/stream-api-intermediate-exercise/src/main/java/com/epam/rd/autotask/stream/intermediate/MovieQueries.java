package com.epam.rd.autotask.stream.intermediate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.rd.autotask.stream.intermediate.model.Genre;
import com.epam.rd.autotask.stream.intermediate.model.Movie;
import com.epam.rd.autotask.stream.intermediate.model.Person;

public class MovieQueries {
    private final List<Movie> movies;

    public MovieQueries(List<Movie> movies) {
        if (movies == null) {
            throw new IllegalArgumentException("Movies list cannot be null");
        }
        this.movies = movies;
    }

    public boolean checkGenreOfAllMovies(Genre genre) {
        return movies.stream().allMatch(movie -> movie.getGenres().contains(genre));
    }

    public boolean checkGenreOfAnyMovies(Genre genre) {
        return movies.stream().anyMatch(movie -> movie.getGenres().contains(genre));
    }

    public boolean checkMovieIsDirectedBy(Person person) {
        return movies.stream().anyMatch(movie -> movie.getDirectors().contains(person));
    }

    public int calculateTotalLength() {
        return movies.stream().mapToInt(Movie::getLength).sum();
    }

    public long moviesLongerThan(int minutes) {
        return movies.stream().filter(movie -> movie.getLength() > minutes).count();
    }

    public List<Person> listOfWriters() {
        return movies.stream()
                .flatMap(movie -> movie.getWriters().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public String[] movieTitlesWrittenBy(Person person) {
        return movies.stream()
                .filter(movie -> movie.getWriters().contains(person))
                .map(Movie::getTitle)
                .toArray(String[]::new);
    }

    public List<Integer> listOfLength() {
        return movies.stream()
                .map(Movie::getLength)
                .collect(Collectors.toList());
    }

    public Movie longestMovie() {
        return movies.stream()
                .max(Comparator.comparingInt(Movie::getLength))
                .orElseThrow(() -> new IllegalArgumentException("Movies list is empty"));
    }

    public Movie oldestMovie() {
        return movies.stream()
                .min(Comparator.comparingInt(Movie::getReleaseYear))
                .orElseThrow(() -> new IllegalArgumentException("Movies list is empty"));
    }

    public List<Movie> sortedListOfMoviesBasedOnReleaseYear() {
        return movies.stream()
                .sorted(Comparator.comparingInt(Movie::getReleaseYear))
                .collect(Collectors.toList());
    }

    public List<Movie> sortedListOfMoviesBasedOnTheDateOfBirthOfOldestDirectorsOfMovies() {
        return movies.stream()
                .sorted(Comparator.comparing(movie -> movie.getDirectors().stream()
                        .min(Comparator.comparing(Person::getDateOfBirth))
                        .orElseThrow(() -> new IllegalArgumentException("No directors found"))
                        .getDateOfBirth()))
                .collect(Collectors.toList());
    }

    public List<Movie> moviesReleasedEarlierThan(int releaseYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() <= releaseYear) // Cambiado de < a <=
                .collect(Collectors.toList());
    }
}