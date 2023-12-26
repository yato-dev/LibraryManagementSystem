public class Book extends LibraryItem {

    String genre;
    int numberofPages;

    public Book(String title, String author, String publisher, String genre, int numberofPages) {
        super(title, author, publisher);
        this.genre = genre;
        this.numberofPages = numberofPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberofPages() {
        return numberofPages;
    }

    public void setNumberofPages(int numberofPages) {
        this.numberofPages = numberofPages;
    }
    

}
