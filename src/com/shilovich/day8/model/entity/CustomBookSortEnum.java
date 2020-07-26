package com.shilovich.day8.model.entity;

public enum CustomBookSortEnum {
    AUTHOR("author"),
    TITLE("title"),
    YEAR("year"),
    PRICE("price");

    private String sortParameter;

    private CustomBookSortEnum(String parameter) {
        this.sortParameter = parameter;
    }

    public String getSortParameter() {
        return sortParameter;
    }
}