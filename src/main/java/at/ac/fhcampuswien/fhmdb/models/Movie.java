package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    // TODO add more properties here

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres.toString();
    }

    @Override
    public String toString() {
        return "\n" + title + "\n" + description + "\nGenres: " + genresToString();
    }

    private String genresToString() {
        return genres == null || genres.isEmpty()
                ? "No genres available"
                : String.join(", ", genres.stream().map(Enum::name).toList());

    }

        public static List<Movie> initializeMovies() {
            List<Movie> movies = new ArrayList<>();

            movies.add(new Movie("Inception",
                    "A thief who enters the dreams of others to steal their secrets must plant an idea in a target’s subconscious.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            // TODO add some dummy data here

            return movies;
        }

    }



