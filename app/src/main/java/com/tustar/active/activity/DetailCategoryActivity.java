package com.tustar.active.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.tustar.active.R;
import com.tustar.active.adapter.ItemAdapter;
import com.tustar.active.common.ExtraKey;
import com.tustar.active.common.RequestCode;
import com.tustar.active.dao.ItemDao;
import com.tustar.active.model.Category;
import com.tustar.active.model.Item;
import com.tustar.active.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = DetailCategoryActivity.class.getSimpleName();
    private Context context;

    // UI
    private TextView mCategoryName;
    private Button mCategoryRandomBtn;
    private Button mCategoryEditBtn;
    private Button mCategoryAddItemBtn;
    private Category category;

    // Item
    private ListView mListView;
    private LinearLayout mEmptyLayout;
    private List<Item> items;
    private ItemAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        context = this;

        // Category
        category = (Category) getIntent().getSerializableExtra(ExtraKey.KEY_CATEGORY);
        final Category finalCategory = category;
        Logger.d(TAG, "onCreate :: category = " + category);
        mCategoryName = (TextView) findViewById(R.id.detail_category_name);
        mCategoryName.setText(category.name);

        // Random
        mCategoryRandomBtn = (Button) findViewById(R.id.detail_category_random);
        mCategoryRandomBtn.setOnClickListener(v -> {
            Category category = new Select().from(Category.class).orderBy("random()").executeSingle();
            mCategoryName.setText(category.name);
        });

        // Edit
        mCategoryEditBtn = (Button) findViewById(R.id.detail_category_edit);
        mCategoryEditBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(context, AddCategoryActivity.class);
            intent.putExtra(ExtraKey.KEY_CATEGORY, finalCategory);
            startActivityForResult(intent, RequestCode.AddCategoryActivity);
        });

        // Add
        mCategoryAddItemBtn = (Button) findViewById(R.id.detail_category_add_item);
        mCategoryAddItemBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(context, AddItemActivity.class);
            intent.putExtra(ExtraKey.KEY_CATEGORY, finalCategory);
            startActivityForResult(intent, RequestCode.AddItemActivity);
        });

        // Items
        mListView = (ListView) findViewById(R.id.detail_category_item_listview);
        mEmptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        mListView.setEmptyView(mEmptyLayout);
        if (category != null) {
            items = ItemDao.getAll(category);
        }
        if (items == null) {
            items = new ArrayList<>();
        }
        adapter = new ItemAdapter(context, items);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.i(TAG, "onActivityResult :: requestCode = " + requestCode + ", resultCode = "
                + resultCode + ", data = " + data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.AddCategoryActivity:
                    Toast.makeText(context, R.string.add_category_update_success,
                            Toast.LENGTH_SHORT).show();
                    category = (Category) data.getSerializableExtra(ExtraKey.KEY_CATEGORY);
                    mCategoryName.setText(category.name);
                    break;
                case RequestCode.AddItemActivity:
                    Toast.makeText(context, R.string.add_item_success,
                            Toast.LENGTH_SHORT).show();
                    items = ItemDao.getAll(category);
                    adapter.setItems(items);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = (Item) adapter.getItem(position);
        items.remove(item);
        item.delete();
        adapter.setItems(items);
        Toast.makeText(context, R.string.detail_item_delete_success, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = (Item) adapter.getItem(position);
        Intent intent = new Intent();
        intent.setClass(context, AddItemActivity.class);
        intent.putExtra(ExtraKey.KEY_ITEM, item);
        startActivityForResult(intent, RequestCode.AddItemActivity);
    }
}
