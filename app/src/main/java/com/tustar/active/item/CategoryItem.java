package com.tustar.active.item;

import android.view.View;
import android.widget.TextView;

import com.tustar.active.R;

/**
 * Created by Tu on 7/30/15.
 */
public class CategoryItem {

    public TextView text;

    public CategoryItem(View view) {
        text = (TextView) view.findViewById(R.id.item_category_text);
    }
}
