package springvue.controller;

import java.util.ArrayList;
import java.util.List;

public class excelFileDesc{
    private List<fileRowDesc> list;

    public List<fileRowDesc> getList() {
        return list;
    }

    public void setList(List<fileRowDesc> list) {
        this.list = list;
    }

    public excelFileDesc() {
        this.list = new ArrayList<>();
    }

    public void addRow(int id, String article, String imgPlace){
        list.add(new fileRowDesc(id, article, imgPlace));
    }
}

