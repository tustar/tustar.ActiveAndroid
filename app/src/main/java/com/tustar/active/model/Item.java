package com.tustar.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Tu on 7/29/15.
 */
@Table(name = "items")
public class Item extends Model implements Serializable {
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

}
