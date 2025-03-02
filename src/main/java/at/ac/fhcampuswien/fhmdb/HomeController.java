package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> movies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public ObservableList<Movie> filterMovies(String query, Genre genre) {
        return FXCollections.observableArrayList(); // Platzhalter-Rückgabe, immer eine leere Liste
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(movies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortAscending(observableMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortDescending(observableMovies);
                sortBtn.setText("Sort (asc)");
            }

        });

    }

    public void sortAscending(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle));
    }
    public void sortDescending(ObservableList<Movie> movies) {
        movies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    }
