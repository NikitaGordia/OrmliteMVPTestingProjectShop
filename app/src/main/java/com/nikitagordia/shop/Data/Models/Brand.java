package com.nikitagordia.shop.Data.Models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nikitagordia on 2/7/18.
 */

@DatabaseTable
public class Brand implements ListableItem {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private ForeignCollection<PC> products;

    @Override
    public String getTitle() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForeignCollection<PC> getProducts() {
        return products;
    }

    public void setProducts(ForeignCollection<PC> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return name;
    }
}
