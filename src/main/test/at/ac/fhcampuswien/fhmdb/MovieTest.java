package at.ac.fhcampuswien.fhmdb;//package at.ac.fhcampuswien.fhmdb.models;
//
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MovieTest {

    @Test
    public void testInitializeMovies() {
        //given

        // when initialise movies
        List<Movie> movies = Movie.initializeMovies();

        // then we expect 8 movies
        assertEquals(8, movies.size(), "Die Anzahl der Filme sollte 8 sein.");

    }
    @Test
    public void testMovieToString() {
        // GIVEN: Ein Movie-Objekt mit einem Titel, einer Beschreibung und Genres
        Movie movie = new Movie("Test Movie", "A test movie description", List.of(Genre.ACTION, Genre.DRAMA));

        // WHEN: Wir rufen die toString() Methode auf
        String movieString = movie.toString();

        // THEN: Wir erwarten, dass die toString-Methode die korrekte Formatierung zurückgibt
        String expectedString = "\nTest Movie\nA test movie description\nGenres: ACTION, DRAMA";
        assertEquals(expectedString, movieString, "Die toString-Ausgabe ist nicht korrekt.");
    }
}