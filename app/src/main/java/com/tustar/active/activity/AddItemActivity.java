package com.tustar.active.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tustar.active.R;
import com.tustar.active.common.ExtraKey;
import com.tustar.active.model.Category;
import com.tustar.active.model.Item;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = AddItemActivity.class.getSimpleName();
    private Context context;
    private EditText mItemNameEt;
    private Button mSaveBtn;

    private Category category;
    private Item item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Long categoryId = getIntent().getLongExtra(ExtraKey.KEY_CATEGORY_ID, -1);
        if (categoryId > -1) {
            category = Category.load(Category.class, categoryId);
        }
        Long itemId = getIntent().getLongExtra(ExtraKey.KEY_ITEM_ID, -1);
        if (itemId > -1) {
            item = Item.load(Item.class, itemId);
        }

        mItemNameEt = (EditText) findViewById(R.id.add_item_input);
        mSaveBtn = (Button) findViewById(R.id.add_item_btn);
        if (item != null) {
            mItemNameEt.setText(item.name);
        }
        mSaveBtn.setOnClickListener(v -> {
            String name = mItemNameEt.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(context, R.string.add_item_tips, Toast.LENGTH_SHORT).show();
                return;
            }

            if (item == null) {
                item = new Item();
            }
            if (category != null) {
                item.category = category;
            }
            item.name = name;
            item.save();
            setResult(RESULT_OK);
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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
}
