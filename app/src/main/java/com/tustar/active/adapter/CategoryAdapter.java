package com.tustar.active.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tustar.active.R;
import com.tustar.active.item.CategoryItem;
import com.tustar.active.model.Category;

import java.util.List;

/**
 * Created by Tu on 7/30/15.
 */
public class CategoryAdapter extends BaseAdapter {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories == null ? 0 : categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CategoryItem item = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_item, parent, false);
            item = new CategoryItem(convertView);
            convertView.setTag(item);
        } else {
            item = (CategoryItem) convertView.getTag();
        }

        // 设置值
        item.text.setText(categories.get(position).name);

        return convertView;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
