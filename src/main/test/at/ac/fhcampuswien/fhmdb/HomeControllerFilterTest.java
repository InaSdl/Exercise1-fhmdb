package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerFilterTest {

    private HomeController controller;

    @BeforeEach
    public void setup() {
        controller = new HomeController();
        // Leere und setze die ursprüngliche Filmliste neu
        controller.movies.clear();
    }

    @Test
    public void testFilterByQueryOnly() {
        // Arrange: Erstelle Testfilme
        Movie movie1 = new Movie("The Great Adventure", "An epic journey", Arrays.asList(Genre.ADVENTURE));
        Movie movie2 = new Movie("Romantic Comedy", "Love and laughter", Arrays.asList(Genre.COMEDY, Genre.ROMANCE));
        Movie movie3 = new Movie("Horror Night", "Spooky thriller", Arrays.asList(Genre.HORROR, Genre.THRILLER));
        controller.movies.addAll(Arrays.asList(movie1, movie2, movie3));

        // Act: Filtere nach dem Query "adventure" (case-insensitive)
        ObservableList<Movie> result = controller.filterMovies("adventure", null);

        // Assert: Nur movie1 enthält "adventure" im Titel oder in der Beschreibung
        assertEquals(1, result.size());
        assertTrue(result.contains(movie1));
    }

    @Test
    public void testFilterByGenreOnly() {
        // Arrange: Erstelle Testfilme
        Movie movie1 = new Movie("Movie One", "Description", Arrays.asList(Genre.ACTION));
        Movie movie2 = new Movie("Movie Two", "Description", Arrays.asList(Genre.DRAMA));
        Movie movie3 = new Movie("Movie Three", "Description", Arrays.asList(Genre.ACTION, Genre.DRAMA));
        controller.movies.addAll(Arrays.asList(movie1, movie2, movie3));

        // Act: Filtere nach Genre ACTION
        ObservableList<Movie> result = controller.filterMovies(null, Genre.ACTION);

        // Assert: movie1 und movie3 sollten zurückgegeben werden
        assertEquals(2, result.size());
        assertTrue(result.contains(movie1));
        assertTrue(result.contains(movie3));
    }

    @Test
    public void testFilterByQueryAndGenre() {
        // Arrange: Erstelle Testfilme
        Movie movie1 = new Movie("Action Blast", "An action-packed thriller", Arrays.asList(Genre.ACTION));
        Movie movie2 = new Movie("Romantic Action", "Love in the battlefield", Arrays.asList(Genre.ACTION, Genre.ROMANCE));
        Movie movie3 = new Movie("Dramatic Love", "A drama film", Arrays.asList(Genre.DRAMA, Genre.ROMANCE));
        controller.movies.addAll(Arrays.asList(movie1, movie2, movie3));

        // Act: Filtere nach Query "action" und Genre ROMANCE
        ObservableList<Movie> result = controller.filterMovies("action", Genre.ROMANCE);

        // Assert: Nur movie2 sollte beide Kriterien erfüllen
        assertEquals(1, result.size());
        assertTrue(result.contains(movie2));
    }

    @Test
    public void testFilterWithNoCriteria() {
        // Arrange: Erstelle Testfilme
        Movie movie1 = new Movie("Movie One", "Description", Arrays.asList(Genre.ACTION));
        Movie movie2 = new Movie("Movie Two", "Description", Arrays.asList(Genre.DRAMA));
        controller.movies.addAll(Arrays.asList(movie1, movie2));

        // Act: Kein Query und kein Genre ausgewählt
        ObservableList<Movie> result = controller.filterMovies("", null);

        // Assert: Alle Filme sollen zurückgegeben werden
        assertEquals(2, result.size());
    }
}
