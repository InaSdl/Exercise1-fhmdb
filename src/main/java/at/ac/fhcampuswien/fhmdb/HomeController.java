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

    public ObservableList<Movie> filterMovies(String query, Genre genre) {
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(); // Create a new list for filtered movies

        // Loop through all movies in the list
        for (Movie movie : movies) {
            boolean matchesQuery = true;
            boolean matchesGenre = true;

            // check if the title or description == the query
            if (query != null && !query.isBlank()) {
                String lowerCaseQuery = query.toLowerCase(); // Convert the query to lowercase
                String lowerCaseTitle = movie.getTitle().toLowerCase();
                String lowerCaseDescription = movie.getDescription().toLowerCase();

                if (!lowerCaseTitle.contains(lowerCaseQuery) && !lowerCaseDescription.contains(lowerCaseQuery)) {
                    matchesQuery = false; // if neither the title nor the description contains the query -> not a match
                }
            }

            // Check if the movie belongs to the selected genre
            if (genre != null) {
                String lowerCaseGenres = movie.getGenres().toLowerCase(); // Convert genres to lowercase
                String lowerCaseGenreName = genre.name().toLowerCase(); // Convert selected genre to lowercase

                if (!lowerCaseGenres.contains(lowerCaseGenreName)) {
                    matchesGenre = false; // If the movie does not belong to the selected genre, it's not a match
                }
            }

            // If the movie matches both the query and the genre, add it to the filtered list
            if (matchesQuery && matchesGenre) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies; // Return the filtered movie list
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(movies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.getItems().addAll(Genre.values());
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

        // set event handler for the filter button to update movie list based on search criteria
        searchBtn.setOnAction(actionEvent -> {
            String query = searchField.getText();
            Genre selectedGenre = (Genre) genreComboBox.getValue();
            // Apply the filter and update the observable list:
            observableMovies.setAll(filterMovies(query, selectedGenre));
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
