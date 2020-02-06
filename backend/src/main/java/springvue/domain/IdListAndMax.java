package springvue.domain;

public class IdListAndMax {
    private int maxID;
    private ExistingArticles idList;

    public IdListAndMax(int maxID, ExistingArticles idList) {
        this.maxID = maxID;
        this.idList = idList;
    }

    public int getMaxID() {
        return maxID;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    public ExistingArticles getIdList() {
        return idList;
    }

    public void setIdList(ExistingArticles idList) {
        this.idList = idList;
    }
}
