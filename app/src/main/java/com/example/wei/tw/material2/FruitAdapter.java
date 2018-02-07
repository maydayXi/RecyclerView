package com.example.wei.tw.material2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

// 提供資料給 RecyclerView 使用的 Adapter 物件
// 包裝繼承自 RecyclerView.Adapter 的類別
// 泛型指定為 FruitAdapter.ViewHolder 類別
// 實作 ItemTouchHelperAdapter 資料操作介面 (移動項目、刪除項目)
public class FruitAdapter extends
        RecyclerView.Adapter<FruitAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    // 儲存項目資料的 List 物件
    private List<Fruit> items;
    private Context context;

    // 最後一個項目的位置
    private int lastPosition = -1;

    // FruitAdapter 建構子、建構顯示資料
    FruitAdapter(List<Fruit> items, Context context) {
        this.items = items;
        this.context = context;
    }

    // 自行實作 ViewHolder 類別、包裝項目使用的畫面元件
    class ViewHolder extends RecyclerView.ViewHolder {

        // 編號、名稱、說明、是否選擇
        private TextView txt_id, txt_name, txt_content;
        private CheckBox chk_selected;

        // 包裝元件
        View rootView;

        // 建構子、建構包裝的畫面元件
        ViewHolder(View view) {
            super(view);

            // 使用父類別宣告的 itemView 欄位變數
            txt_id = itemView.findViewById(R.id.txtId);
            txt_name = itemView.findViewById(R.id.txtName);
            txt_content = itemView.findViewById(R.id.txtContent);
            chk_selected = itemView.findViewById(R.id.chkSelected);

            rootView = view;
        }
    }

    // Create new Views (invoked by layout manager)
    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        // Create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item, parent,
                        false);

        return new ViewHolder(v);
    }

    // Replace the content of a view(invoked by layout manger)
    // 綁定要顯示的資料內容到畫面元件
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 int position) {

        // get element from your data set at this position
        // 取得目前位置的項目資料
        final Fruit fruit = items.get(position);

        // replace the contents of the view with that element
        // 設定項目資料
        // 編號、名稱、內容、是否選擇
        holder.txt_id.setText(Long.toString(fruit.getId()));
        holder.txt_name.setText(fruit.getName());
        holder.txt_content.setText(fruit.getContent());
        holder.chk_selected.setSelected(fruit.isSelected());


        // 建立與註冊是否選擇元件的點擊監聽事件
        holder.chk_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 設定目前位置項目物件的是否選擇狀態
                fruit.setSelected(holder.chk_selected.isSelected());
            }
        });

        // 設定該項目的進入動畫
        setAnimation(holder.rootView, position);
    }

    // 設定動畫效果
    private void setAnimation(View view, int position) {

        // 如果有新的項目生成
        if (position > lastPosition) {

            // 建立 Animation 動畫物件
            // 第一個參數為使用的 Activity
            // 第二個參數為動畫效果：指定效果為由左方滑入
            Animation animation = AnimationUtils.loadAnimation(
                    context, android.R.anim.slide_in_left);

            // 設定動畫效果
            view.setAnimation(animation);
            // 儲存最後一個項目的位置
            lastPosition = position;
        }
    }

    // 取得項目筆數
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 實作資料操作介面的方法
    // 左右滑動項目 (刪除項目)
    @Override
    public void onItemDismiss(int position) {
        remove(position);
    }

    // 新增一個項目
    void add(Fruit fruit) {
        items.add(fruit);
        // 通知資料項目已經新增
        notifyItemInserted(items.size());
    }

    // 刪除一個項目
    private void remove(int position) {
        items.remove(position);

        // 通知資料項目已經刪除
        notifyItemRemoved(position);
    }

    // 實作 ItemTouchHelperAdapter 介面的方法
    // 長按並移動項目
    // 第一個參數：從哪個位置
    // 第二個參數：到哪個位置
    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        // 如果是往下拖拉
        if (fromPosition < toPosition)
            for (int i = fromPosition; i < toPosition; i++)
                // 交換 List 物件中的二個 element
                // 第一個參數：要交換的 List 物件
                // 第二個參數：要換的第一個 element
                // 第三個參數：要換的第二個 element
                // 第二個 element 跟第二個 element 互換
                Collections.swap(items, i, i+1);
        else
            for (int i= toPosition; i > fromPosition; i--)
                Collections.swap(items, i, i-1);

        // 通知畫面元件資料已交換
        notifyItemMoved(fromPosition, toPosition);
    }
}
