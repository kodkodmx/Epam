# Stream API intermediate

The purpose of this exercise is to practice Java Stream API at intermediate level.
The data model is similar to the previous exercise, movie. In this exercise the model is based on movies, but more complex.

Estimated workload of this exercise is _90 minutes_.


## Movie Data Model

The stream operations are defined on movie data. There are two domain model classes: `Movie` and `Person`.
`Person` can be the director, writer or cast of the movie..

Person example:

```
name: Arnold Schwarzenegger
date of birth: July 30, 1947
biography: With an almost unpronounceable surname and a thick Austrian accent
```

Movie example:

```
title: The Terminator
description: A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine.
genres: ACTION, SCI_FI
length: 107 (minutes)
release year: 1984
directors: James Cameron
writers: James Cameron, Gale Anne Hurd, William Wisher
casts: Arnold Schwarzenegger, Linda Hamilton, Michael Biehn
```
Note: directors, writers and casts fields are collections that hold Person reference.


## Description

`MovieQueries` class is responsible to perform different queries on a list of movies.
Please implement `MovieQueries` constructor and methods according to the following method definitions.
Please do not change method names, arguments, or return type of the methods:

| Method name                                                        | Description                                                                                     |
|--------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| `checkGenreOfAllMovies`                                            | Check that all the movies in the list belongs to the given genre.                               |
| `checkGenreOfAnyMovies`                                            | Check that at least one movie in the list belongs to the given genre.                           |
| `checkMovieIsDirectedBy`                                           | Check that at least one movie in the list is directed by the given person.                      |
| `calculateTotalLength`                                             | Calculate the total length of all the movies in the list.                                       |
| `moviesLongerThan`                                                 | Count the movies that are longer then the given parameter.                                      |
| `listOfWriters`                                                    | Return the writers of all movies. One writer appear only once in the list.                      |
| `movieTitlesWrittenBy`                                             | Return the titles of movies that are written by the given person.                               |
| `listOfLength`                                                     | Return a list of the length of movies.                                                          |
| `longestMovie`                                                     | Find the longest movie.<br/>Throws `IllegalArgumentException` if the movies list is empty.      |
| `oldestMovie`                                                      | Find the oldest movie.<br/>Throws `IllegalArgumentException` if the movies list is empty.       |
| `sortedListOfMoviesBasedOnReleaseYear`                             | Return a sorted list of movies based on their release year.                                     |
| `sortedListOfMoviesBasedOnTheDateOfBirthOfOldestDirectorsOfMovies` | Return a sorted list of movies based on the date of birth of the oldest director of each movie. |
| `moviesReleasedEarlierThan`                                        | Return a list of all the movies which are released earlier than the given year (inclusively).   |

`MovieQueries` constructor must throw `IllegalArgumentException` if the passed parameter is null.

In `MovieQueries` implementation you can expect that the `Movie` and related `Person` objects passed as arguments are all valid,
each field is populated with non-null values. No need to validate them.

Use only Java Stream API.


## Examples

Having the following movies:

```
The Terminator
genres: ACTION, SCI_FI

Blade Runner
genres: ACTION, SCI_FI, DRAMA

Indiana Jones
genres: ACTION, ADVENTURE
```

- `checkGenreOfAllMovies(Genre.SCI_FI)` returns false
- `checkGenreOfAnyMovies(Genre.SCI_FI)` returns true


## The Application

The `Application` class is already implemented, which delegates the querying task to `MovieQuery` class.
You can find the models in `model` package.
`MovieDataStore` builds test data and provide them to the application.
Classes in `builder` package helps to create models.
