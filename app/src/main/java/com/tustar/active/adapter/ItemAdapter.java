package com.tustar.active.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tustar.active.R;
import com.tustar.active.item.CategoryItem;
import com.tustar.active.model.Item;

import java.util.List;

/**
 * Created by Tu on 7/30/15.
 */
public class ItemAdapter extends BaseAdapter {

    private static final String TAG = ItemAdapter.class.getSimpleName();
    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
        item.text.setText(items.get(position).name);

        return convertView;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
