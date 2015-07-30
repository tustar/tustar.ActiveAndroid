package com.tustar.active.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.tustar.active.R;
import com.tustar.active.adapter.CategoryAdapter;
import com.tustar.active.common.ExtraKey;
import com.tustar.active.common.RequestCode;
import com.tustar.active.model.Category;
import com.tustar.active.utils.Logger;

import java.util.List;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context context;

    // ListView
    private ListView mCateListView;
    private LinearLayout mEmptyLayout;
    private List<Category> categories;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mCateListView = (ListView) findViewById(R.id.cate_listview);
        mEmptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        mCateListView.setEmptyView(mEmptyLayout);

        categories = getCategoriesFromDb();
        adapter = new CategoryAdapter(context, categories);
        mCateListView.setAdapter(adapter);
        mCateListView.setOnItemClickListener(this);
        mCateListView.setOnItemLongClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        categories = getCategoriesFromDb();
        adapter.setCategories(categories);
    }

    private List<Category> getCategoriesFromDb() {
        return new Select().from(Category.class).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent();
            intent.setClass(context, AddCategoryActivity.class);
            startActivityForResult(intent, RequestCode.AddCategoryActivity);
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
                    Toast.makeText(context, R.string.add_category_success,
                            Toast.LENGTH_SHORT).show();
                    categories = getCategoriesFromDb();
                    adapter.setCategories(categories);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Category category = (Category) adapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(ExtraKey.KEY_CATEGORY, category);
        intent.setClass(context, DetailCategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Category category = (Category) adapter.getItem(position);
        categories.remove(category);
        category.delete();
        adapter.setCategories(categories);
        Toast.makeText(context, R.string.detail_category_delete_success, Toast.LENGTH_SHORT).show();
        return true;
    }
}
