import java.util.ArrayList;

class Oppgave1 {
	public static void main(String[] args) {
		BookShelf<Book> b = new BookShelf<Book>();
		b.put(new Book("A", "B", 123), 1);
		Book a = b.get(1);

	}
}

class Book {
	private String author;
	private String title;
	private int year; 

	Book(String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}
}

interface Container<T> {
	public void put(T b, int t);
	public T get(int t);
}

class BookShelf<T> implements Container<T> {
	ArrayList<T> bookList = new ArrayList<T>();

	public void put(T b, int t) {
		bookList.add(t, b);
	}

	public T get(int t) {
		return bookList.remove(t);
	}
}