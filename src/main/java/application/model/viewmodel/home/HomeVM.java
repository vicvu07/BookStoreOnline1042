package application.model.viewmodel.home;

import application.model.viewmodel.common.BookVM;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.LayoutHeaderVM;

import java.util.Date;
import java.util.List;

public class HomeVM {
    private List<BannerVM> listBanners;
    private List<BookVM> bookVMList;
    private List<CategoryVM> categoryVMList;
    private String keyWord;
    private LayoutHeaderVM layoutHeaderVM;

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<BannerVM> getListBanners() {
        return listBanners;
    }

    public void setListBanners(List<BannerVM> listBanners) {
        this.listBanners = listBanners;
    }


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
}
