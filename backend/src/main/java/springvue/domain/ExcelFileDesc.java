package springvue.domain;

import java.util.ArrayList;
import java.util.List;

public class ExcelFileDesc {
    private List<FileRowDesc> list;

    public List<FileRowDesc> getList() {
        return list;
    }

    public void setList(List<FileRowDesc> list) {
        this.list = list;
    }

    public ExcelFileDesc() {
        this.list = new ArrayList<>();
    }

    public void addRow(int id, String article, String imgPlace){
        list.add(new FileRowDesc(id, article, imgPlace));
    }
}

