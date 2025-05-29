package Main;

import Model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library_hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();


            Author author = new Author();
            author.setName("Orhan Pamuk");
            author.setBirthDate(1952);
            author.setCountry("Türkiye");
            entityManager.persist(author);


            Publisher publisher = new Publisher();
            publisher.setName("YKY");
            publisher.setEstablishmentYear(1950);
            publisher.setAddress("İstanbul");
            entityManager.persist(publisher);


            Category category = new Category();
            category.setName("Novel");
            category.setDescription("Literary Novel");
            entityManager.persist(category);


            Book book = new Book();
            book.setName("Kar");
            book.setPublicationYear(2002);
            book.setStock(5);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setCategories(Set.of(category));
            entityManager.persist(book);


            BookBorrowing borrowing = new BookBorrowing();
            borrowing.setBorrowerName("Ali Veli");
            borrowing.setBorrowingDate(LocalDate.now());
            borrowing.setReturnDate(null);
            borrowing.setBook(book);
            entityManager.persist(borrowing);

            transaction.commit();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
