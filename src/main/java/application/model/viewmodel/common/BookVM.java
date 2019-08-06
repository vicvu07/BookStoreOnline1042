package application.model.viewmodel.common;

import application.data.model.Author;

import java.util.Date;
import java.util.List;

public class BookVM {
    private int id;
    private String categoryName;
    private String name;
    private String shortDesc;
    private String mainImage;
    private int price;
    private Date createdDate;
    private int publishedYear;
    private int discount;
    private List<AuthorVM> authorVMList;
    private List<BookImageVM> bookImageVMList;

    public List<AuthorVM> getAuthorVMList() {
        return authorVMList;
    }

    public void setAuthorVMList(List<AuthorVM> authorVMList) {
        this.authorVMList = authorVMList;
    }

    public void setBookImageVMList(List<BookImageVM> bookImageVMList) {
        this.bookImageVMList = bookImageVMList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
