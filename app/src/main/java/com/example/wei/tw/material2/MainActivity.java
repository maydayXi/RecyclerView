package com.example.wei.tw.material2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txt_info;
    private RecyclerView item_list;
    private FruitAdapter itemAdapter;

    // 列表元件使用的資料
    private List<Fruit> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processViews();

        items = new ArrayList<>();

        // 加入三個 Fruit 物件
        items.add(new Fruit(1, "Strawberry",
                "Sweet fleshy red fruit", true));
        items.add(new Fruit(2, "Carrot",
                "Edible root of the cultivated carrot plant",
                false));
        items.add(new Fruit(3, "Pumpkin",
                "Usually large pulpy deep-yellow round fruit",
                true));

        // 增加 RecyclerView 效能的設定
        item_list.setHasFixedSize(true);

        // 建立與設定 RecyclerView 元件的配置版面管理
        item_list.setLayoutManager(
                new LinearLayoutManager(this));

        // 設定 RecyclerView 元件的資料來源物件
        itemAdapter = new FruitAdapter(items, this) {

            @SuppressLint("RecyclerView")
            @Override
            public void onBindViewHolder(final ViewHolder holder,
                                         final int position) {
                super.onBindViewHolder(holder, position);

                holder.rootView.setOnClickListener(
                        new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Fruit fruit = items.get(position);
                        txt_info.setText(fruit.getName());
                    }
                });
            }
        };

        // 設定 RecyclerView 使用的資料來源物件
        item_list.setAdapter(itemAdapter);

        // 建立與設定項目操作物件
        ItemTouchHelper.Callback callback = new
                SimpleItemTouchHelperCallback(itemAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(item_list);
    }

    private void processViews() {
        txt_info = findViewById(R.id.txtInfo);
        item_list = findViewById(R.id.item_list);
    }

    public void clickAdd(View v) {
        int newId = items.size() + 1;
        Fruit fruit = new Fruit(newId, "Fruit#" + newId,
                "Content#" + newId, false);

        // 新增一個項目
        itemAdapter.add(fruit);
        // 移到最後一個項目的位置
        item_list.scrollToPosition(items.size() - 1);
    }
}
