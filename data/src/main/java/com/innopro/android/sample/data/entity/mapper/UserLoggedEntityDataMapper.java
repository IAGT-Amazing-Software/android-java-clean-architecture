package com.innopro.android.sample.data.entity.mapper;

import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.entity.UserLoggedEntity;
import com.innopro.android.sample.domain.UserLogged;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link UserLoggedEntity} (in the data layer) to {@link UserLogged} in the
 * domain layer.
 */
@Singleton
public class UserLoggedEntityDataMapper {
    //region Constants
    private static final String TAG = UserLoggedEntityDataMapper.class.getSimpleName();
    //endregion

    //region Fields

    //endregion

    //region Constructors & Initialization
    @Inject
    public UserLoggedEntityDataMapper() {
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods

    /**
     * Transform a {@link UserEntity} into an {@link UserLogged}.
     *
     * @param userLoggedEntity Object to be transformed.
     * @return {@link UserLogged} if valid {@link UserLoggedEntity} otherwise null.
     */
    public UserLogged transform(UserLoggedEntity userLoggedEntity) {
        UserLogged userLogged = null;
        if (userLoggedEntity != null) {
            userLogged = new UserLogged(userLoggedEntity.getUserLoggedId());
            userLogged.setAvatarUrl(userLoggedEntity.getAvatarUrl());
            userLogged.setFullName(userLoggedEntity.getFullname());
        }

        return userLogged;
    }

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion

}
