package com.example.board.model;

public class FileVo {
    private int id;
    private int articleId;
    private String uuidName;
    private String originalName;
    private String dir;

    public FileVo() {
    }

    public FileVo(int id, int articleId, String uuidName, String originalName, String dir) {
        this.id = id;
        this.articleId = articleId;
        this.uuidName = uuidName;
        this.originalName = originalName;
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUuidName() {
        return uuidName;
    }

    public void setUuidName(String uuidName) {
        this.uuidName = uuidName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
