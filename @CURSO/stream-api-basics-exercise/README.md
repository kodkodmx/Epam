# Stream API basics

The purpose of this exercise is to train you in usage of Java Stream API in basic level.

Estimated workload of this exercise is _30 minutes_.

### Description

MovieQueries class is responsible to perform different queries on a list of movie titles.
Please implement `MovieQueries` constructor and methods according to the following method definitions. 
Please do not change method names, arguments, or return type of the methods:

| Method name                                    | Description                                                                                            |
|------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| `getNumberOfMovies`                            | Count the elements in the movie list.                                                                  |
| `getNumberOfMoviesThatStartsWith`              | Count the elements that start with given parameter.                                                    |
| `getNumberOfMoviesThatStartsWithAndEndsWith`   | Count the elements that start and end with the given parameters.                                   |
| `getLengthOfTitleOfMovies`                     | Count the length of all movie titles.                                                                  |
| `getNumberOfLettersInShortestTitle`            | Return the length of the shortest movie title.<br/>Throw `IllegalArgumentException` if the list is empty. |
| `getFirstTitleThatContainsThreeWords`          | Return the first movie title that contains three words.                                                |
| `getFirstFourTitlesThatContainAtLeastTwoWords` | Return the first four (and only four) movie title that contains at least 2 words.                      |
| `printAllTitleToConsole`                       | Print all movie titles to the console.                                                                 |

`MovieQueries` constructor must throw `IllegalArgumentException` if the parameter is null.

In this exercise use Stream API, do not implement `for` loop, `while` loop or any other imperative approach.

### Examples

Having the following test data:

```
"Terminator", "Blade Runner", "Star Wars", "Indiana Jones", "Friends",
"The Magnificent Seven", "The Dark Knight", "The Lord of the Rings: The Return of the King",
"Pulp Fiction"
```

- `getNumberOfMoviesThatStartsWith` called with parameter `"The"`, returns 3.
- `getFirstTitleThatContainsThreeWords` returns `"The Magnificent Seven"`

### The Application

The `Application` class is already implemented, which delegates
the querying task to `MovieQueries` class.
