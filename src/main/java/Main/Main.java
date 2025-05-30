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

            Author author2 = new Author();
            author2.setName("Elif Şafak");
            author2.setBirthDate(1971);
            author2.setCountry("Türkiye");
            entityManager.persist(author2);


            Publisher publisher = new Publisher();
            publisher.setName("YKY");
            publisher.setEstablishmentYear(1950);
            publisher.setAddress("İstanbul");
            entityManager.persist(publisher);

            Publisher publisher2 = new Publisher();
            publisher2.setName("Doğan Kitap");
            publisher2.setEstablishmentYear(1999);
            publisher2.setAddress("İstanbul");
            entityManager.persist(publisher2);


            Category category = new Category();
            category.setName("Novel");
            category.setDescription("Literary Novel");
            entityManager.persist(category);

            Category category2 = new Category();
            category2.setName("Drama");
            category2.setDescription("Drama Type Books");
            entityManager.persist(category2);


            Book book = new Book();
            book.setName("Kar");
            book.setPublicationYear(2002);
            book.setStock(5);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setCategories(Set.of(category));
            entityManager.persist(book);

            Book book2 = new Book();
            book2.setName("Benim Adım Kırmızı");
            book2.setPublicationYear(1998);
            book2.setStock(3);
            book2.setAuthor(author);
            book2.setPublisher(publisher);
            book2.setCategories(Set.of(category, category2));
            entityManager.persist(book2);

            Book book3 = new Book();
            book3.setName("Aşk");
            book3.setPublicationYear(2009);
            book3.setStock(4);
            book3.setAuthor(author2);
            book3.setPublisher(publisher2);
            book3.setCategories(Set.of(category2));
            entityManager.persist(book3);


            BookBorrowing borrowing = new BookBorrowing();
            borrowing.setBorrowerName("Ali Veli");
            borrowing.setBorrowingDate(LocalDate.now());
            borrowing.setReturnDate(null);
            borrowing.setBook(book);
            entityManager.persist(borrowing);

            BookBorrowing borrowing2 = new BookBorrowing();
            borrowing2.setBorrowerName("Ayşe Kara");
            borrowing2.setBorrowingDate(LocalDate.of(2024, 10, 1));
            borrowing2.setReturnDate(LocalDate.of(2024, 10, 15));
            borrowing2.setBook(book);
            entityManager.persist(borrowing2);

            BookBorrowing borrowing3 = new BookBorrowing();
            borrowing3.setBorrowerName("Mehmet Can");
            borrowing3.setBorrowingDate(LocalDate.now());
            borrowing3.setReturnDate(null);
            borrowing3.setBook(book3);
            entityManager.persist(borrowing3);

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
