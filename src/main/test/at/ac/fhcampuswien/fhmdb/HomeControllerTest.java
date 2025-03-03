package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class HomeControllerTest {
    private HomeController homeController;


    @BeforeEach
    void setUp() {
        this.homeController = new HomeController();
        ObservableList<Movie> observableMovies = this.homeController.getObservableMovies();
        observableMovies.addAll(Movie.initializeMovies());
    }

    @Test
    void Sort_Ascending() {
        // Given: Liste Observable Movies

        // When: Die Funktion Sort_Ascending wird aufgerufen um aufsteigend zu sortieren.
        this.homeController.sortAscending(this.homeController.getObservableMovies());

        // Then: Die Liste ist aufsteigend sortiert.
        ObservableList<Movie> observableMovies = this.homeController.getObservableMovies();
        for (int i = 0; i < observableMovies.size() - 1; i++) {
            assertTrue(observableMovies.get(i).getTitle().compareTo(observableMovies.get(i + 1).getTitle()) <= 0);
        }
    }


    @Test
    public void Sort_Descending() {
        //Given: Liste Observable Movies

        //When: Die Funktion Sort_Descending wird aufgerufen um absteigend zu sortieren.
        this.homeController.sortDescending(this.homeController.getObservableMovies());
        //Then: Die Liste ist absteigend sortiert.
        ObservableList<Movie> observableMovies = this.homeController.getObservableMovies();
        for (int i = 0; i < observableMovies.size() - 1; i++) {
            assertTrue(observableMovies.get(i).getTitle().compareTo(observableMovies.get(i + 1).getTitle()) >= 0);
        }
    }


    @Test
    void Filter_Science_Fiction_Movies() {
        // Given: Die Liste enthält gemischte Genres.

        // When: Es wird nach "SCIENCE_FICTION" gefiltert.
        this.homeController.filterMoviesByGenre(Genre.SCIENCE_FICTION);
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: Die Liste enthält nur Filme mit dem Genre SCIENCE_FICTION.
        assertEquals(8, filteredMovies.size()); // Erwartet, dass es 8 Filme mit SCIENCE_FICTION gibt.
        assertTrue(filteredMovies.stream().allMatch(movie -> movie.getGenres().contains(Genre.SCIENCE_FICTION)));
    }

    @Test
    void Filter_Clear_Genre() {
        // Given: Die Liste enthält MYSTERY-Filme.
        this.homeController.filterMoviesByGenre(Genre.MYSTERY);
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // When: Filter wird entfernt.
        this.homeController.filterMoviesByGenre(null);

        // Then: Die Liste enthält wieder alle Filme.
        assertEquals(27, filteredMovies.size()); // Erwartet, dass alle Filme angezeigt werden.
    }

    @Test
    void Search_For_Island() {
        // Given: Eine vollständige Liste.

        // When: Es wird nach "island" gesucht.
        this.homeController.searchMovies("island");
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: Die Liste enthält nur Filme mit dem Suchbegriff "island".
        assertEquals(1, filteredMovies.size()); // Erwartet, dass es einen Film gibt.
        assertTrue(filteredMovies.stream().allMatch(movie -> movie.getTitle() == "Shutter Island"));
    }

    @Test
    void Search_For_The() {
        // Given: Eine vollständige Liste.
        String query = "the";

        // When: Es wird nach "the" gesucht.
        this.homeController.searchMovies(query);
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: Die Liste enthält alle Filme mit "the".
        assertEquals(22, filteredMovies.size()); // Erwartet, dass es 9 Filme gibt.
        assertTrue(filteredMovies.stream().allMatch(movie -> movie.getTitle().contains
                (query) || movie.getDescription().contains(query)));
    }

    @Test
    void Search_For_No_Result() {
        // Given: Eine vollständige Liste.
        String query = "xylophon";

        // When: Es wird nach "xylophon" gesucht.
        this.homeController.searchMovies(query);
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: Die Liste enthält keine Ergebnisse.
        assertTrue(filteredMovies.isEmpty());

    }

    @Test
    void Search_For_Empty_Query() {
        // Given: Eine vollständige Liste.
        String query = "";

        // When: Im Suchfeld nichts angegeben ist.
        this.homeController.searchMovies(query);
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: Die Liste enthält alle Filme.
        assertEquals(27, filteredMovies.size());
    }

    @Test
    void Filter_By_Genre_And_SearchQuery() {
        // Given: the entire movie-list is already there
        // When: we filter by genre SCIENCE_FICTION and query "the".
        this.homeController.filterMovies(Genre.SCIENCE_FICTION, "the");
        ObservableList<Movie> filteredMovies = this.homeController.getObservableMovies();

        // Then: all movies must have genre "science_fiction" and in the title or description "the"
        // according to dummy list there should be 4 movies:
        assertEquals(6, filteredMovies.size(), "we expect 6 movies that meet the criteria");

        boolean allMatch = true;
        for (int i = 0; i < filteredMovies.size(); i++) {
            Movie movie = filteredMovies.get(i);

            // check if the movie has the genre SCIENCE_FICTION
            if (!movie.getGenres().contains(Genre.SCIENCE_FICTION)) {
                allMatch = false;
                break;
            }

            // convert title and description to lower case for case insensitive comparison
            String titleLower = movie.getTitle().toLowerCase();
            String descriptionLower = movie.getDescription().toLowerCase();

            // check if either title or description contains word "the"
            if (!(titleLower.contains("the") || descriptionLower.contains("the"))) {
                allMatch = false;
                break;
            }
        }

        assertTrue(allMatch, "All movies should have genre SCIENCE_FICTION and 'the' in title or description");
    }

}
