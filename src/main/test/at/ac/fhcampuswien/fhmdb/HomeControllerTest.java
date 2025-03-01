package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

//@Test
private HomeController homeController;

    @BeforeEach
    public void setUp() {
        homeController = new HomeController();
        homeController.initialize(null, null);  // Simuliere die Initialisierung der UI ohne tatsächliche UI
    }

    @Test
    public void testObservableMoviesInitialization() {
        // Teste, ob die Filme korrekt zur observableMovies Liste hinzugefügt wurden
        List<Movie> allMovies = Movie.initializeMovies();  // Annahme: Movie.initializeMovies() gibt eine Liste zurück
        assertEquals(allMovies.size(), homeController.observableMovies.size(), "Die Anzahl der Filme sollte übereinstimmen.");
    }







}