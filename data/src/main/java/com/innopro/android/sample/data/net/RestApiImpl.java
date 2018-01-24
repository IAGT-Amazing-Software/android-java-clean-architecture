package com.innopro.android.sample.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.innopro.android.sample.data.BuildConfig;
import com.innopro.android.sample.data.entity.CategoryEntity;
import com.innopro.android.sample.data.entity.MessageEntity;
import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.data.entity.UserEntity;
import com.innopro.android.sample.data.entity.UserLoggedEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {
    //region Constants
    private static final String TAG = RestApiImpl.class.getSimpleName();
    //endregion

    //region Fields
    private final Context context;
    private RestEndPoint retrofitAPI;

    //endregion

    //region Constructors & Initialization
    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     */
    public RestApiImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();

        String baseUrl = BuildConfig.domain;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RestEndPoint.class);
    }

    //endregion

    //region Methods for/from SuperClass/Interfaces
    @Override
    public Observable<TokenEntity> tokenEntity() {
        return retrofitAPI.tokenEntity();
    }

    /*USERS*/
    @Override
    public Observable<List<UserEntity>> userEntityList() {
        retrofitAPI.userEntityList().map(userEntities -> Log.i("Users", userEntities.toString()));
        return retrofitAPI.userEntityList();
    }

    @Override
    public Observable<UserEntity> userEntityById(final int userId) {
        return retrofitAPI.userEntityById(userId);
    }

    /*USER LOGGED*/
    @Override
    public Observable<UserLoggedEntity> userLoggedEntity() {
        return retrofitAPI.userLoggedEntity();
    }

    /*MESSAGES*/
    @Override
    public Observable<List<MessageEntity>> messageEntityList() {
        return retrofitAPI.messageEntityList();
    }

    @Override
    public Observable<MessageEntity> messageEntityById(final int messageId) {
        return retrofitAPI.messageEntityById(messageId);
    }

    /*CATEGORIES*/
    @Override
    public Observable<List<CategoryEntity>> categoryEntityList() {
        return retrofitAPI.categoryEntityList();
    }

    @Override
    public Observable<CategoryEntity> categoryEntityById(final int categoryId) {
        return retrofitAPI.categoryEntityById(categoryId);
    }
    //endregion

    //region Methods

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
    //endregion

    //region Inner and Anonymous Classes

    //endregion

    //region Getter & Setter

    //endregion





}
