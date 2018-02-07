package com.example.wei.tw.material2;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

// 項目操作類別，繼承 ItemTouchHelper.Callback 類別
public class SimpleItemTouchHelperCallback
        extends ItemTouchHelper.Callback {

    // 實作操作輔助介面的 Adapter 物件
    private final ItemTouchHelperAdapter adapter;

    // 建構子
    SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    // 開啟長按
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    // 開啟左右滑動
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    // 設定使用者操作的種類為上下與左右滑動
    @Override
    public int getMovementFlags(RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder) {
        // 上下滑動參數
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        // 左右滑動參數
        int swipeFlats = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlats);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {

        // 移動 Adapter 物件的項目資料
        adapter.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                         int direction) {

        // 刪除 Adapter 物件的項目資料
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
