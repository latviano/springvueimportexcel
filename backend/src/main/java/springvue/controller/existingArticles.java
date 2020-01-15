package springvue.controller;

import java.util.HashMap;

public class existingArticles {
    private String article;
    HashMap<Integer, String> fileExtension;

    public existingArticles(String article) {
        this.article = article;
        this.fileExtension = new HashMap<>();
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void addRow(Integer id, String fileExtension){
        this.fileExtension.put(id, fileExtension);
    }
}
