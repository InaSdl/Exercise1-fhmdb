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

    @Test
    public void testSortAscending() {
        //Given:
        HomeController homeController = new HomeController();
        ObservableList<Movie> observableMovies = homeController.getObservableMovies();
        observableMovies.addAll(Movie.initializeMovies());
        //When:
        homeController.sortAscending(observableMovies);
        //Then:
        for (int i = 0; i < observableMovies.size() - 1; i++) {
            assertTrue(observableMovies.get(i).getTitle().compareTo(observableMovies.get(i + 1).getTitle()) <= 0);
        }
    }

    @Test
    public void testSortDescending() {
        //Given:
        HomeController homeController = new HomeController();
        ObservableList<Movie> observableMovies = homeController.getObservableMovies();
        observableMovies.addAll(Movie.initializeMovies());
        //When:
        homeController.sortDescending(observableMovies);
        //Then:
        for (int i = 0; i < observableMovies.size() - 1; i++) {
            assertTrue(observableMovies.get(i).getTitle().compareTo(observableMovies.get(i + 1).getTitle()) >= 0);
        }
    }

    @BeforeEach
    void setUp() {
        this.homeController = new HomeController();
        ObservableList<Movie> observableMovies = this.homeController.getObservableMovies();
        observableMovies.addAll(Movie.initializeMovies());
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
        homeController.filterMovies(Genre.SCIENCE_FICTION, "the");
        ObservableList<Movie> filteredMovies = homeController.getObservableMovies();

        // Calculate the expected number of movies using a loop
        int expectedCount = 0;
        List<Movie> allMovies = Movie.initializeMovies();
        for (Movie movie : allMovies) {
            if (movie.getGenres().contains(Genre.SCIENCE_FICTION)) {
                String titleLower = movie.getTitle().toLowerCase();
                String descriptionLower = movie.getDescription().toLowerCase();
                if (titleLower.contains("the") || descriptionLower.contains("the")) {
                    expectedCount++;
                }
            }
        }
        // Then: the filtered list should have the expected number of movies
        assertEquals(expectedCount, filteredMovies.size(), "we expect " + expectedCount + " movies that meet the criteria");

        // And check each movie meets the  criteria
        for (Movie movie : filteredMovies) {
            // Check that the movie has the SCIENCE_FICTION genre.
            assertTrue(movie.getGenres().contains(Genre.SCIENCE_FICTION),
                    "Movie does not have genre SCIENCE_FICTION: " + movie.getTitle());

            // Check that either title or description contains "the" (case-insensitive)
            String titleLower = movie.getTitle().toLowerCase();
            String descriptionLower = movie.getDescription().toLowerCase();
            assertTrue(titleLower.contains("the") || descriptionLower.contains("the"),
                    "Movie does not contain 'the' in title or description: " + movie.getTitle());
        }
    }


}
