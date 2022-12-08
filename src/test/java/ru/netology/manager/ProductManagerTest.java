package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.product.Book;
import ru.netology.domain.product.Product;
import ru.netology.domain.product.Smartphone;
import ru.netology.domain.repository.ProductRepository;


import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Book book1 = new Book(1, "Лес", 500, "Иванов");
    Book book2 = new Book(2, "Лесая поляна", 300, "Петров");
    Book book3 = new Book(3, "Небо", 200, "Сидоров");
    Smartphone smartphone = new Smartphone(5, "Galaxy", 4000, "Samsung");

    @Test
    public void shouldAddProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone);

        Product[] expected = {book1, book2, book3, smartphone};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddProductIfOne() {
        manager.add(book2);

        Product[] expected = {book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shoulNotdAddProductIfNo() {

        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfFewProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone);

        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("Лес");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfOneProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone);

        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Небо");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNo() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Еж");

        Assertions.assertArrayEquals(expected, actual);
    }


}