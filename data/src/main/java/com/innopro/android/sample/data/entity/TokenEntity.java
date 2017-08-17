package com.innopro.android.sample.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Token Entity used on Data layer
 */

public class TokenEntity extends RealmObject {
    //region Constants

    //endregion

    //region Fields
    @PrimaryKey
    private int id;
    @SerializedName(value = "value",alternate = {})
    private String value;
    //Expires in seconds
    @SerializedName(value = "expires_in",alternate ={} )
    private long expiresIn;
    @SerializedName(value = "type",alternate = {})
    private String type;
    @SerializedName(value = "scope",alternate = {})
    private String scope;
    //endregion

    //region Constructors & Initialization

    public TokenEntity() {
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    //endregion
}
