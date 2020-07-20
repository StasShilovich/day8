package test.shilovich.day6.creator;

import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestStorageCreator {
    public CustomBookStorage addBooks() {
        CustomBookStorage storage = CustomBookStorage.getInstance();
        List<CustomBook> books = new ArrayList<>();
        books.add(new CustomBook(41554, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99")));
        books.add(new CustomBook(96942, "Albert Camus", "The Plague",
                1947, new BigDecimal("12.49")));
        books.add(new CustomBook(59725, "Shashi Tharoor", "The Great Indian Novel",
                1989, new BigDecimal("24.36")));
        books.add(new CustomBook(29318, "Paulo Coelho", "The Devil and Miss Prym",
                2000, new BigDecimal("9.99")));
        books.add(new CustomBook(16898, "Patrick Süskin", "Perfume: The Story of a Murderer",
                1985, new BigDecimal("10.47")));
        books.add(new CustomBook(88677, "Toni Morrison", "Jazz",
                1992, new BigDecimal("11.83")));
        books.add(new CustomBook(20504, "Mark Z. Danielewski", "House of Leaves",
                2000, new BigDecimal("13.49")));
        books.add(new CustomBook(84147, "Fyodor Dostoyevsky", "Crime and Punishment",
                1866, new BigDecimal("6.99")));
        books.add(new CustomBook(30741, "Jonathan Coe", "What a Carve Up!",
                1944, new BigDecimal("14.89")));
        storage.setBooks(books);
        return storage;
    }
}
