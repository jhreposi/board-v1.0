package com.example.board.model;

public class Comment {
    private int id;
    private String comment;
    private String postDate;
    private int articleId;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getPostDate() {
        return postDate;
    }

    public int getArticleId() {
        return articleId;
    }
}
