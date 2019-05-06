package com.fitsort.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private int publish_id;
    private String isbn;
    private float price;

    public Book() {
    }

    public Book(int id, String name, String author, int publish_id, String isbn, float price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish_id = publish_id;
        this.isbn = isbn;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(int publish_id) {
        this.publish_id = publish_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
