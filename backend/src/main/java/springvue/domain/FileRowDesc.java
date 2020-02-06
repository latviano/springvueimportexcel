package springvue.domain;

public class FileRowDesc {
    private int id;
    private String article;
    private String imgPlace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getImgPlace() {
        return imgPlace;
    }

    public void setImgPlace(String imgPlace) {
        this.imgPlace = imgPlace;
    }

    public FileRowDesc() {
    }

    public FileRowDesc(int id, String article, String imgPlace) {
        this.id = id;
        this.article = article;
        this.imgPlace = imgPlace;
    }
}
