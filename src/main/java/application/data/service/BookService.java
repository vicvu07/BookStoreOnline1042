package application.data.service;

import application.data.model.Author;
import application.data.model.Book;
import application.data.model.BookAuthor;
import application.data.repository.AuthorRepository;
import application.data.repository.BookAuthorRepository;
import application.data.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private static final Logger logger = LogManager.getLogger(BookService.class);


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private BookRepository bookRepository;

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void addNewListBooks(List<Book> listBooks) {
        bookRepository.save(listBooks);
    }
    public Book findOne(int bookId) {
        return bookRepository.findOne(bookId);
    }


    public List<Book> getListAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Page<Book> getListBookByCategoryOrBookNameContaining(Pageable pageable, Integer categoryId, String bookName) {
        return bookRepository.getListBookByCategoryOrBookNameContaining(pageable, categoryId, bookName);
    }


    public boolean updateBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteBook(int bookId) {
        try {
            bookRepository.delete(bookId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }



    public List<Author> getListAuthor() {
        return StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }
    public List<Author> getListAuthorOfBook(int bookId) {
        List<BookAuthor> listBookAuthor = StreamSupport
                .stream(bookAuthorRepository.findAuthorOfBook(bookId).spliterator(), false).collect(Collectors.toList());
        return getListAuthor().stream().filter(author -> {
            return (listBookAuthor.stream().filter(BookAuthor -> BookAuthor.getBookId() == author.getId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }


}
