package application.controller.web;

import application.data.model.Author;
import application.data.model.Book;
import application.data.model.BookImage;
import application.data.service.BookService;
import application.model.viewmodel.common.AuthorVM;
import application.model.viewmodel.common.BookImageVM;
import application.model.viewmodel.common.BookVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class BookDetailController extends BaseController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public String bookDetail(@PathVariable Integer bookId, Model model,
                                @Valid @ModelAttribute("bookname") BookVM bookName,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal) {

        /**
         * check cookie to create cart guid
         */
        /*this.checkCookie(response,request,principal);
*/
        BookVM vm = new BookVM();

        Book bookEntity = bookService.findOne(bookId);


        if(bookEntity!=null) {
            vm.setId(bookEntity.getId());
            vm.setName(bookEntity.getName());
            vm.setMainImage(bookEntity.getMainImage());
            vm.setShortDesc(bookEntity.getShortDesc());
            vm.setPrice(bookEntity.getPrice());
            vm.setCategoryName(bookEntity.getCategory().getName());
            vm.setDiscount(bookEntity.getDiscount());
            vm.setPublishedYear(bookEntity.getPublishedYear());
           /*
            private List<Author> authorVMList;


            /**
             * set list product image vm
             */
            List<BookImageVM> bookImageVMS = new ArrayList<>();
            for(BookImage bookImage : bookEntity.getBookImageList()) {
                BookImageVM bookImageVM = new BookImageVM();
                bookImageVM.setLink(bookImage.getLink());

                bookImageVMS.add(bookImageVM);
            }
            vm.setBookImageVMList(bookImageVMS);

           /* set list author of book*/
            List<AuthorVM> authorVMS = new ArrayList<AuthorVM>();
            for(Author authorBook : bookService.getListAuthorOfBook(bookEntity.getId())){
                AuthorVM authorVM = new AuthorVM();
                authorVM.setName(authorBook.getName());
                authorVMS.add(authorVM);
            }
            vm.setAuthorVMList(authorVMS);



        }



        model.addAttribute("vm",vm);

        return "/book-detail";
    }

}
