package com.example.wei.tw.material2;

// RecyclerView 元件如果需要提供拖拉項目移動位置
// 左右移動刪除項目操作功能
// 宣告一個輔助介面
public interface ItemTouchHelperAdapter {

    // 長按並移動項目位置
    void onItemMove(int fromPosition, int toPosition);

    // 左右滑動項目
    // 參數：哪個項目
    void onItemDismiss(int position);
}
