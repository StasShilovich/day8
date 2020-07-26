package com.shilovich.day8.model.entity;

import java.math.BigDecimal;

public class CustomBook {
    private Long id;
    private String author;
    private String title;
    private Integer year;
    private BigDecimal price;
    private Boolean deleted;

    public CustomBook() {
    }

    public CustomBook(Long id, String author, String title, Integer year, BigDecimal price, Boolean deleted) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomBook book = (CustomBook) o;
        if (id != null ? !id.equals(book.id) : book.id != null) {
            return false;
        }
        if (author != null ? !author.equals(book.author) : book.author != null) {
            return false;
        }
        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }
        if (year != null ? !year.equals(book.year) : book.year != null) {
            return false;
        }
        if (price != null ? !price.equals(book.price) : book.price != null) {
            return false;
        }
        return deleted != null ? deleted.equals(book.deleted) : book.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");
        sb.append("id=").append(id);
        sb.append(", author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", year=").append(year);
        sb.append(", price=").append(price);
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }
}
