package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.UserLogged;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.UserLoggedRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link UserLogged}.
 */
public class GetUserLogged extends UseCase<UserLogged, Void> {
    //region Constants
    private static final String TAG = GetUserLogged.class.getSimpleName();
    //endregion

    //region Fields

    private final UserLoggedRepository userLoggedRepository;
    //endregion

    //region Constructors & Initialization

    @Inject
    public GetUserLogged(UserLoggedRepository userLoggedRepository,
                         PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userLoggedRepository = userLoggedRepository;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    @Override
    protected Observable buildUseCaseObservable(Void nothing) {
        return this.userLoggedRepository.userLogged();
    }
    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion


}
