package springvue.domain;

import java.util.HashMap;

public class ExistingArticles {
    private String article;
    public HashMap<Integer, String> fileExtension;

    public ExistingArticles(String article) {
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
