package springvue.controller;

public class idListAndMax {
    private int maxID;
    private existingArticles idList;

    public idListAndMax(int maxID, existingArticles idList) {
        this.maxID = maxID;
        this.idList = idList;
    }

    public int getMaxID() {
        return maxID;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    public existingArticles getIdList() {
        return idList;
    }

    public void setIdList(existingArticles idList) {
        this.idList = idList;
    }
}
