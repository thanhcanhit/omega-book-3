/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enums.Type;
import enums.BookCategory;
import enums.BookType;
import java.time.LocalDate;

/**
 *
 * @author thanhcanhit
 */
public final class Book extends Product {

    //    Hằng báo lỗi
    public static final String AUTHOR_EMPTY = "Book author không được rỗng";
    public static final String PUBLISHER_EMPTY = "Book publisher không được rỗng";
    public static final String LANGUAGE_EMPTY = "Book language không được rỗng";
    public static final String TRANSLATOR_EMPTY = "Book author không được rỗng (có thể null)";
    public static final String PUBLISH_YEAR_INVALID = "Book publishYear phải < 1900 và <= năm hiện tại";
    public static final String PAGE_QUANTITY_LOWER_ZERO = "Book pageQuantity phải lớn hơn 0";
    private String author;
    private String publisher;
    private Integer publishYear;
    private String description;

private Integer pageQuantity;
    private Boolean isHardCover;
    private String language;
    private String translator;
    private BookType bookOrigin;
    private BookCategory bookCategory;

    public Book() {
    }

    public Book(String ProductID) throws Exception {
        super(ProductID);
    }

//    Không truyền price, dùng khi tạo đối tượng mới
    public Book(String author, String publisher, Integer publishYear, String description, Integer pageQuantity, Boolean isHardCover, String language, String translator, BookType bookOrigin, BookCategory bookCategory, String productID, String name, Double costPrice, byte[] image, Double VAT, Integer inventory, Type type) throws Exception {
        super(productID, name, costPrice, image, VAT, inventory, type);
        setAuthor(author);
        setPublisher(publisher);
        setPublishYear(publishYear);
        setDescription(description);
        setPageQuantity(pageQuantity);
        setIsHardCover(isHardCover);
        setLanguage(language);
        setTranslator(translator);
        setBookOrigin(bookOrigin);
        setBookCategory(bookCategory);
    }

//    Truyền tất cả, dùng khi đọc đối tượng từ db
    public Book(String author, String publisher, Integer publishYear, String description, Integer pageQuantity, Boolean isHardCover, String language, String translator, BookType bookOrigin, BookCategory bookCategory, String productID, String name, Double costPrice, Double price, byte[] image, Double VAT, Integer inventory, Type type) throws Exception {
        super(productID, name, costPrice, price, image, VAT, inventory, type);
        setAuthor(author);
        setPublisher(publisher);
        setPublishYear(publishYear);
        setDescription(description);
        setPageQuantity(pageQuantity);
        setIsHardCover(isHardCover);
        setLanguage(language);
        setTranslator(translator);
        setBookOrigin(bookOrigin);
        setBookCategory(bookCategory);
    }

    public String getAuthor() {
        return author;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public BookType getBookOrigin() {
        return bookOrigin;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsHardCover() {
        return isHardCover;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getPageQuantity() {
        return pageQuantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public String getTranslator() {
        return translator;
    }

    public void setAuthor(String author) throws Exception {
        if (author.isBlank()) {
            throw new Exception(AUTHOR_EMPTY);
        }
        this.author = author;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void setBookOrigin(BookType bookOrigin) {
        this.bookOrigin = bookOrigin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsHardCover(Boolean isHardCover) {
        this.isHardCover = isHardCover;
    }

    public void setLanguage(String language) throws Exception {
        if (language != null && language.isBlank()) {
            throw new Exception(LANGUAGE_EMPTY);
        }
        this.language = language;
    }

    public void setPageQuantity(Integer pageQuantity) throws Exception {
        if (pageQuantity < 0) {
            throw new Exception(PAGE_QUANTITY_LOWER_ZERO);
        }
        this.pageQuantity = pageQuantity;
    }

    public void setPublisher(String publisher) throws Exception {
        if (publisher.isBlank()) {
            throw new Exception(PUBLISHER_EMPTY);
        }
        this.publisher = publisher;
    }

    public void setPublishYear(Integer publishYear) throws Exception {
        if (publishYear < 1900 || publishYear > LocalDate.now().getYear()) {
            throw new Exception(PUBLISH_YEAR_INVALID);
        }
        this.publishYear = publishYear;
    }

    public void setTranslator(String translator) throws Exception {
//      Cho phép rỗng
        if (translator != null && translator.isBlank()) {
            throw new Exception(TRANSLATOR_EMPTY);
        }
        this.translator = translator;
    }

    @Override
    public String toString() {
        return "Book{" + "author=" + author + ", publisher=" + publisher + ", publishYear=" + publishYear + ", description=" + description + ", quantity=" + pageQuantity + ", isHardCover=" + isHardCover + ", language=" + language + ", translator=" + translator + '}';
    }

}
