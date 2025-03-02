package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HomeControllerTest {

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


}
