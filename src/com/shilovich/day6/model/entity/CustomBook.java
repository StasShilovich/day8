package com.shilovich.day6.model.entity;

import java.math.BigDecimal;

public class CustomBook {
    private int tag;
    private String author;
    private String title;
    private int year;
    private BigDecimal price;

    public CustomBook() {
    }

    public CustomBook(int tag, String author, String title, int year, BigDecimal price) {
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomBook)) return false;
        CustomBook that = (CustomBook) o;
        if (getTag() != that.getTag()) return false;
        if (getYear() != that.getYear()) return false;
        if (getAuthor() != null ? !getAuthor().equals(that.getAuthor()) : that.getAuthor() != null) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        return getPrice() != null ? getPrice().equals(that.getPrice()) : that.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getTag();
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");
        sb.append("tag=").append(tag);
        sb.append(", author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", date=").append(year);
        sb.append(", price=").append(price);
        sb.append('}').append(System.lineSeparator());
        return sb.toString();
    }
}
