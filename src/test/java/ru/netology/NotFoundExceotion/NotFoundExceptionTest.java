package ru.netology.NotFoundExceotion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.product.Product;
import ru.netology.domain.repository.ProductRepository;

public class NotFoundExceptionTest {
    Product product1 = new Product(1, "Лес", 500);
    Product product2 = new Product(2, "Sumsung", 4000);
    Product product3 = new Product(3, "Iphone", 40_000);


    @Test
    public void shouldRemoveCorrectId() {
        ProductRepository repo = new ProductRepository();

        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.removeById(product1.getId());

        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveIncorrectId() {
        ProductRepository repo = new ProductRepository();

        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Assertions.assertThrows(NotFoundException.class, () -> repo.removeById(6));
    }
}
