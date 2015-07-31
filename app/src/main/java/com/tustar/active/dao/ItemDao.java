package com.tustar.active.dao;

import com.activeandroid.query.Select;
import com.tustar.active.model.Item;

import java.util.List;

/**
 * Created by Tu on 7/30/15.
 */
public class ItemDao {

    private ItemDao() {

    }

    public static List<Item> getAll(Long categoryId) {

        return new Select()
                .from(Item.class)
                .where("category = ?", categoryId)
                .orderBy("name asc")
                .execute();
    }

}
