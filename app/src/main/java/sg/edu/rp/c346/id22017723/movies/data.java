package sg.edu.rp.c346.id22017723.movies;

public class data {

    int id;
    String title;
    int year;
    String rating;
    String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public data(int id, String title, int year, String rating, String genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }
}
