package com.tustar.active.model;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Tu on 7/29/15.
 */
@Table(name = "items", id = BaseColumns._ID)
public class Item extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    // If name is omitted, then the field name is used.
    @Column(name = "name")
    public String name;

    @Column(name = "category")
    public Category category;

    public Item() {
        super();
    }

    public Item(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}


