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
        return genres == null || genres.isEmpty()
                ? "No genres available"
                : String.join(", ", genres.stream().map(Enum::name).toList());
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

            movies.add(new Movie("Inception",
                    "A thief who enters the dreams of others to steal their secrets must plant an idea in a target’s subconscious.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            movies.add(new Movie("Interstellar",
                    "A group of explorers embarks on a space journey to find a new habitable planet as Earth faces extinction.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA, Genre.THRILLER)));

            movies.add(new Movie("The Matrix",
                    "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER)));

            movies.add(new Movie("Shutter Island",
                    "A U.S. Marshal investigates the disappearance of a murderer who escaped from a mental institution on a remote island.",
                    Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA)));

            movies.add(new Movie("Memento",
                    "A man with short-term memory loss attempts to solve his wife’s murder, using tattoos and Polaroid pictures to piece together the clues.",
                    Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA)));

            movies.add(new Movie("The Prestige",
                    "Two magicians engage in a bitter rivalry, using increasingly dangerous tricks and illusions to outdo each other.",
                    Arrays.asList(Genre.DRAMA, Genre.MYSTERY, Genre.THRILLER)));

            movies.add(new Movie("Ex Machina",
                    "A young programmer is selected to participate in a groundbreaking experiment in artificial intelligence by evaluating the human qualities of a highly advanced humanoid A.I.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.DRAMA)));

            movies.add(new Movie("Edge of Tomorrow",
                    "A public relations officer is caught in a time loop and must relive the same day repeatedly while fighting aliens in an intergalactic war.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER)));

            movies.add(new Movie("Looper",
                    "A hired gun in the future, who kills people sent back in time by criminal organizations, faces a moral dilemma when his future self is sent back for execution.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            movies.add(new Movie("Donnie Darko",
                    "A troubled teenager experiences disturbing visions of a man in a creepy rabbit costume, leading him to uncover a series of strange events involving time travel and reality distortion.",
                    Arrays.asList(Genre.DRAMA, Genre.SCIENCE_FICTION, Genre.THRILLER)));





            // TODO add some dummy data here

            return movies;
        }

    }



