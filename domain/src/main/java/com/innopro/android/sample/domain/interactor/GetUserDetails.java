package com.innopro.android.sample.domain.interactor;

import com.innopro.android.sample.domain.User;
import com.innopro.android.sample.domain.executor.PostExecutionThread;
import com.innopro.android.sample.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetails extends UseCase<User, GetUserDetails.Params> {
    //region Constants
    private static final String TAG = GetUserDetails.class.getSimpleName();
    //endregion

    //region Fields
    private final UserRepository userRepository;

    //endregion

    //region Constructors & Initialization
    @Inject
    public GetUserDetails(UserRepository userRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    protected Observable buildUseCaseObservable(Params params) {
        return this.userRepository.user(params.userId);
    }

    //endregion

    //region Methods

    //endregion

    //region Inner and Anonymous Classes
    public static final class Params {
        private final int userId;

        private Params(int userId) {
            this.userId = userId;
        }

        public static Params forGetUserFiles(int userId) {
            return new Params(userId);
        }
    }

    //endregion

    //region Getter & Setter

    //endregion


}
