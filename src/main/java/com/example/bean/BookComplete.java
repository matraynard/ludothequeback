package com.example.bean;

import com.example.entity.Book;

public class BookComplete {

    private Book book;
    private Long pageCount;

    public BookComplete(Book book, Long pageCount){
        this.book = book;
        this.pageCount = pageCount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}