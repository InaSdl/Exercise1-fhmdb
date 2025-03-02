package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    // TODO add more properties here

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres == null || genres.isEmpty()
                ? "No genres available"
                : String.join(", ", genres.stream().map(Enum::name).toList());
    }

    @Override
    public String toString() {
        return "\n" + title + "\n" + description + "\nGenres: " + genresToString();
    }

    private String genresToString() {
        return genres == null || genres.isEmpty()
                ? "No genres available"
                : String.join(", ", genres.stream().map(Enum::name).toList());

    }

        public static List<Movie> initializeMovies() {
            List<Movie> movies = new ArrayList<>();

            movies.add(new Movie("Inception",
                    "A thief who enters the dreams of others to steal their secrets must plant an idea in a target’s subconscious.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            movies.add(new Movie("Inception",
                    "A thief who enters the dreams of others to steal their secrets must plant an idea in a target’s subconscious.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            movies.add(new Movie("Interstellar",
                    "A group of explorers embarks on a space journey to find a new habitable planet as Earth faces extinction.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.DRAMA, Genre.THRILLER)));

            movies.add(new Movie("The Matrix",
                    "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER)));

            movies.add(new Movie("Shutter Island",
                    "A U.S. Marshal investigates the disappearance of a murderer who escaped from a mental institution on a remote island.",
                    Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA)));

            movies.add(new Movie("Memento",
                    "A man with short-term memory loss attempts to solve his wife’s murder, using tattoos and Polaroid pictures to piece together the clues.",
                    Arrays.asList(Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA)));

            movies.add(new Movie("The Prestige",
                    "Two magicians engage in a bitter rivalry, using increasingly dangerous tricks and illusions to outdo each other.",
                    Arrays.asList(Genre.DRAMA, Genre.MYSTERY, Genre.THRILLER)));

            movies.add(new Movie("Ex Machina",
                    "A young programmer is selected to participate in a groundbreaking experiment in artificial intelligence by evaluating the human qualities of a highly advanced humanoid A.I.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.DRAMA)));

            movies.add(new Movie("Edge of Tomorrow",
                    "A public relations officer is caught in a time loop and must relive the same day repeatedly while fighting aliens in an intergalactic war.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER)));

            movies.add(new Movie("Looper",
                    "A hired gun in the future, who kills people sent back in time by criminal organizations, faces a moral dilemma when his future self is sent back for execution.",
                    Arrays.asList(Genre.SCIENCE_FICTION, Genre.THRILLER, Genre.ACTION)));

            movies.add(new Movie("Donnie Darko",
                    "A troubled teenager experiences disturbing visions of a man in a creepy rabbit costume, leading him to uncover a series of strange events involving time travel and reality distortion.",
                    Arrays.asList(Genre.DRAMA, Genre.SCIENCE_FICTION, Genre.THRILLER)));

            movies.add(new Movie("Indiana Jones and the Last Crusade",
                    "An archaeologist embarks on an adventure to find the Holy Grail before the Nazis do.",
                    Arrays.asList(Genre.ADVENTURE, Genre.ACTION)));

            movies.add(new Movie("Toy Story",
                    "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.",
                    Arrays.asList(Genre.ANIMATION, Genre.COMEDY, Genre.FAMILY)));

            movies.add(new Movie("The Theory of Everything",
                    "The life story of the theoretical physicist Stephen Hawking, showcasing his struggles with ALS and his groundbreaking scientific work.",
                    Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA)));

            movies.add(new Movie("Superbad",
                    "Two co-dependent high school seniors attempt to score alcohol for a party to impress their crushes.",
                    Arrays.asList(Genre.COMEDY, Genre.ROMANCE)));

            movies.add(new Movie("The Godfather",
                    "The aging patriarch of an organized crime dynasty transfers control of his empire to his reluctant son.",
                    Arrays.asList(Genre.CRIME, Genre.DRAMA)));

            movies.add(new Movie("Won't You Be My Neighbor?",
                    "An intimate look at America’s favorite neighbor, Mister Rogers.",
                    Arrays.asList(Genre.DOCUMENTARY, Genre.BIOGRAPHY)));

            movies.add(new Movie("Finding Nemo",
                    "A clownfish sets out on a journey to find his lost son.",
                    Arrays.asList(Genre.FAMILY, Genre.ADVENTURE, Genre.ANIMATION)));

            movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring",
                    "A young hobbit and his companions embark on a journey to destroy a powerful ring.",
                    Arrays.asList(Genre.FANTASY, Genre.ADVENTURE, Genre.ACTION)));

            movies.add(new Movie("Schindler's List",
                    "A German industrialist saves thousands of Jewish refugees during World War II by employing them in his factories.",
                    Arrays.asList(Genre.HISTORY, Genre.DRAMA, Genre.WAR)));

            movies.add(new Movie("The Conjuring",
                    "Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.",
                    Arrays.asList(Genre.HORROR, Genre.MYSTERY, Genre.THRILLER)));

            movies.add(new Movie("La La Land",
                    "A jazz musician and an aspiring actress fall in love while pursuing their dreams in Los Angeles.",
                    Arrays.asList(Genre.MUSICAL, Genre.ROMANCE, Genre.COMEDY)));

            movies.add(new Movie("The Girl with the Dragon Tattoo",
                    "A journalist and a computer hacker uncover dark secrets while investigating a decades-old missing person case.",
                    Arrays.asList(Genre.MYSTERY, Genre.CRIME, Genre.THRILLER)));

            movies.add(new Movie("Titanic",
                    "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated Titanic.",
                    Arrays.asList(Genre.ROMANCE, Genre.DRAMA, Genre.HISTORY)));

            movies.add(new Movie("Rocky",
                    "A small-time boxer gets a once-in-a-lifetime chance to fight the heavyweight champion.",
                    Arrays.asList(Genre.SPORT, Genre.DRAMA)));

            movies.add(new Movie("Saving Private Ryan",
                    "A group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.",
                    Arrays.asList(Genre.WAR, Genre.DRAMA, Genre.ACTION)));

            movies.add(new Movie("The Good, the Bad and the Ugly",
                    "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a hidden treasure during the American Civil War.",
                    Arrays.asList(Genre.WESTERN, Genre.ACTION, Genre.ADVENTURE)));






            // TODO add some dummy data here

            return movies;
        }

    }



