package com.example.board.model;

public class Article {
    private int id;
    private int categoryId;
    private String title;
    private String author;
    private String password;
    private String content;
    private int viewCount;
    private String postDate;
    private String editDate;

    public Article() {
    }

    public Article(int id, int categoryId, String title, String author, String password, String content, int viewCount, String postDate, String editDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.author = author;
        this.password = password;
        this.content = content;
        this.viewCount = viewCount;
        this.postDate = postDate;
        this.editDate = editDate;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }

    public String getContent() {
        return content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getEditDate() {
        return editDate;
    }
}
