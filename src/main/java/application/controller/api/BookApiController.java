package application.controller.api;

import application.data.model.Category;
import application.data.model.Book;
import application.data.service.CategoryService;
import application.data.service.BookService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.BookDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/book")
public class BookApiController {

    private static final Logger logger = LogManager.getLogger(BookApiController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;



    @PostMapping(value = "/create")
    public BaseApiResult createBook(@RequestBody BookDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Book book = new Book();
            book.setName(dto.getName());
            book.setShortDesc(dto.getShortDesc());
            book.setPrice(dto.getPrice());
            book.setDiscount(dto.getDiscount());
            book.setMainImage(dto.getMainImage());
            book.setCategory(categoryService.findOne(dto.getCategoryId()));
            book.setCreatedDate(new Date());
            book.setPublishedYear(dto.getPublishedYear());
            bookService.addNewBook(book);
            result.setData(book.getId());
            result.setMessage("Save book successfully: " + book.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{bookId}")
    public BaseApiResult updateBook(@PathVariable int bookId,
                                       @RequestBody BookDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Book book = bookService.findOne(bookId);
            book.setName(dto.getName());
            book.setShortDesc(dto.getShortDesc());
            book.setPrice(dto.getPrice());
            book.setDiscount(dto.getDiscount());
            book.setMainImage(dto.getMainImage());
            book.setCategory(categoryService.findOne(dto.getCategoryId()));
            book.setCreatedDate(new Date());
            book.setPublishedYear(dto.getPublishedYear());
            bookService.addNewBook(book);
            result.setSuccess(true);
            result.setMessage("Update book successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{bookId}")
    public BaseApiResult detailMaterial(@PathVariable int bookId){
        DataApiResult result= new DataApiResult();

        try {
            Book bookEntity = bookService.findOne(bookId);
            if(bookEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this book");
            } else {
                BookDTO dto = new BookDTO();
                dto.setId(bookEntity.getId());
                if(bookEntity.getCategory() != null) {
                    dto.setCategoryId(bookEntity.getCategory().getId());
                }
                dto.setMainImage(bookEntity.getMainImage());
                dto.setName(bookEntity.getName());
                dto.setPrice(bookEntity.getPrice());
                dto.setDiscount(bookEntity.getDiscount());
                dto.setShortDesc(bookEntity.getShortDesc());
                dto.setCreatedDate(new Date());
                dto.setPublishedYear(bookEntity.getPublishedYear());
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
