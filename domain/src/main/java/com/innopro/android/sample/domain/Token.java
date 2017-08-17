package com.innopro.android.sample.domain;

/**
 * Created by iagt on 15/08/17.
 */

public class Token {
    //region fields
    private String value;
    private long expiresIn;
    private String type;
    private String scope;
    //endregion

    //region Constructors & Initialization

    public Token() {
    }

    public Token(String value, long expiresIn, String type, String scope) {
        this.value = value;
        this.expiresIn = expiresIn;
        this.type = type;
        this.scope = scope;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

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
