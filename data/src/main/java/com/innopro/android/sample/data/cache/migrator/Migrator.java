package com.innopro.android.sample.data.cache.migrator;

import android.util.Log;

import javax.inject.Inject;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by macminidesarrollo on 24/1/18.
 */

public class Migrator implements RealmMigration {
    //region Constants
    private static final String TAG = Migrator.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Inject
    public Migrator(){}
    //endregion

    //region Methods for/from SuperClass/Interfaces


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema=realm.getSchema();
        Log.d(TAG, "migrate: Started Migration "+oldVersion);
        if(oldVersion==0){
            oldVersion++;
        }
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}

