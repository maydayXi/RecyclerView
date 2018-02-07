package com.example.wei.tw.material2;

// 要顯示的資料較多時
// 包裝成一個資料類別
public class Fruit {

    // 宣告每個項目資料莖要的欄位變數
    // 編號、名稱、說明與是否選擇
    private long id;
    private String name;
    private String content;
    private boolean selected;

    Fruit(long id,
          String name, String content,
          boolean selected) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.selected = selected;
    }

    // 取得編號
    public long getId() {
        return id;
    }

    // 取得名稱
    String getName() {
        return name;
    }

    // 取得說明
    String getContent() {
        return content;
    }

    // 取得是否選擇
    boolean isSelected() {
        return selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }
}
