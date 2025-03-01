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
        homeController.sortAscending();
        //Then:
        assertEquals("Donnie Darko", observableMovies.get(0).getTitle());
    }


}
