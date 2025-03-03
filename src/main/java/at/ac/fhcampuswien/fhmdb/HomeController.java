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
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(movies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        searchBtn.setOnAction(actionEvent ->  {
            Genre selectedGenre = (Genre) genreComboBox.getSelectionModel().getSelectedItem();

            if (selectedGenre == Genre.NONE) {
                selectedGenre = null;
            }

            String searchQuery = searchField.getText();

            filterMovies(selectedGenre, searchQuery);
        });

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

//    public void filterMoviesByGenre(Genre genre) {
//        filterMovies(genre, "");
//    }

    public void searchMovies(String query) {
        filterMovies(null, query);
    }

    public void filterMovies(Genre genre, String query) {
        if ((genre == null || genre == Genre.NONE) && query.isEmpty()) {
            observableMovies.setAll(movies);
            return;
        }

        List<Movie> filteredMovies = movies.stream()
                .filter(movie -> {
                    if (genre != null && genre != Genre.NONE && !movie.getGenres().contains(genre)) {
                        return false;
                    }
                    if (!query.isEmpty() && !movie.getTitle().toLowerCase().contains(query.toLowerCase())
                     && !movie.getDescription().toLowerCase().contains(query.toLowerCase()))
                    {
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());

        observableMovies.setAll(filteredMovies);
    }
}
