package application.controller.web;


import application.data.model.Category;
import application.data.model.Book;
/*import application.data.model.User;*/
import application.data.service.CategoryService;
import application.data.service.BookService;
import application.model.viewmodel.admin.AdminChartVM;
import application.model.viewmodel.admin.AdminBookVM;
import application.model.viewmodel.admin.HomeAdminVM;
import application.model.viewmodel.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;


    @GetMapping("")
    public String admin(Model model){

        HomeAdminVM vm2 = new HomeAdminVM();
        vm2.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm",vm2);

        AdminChartVM vm = new AdminChartVM();
        List<ChartLabelDataVM> countBookByCategory = categoryService.countBookByCategory();
        ChartVM chartVM = new ChartVM();
        chartVM.setLabelDataList(countBookByCategory);
        List<ChartLabelDataVM> sumBookByCategory = categoryService.sumBookByCategory();
        ChartVM chartVM2 = new ChartVM();
        chartVM2.setLabelDataList(sumBookByCategory);


        vm.setCountBookByCategory(chartVM);
        vm.setSumBookByCategory(chartVM2);



        model.addAttribute("vm", vm);

        return "/admin/home";
    }


    @GetMapping("/book")
    public String book(Model model,
                          @Valid @ModelAttribute("bookname") BookVM bookName,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
                          ) {
        AdminBookVM vm = new AdminBookVM();


        /**
         * set list categoryVM
         */
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for(Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }


        Pageable pageable = new PageRequest(page, size);

        Page<Book> bookPage = null;

       if (bookName.getName() != null && !bookName.getName().isEmpty()) {
            bookPage = bookService.getListBookByCategoryOrBookNameContaining(pageable,null,bookName.getName().trim());
            vm.setKeyWord("Find with key: " + bookName.getName());
       } else {
            bookPage = bookService.getListBookByCategoryOrBookNameContaining(pageable,null,null);
       }


        List<BookVM> bookVMList = new ArrayList<>();

        for(Book book : bookPage.getContent()) {
            BookVM bookVM = new BookVM();
            if(book.getCategory() == null) {
                bookVM.setCategoryName("Unknown");
            } else {
                bookVM.setCategoryName(book.getCategory().getName());
            }
            bookVM.setId(book.getId());
            bookVM.setName(book.getName());
            bookVM.setMainImage(book.getMainImage());
            bookVM.setPrice(book.getPrice());
            bookVM.setDiscount(book.getDiscount());
            bookVM.setPublishedYear(book.getPublishedYear());
            bookVM.setShortDesc(book.getShortDesc());
            bookVM.setCreatedDate(book.getCreatedDate());

            bookVMList.add(bookVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        vm.setBookVMList(bookVMList);
        if(bookVMList.size() == 0) {
            vm.setKeyWord("Not found any book");
        }


        model.addAttribute("vm",vm);
        model.addAttribute("page",bookPage);

        return "/admin/book";
    }
    @GetMapping ("/category")
    public String category(Model model) {
        AdminBookVM vm = new AdminBookVM();


        /**
         * set list categoryVM
         */
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for(Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCreatedDate(category.getCreatedDate());
            categoryVMList.add(categoryVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        if(categoryList.size() == 0) {
            vm.setKeyWord("Not found any book");
        }
        model.addAttribute("vm",vm);

        return "/admin/category";
    }

    /*@GetMapping("/chart")
    public String testChart(Model model){
        AdminChartVM vm = new AdminChartVM();
        List<ChartLabelDataVM> countBookByCategory = categoryService.countBookByCategory();
        ChartVM chartVM = new ChartVM();
        chartVM.setLabelDataList(countBookByCategory);
        List<ChartLabelDataVM> sumBookByCategory = categoryService.sumBookByCategory();
        ChartVM chartVM2 = new ChartVM();
        chartVM2.setLabelDataList(sumBookByCategory);


        vm.setCountBookByCategory(chartVM);
        vm.setSumBookByCategory(chartVM2);

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);

        return "/admin/chart";

    }*/

}
