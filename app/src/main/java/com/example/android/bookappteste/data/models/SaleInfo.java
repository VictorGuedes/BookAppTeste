
package com.example.android.bookappteste.data.models;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "SaleInfo")
public class SaleInfo implements Serializable {

    @SerializedName("saleability")
    @Expose
    private String saleability;

    @SerializedName("buyLink")
    @Expose
    private String buyLink;

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }
}
