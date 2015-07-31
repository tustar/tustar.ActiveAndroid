package com.tustar.active.model;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Tu on 7/29/15.
 */
@Table(name = "categories", id = BaseColumns._ID)
public class Category extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    public String name;

    public Category() {
        super();
    }

    // This method is optional, does not affect the foreign key creation.
    public List<Item> items() {
        return getMany(Item.class, "category");
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
