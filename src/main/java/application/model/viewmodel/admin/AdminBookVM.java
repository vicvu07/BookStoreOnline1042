package application.model.viewmodel.admin;

import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.LayoutHeaderAdminVM;
import application.model.viewmodel.common.BookVM;

import java.util.List;

public class AdminBookVM {

    private List<BookVM> bookVMList;
    private List<CategoryVM> categoryVMList;
    private String keyWord;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    public List<BookVM> getBookVMList() {
        return bookVMList;
    }

    public void setBookVMList(List<BookVM> bookVMList) {
        this.bookVMList = bookVMList;
    }

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }
}
