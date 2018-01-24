package com.innopro.android.sample.data.repository;

import com.innopro.android.sample.data.entity.mapper.UserLoggedEntityDataMapper;
import com.innopro.android.sample.data.repository.datasource.UserLoggedDataStoreFactory;
import com.innopro.android.sample.data.repository.datasource.source.UserLoggedDataStore;
import com.innopro.android.sample.domain.UserLogged;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link UserLoggedRepository} for retrieving user logged data.
 */
@Singleton
public class UserLoggedDataRepository implements UserLoggedRepository {
    //region Constants
    private static final String TAG = UserLoggedDataRepository.class.getSimpleName();
    //endregion

    //region Fields
    private final UserLoggedDataStoreFactory userLoggedDataStoreFactory;
    private final UserLoggedEntityDataMapper userLoggedEntityDataMapper;

    //endregion

    //region Constructors & Initialization
    /**
     * Constructs a {@link UserLoggedRepository}.
     *
     * @param dataStoreFactory           A factory to construct different data source implementations.
     * @param userLoggedEntityDataMapper {@link UserLoggedEntityDataMapper}.
     */
    @Inject
    public UserLoggedDataRepository(UserLoggedDataStoreFactory dataStoreFactory,
                                    UserLoggedEntityDataMapper userLoggedEntityDataMapper) {
        this.userLoggedDataStoreFactory = dataStoreFactory;
        this.userLoggedEntityDataMapper = userLoggedEntityDataMapper;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<UserLogged> userLogged() {
        final UserLoggedDataStore userLoggedDataStore = this.userLoggedDataStoreFactory.create();
        return userLoggedDataStore.userLoggedEntity()
                .map(userLoggedEntity -> this.userLoggedEntityDataMapper.transform(userLoggedEntity));
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion
}
