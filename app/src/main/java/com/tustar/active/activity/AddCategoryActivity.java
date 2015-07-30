package com.tustar.active.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tustar.active.R;
import com.tustar.active.common.ExtraKey;
import com.tustar.active.model.Category;

public class AddCategoryActivity extends ActionBarActivity {

    private static final String TAG = AddCategoryActivity.class.getSimpleName();
    private Context context;

    // UI
    private EditText mCategoryNameEt;
    private Button mCategoryAddBtn;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        category = (Category) getIntent().getSerializableExtra(ExtraKey.KEY_CATEGORY);

        mCategoryNameEt = (EditText) findViewById(R.id.add_category_input);
        if (category != null) {
            mCategoryNameEt.setText(category.name);
        }
        mCategoryAddBtn = (Button) findViewById(R.id.add_category_btn);
        mCategoryAddBtn.setOnClickListener(view -> {
            String name = mCategoryNameEt.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(context, R.string.add_category_tips, Toast.LENGTH_SHORT).show();
                return;
            }

            Category newCategory = category;
            if (newCategory == null) {
                newCategory = new Category();
            }
            newCategory.name = name;
            if (newCategory.save().longValue() > 0) {
                Intent data = new Intent();
                data.putExtra(ExtraKey.KEY_CATEGORY, newCategory);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_category, menu);
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
