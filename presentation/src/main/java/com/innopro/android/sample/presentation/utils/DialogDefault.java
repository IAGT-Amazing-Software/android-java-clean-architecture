package com.innopro.android.sample.presentation.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.innopro.android.sample.presentation.R;

/**
 * Created by macminidesarrollo on 23/1/18.
 */
@FragmentWithArgs
public class DialogDefault extends DialogFragment {

    //region Constants
    private static final String TAG = DialogDefault.class.getSimpleName();
    //endregion

    //region Fields

    @Arg
    String titleString;
    @Arg
    String bodyString;
    @Arg
    String negativeButtonString;
    @Arg
    String positiveButtonString;
    @Arg
    Integer titleColor;
    @Arg
    Integer bodyColor;
    @Arg
    Integer positiveButtonColor;
    @Arg
    Integer negativeButtonColor;
    @Arg
    DialogDefaultListener listener;
    @Arg
    Float titleTextSize;
    @Arg
    Float bodyTextSize;
    @Arg
    Float positiveButtonTextSize;
    @Arg
    Float negativeButtonTextSize;
    //endregion

    //region Constructors & Initialization
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            getDialog().setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder;
        final AlertDialog alertDialog;
        initializeVaribles();

        TextView title = (TextView) View.inflate(getContext(), R.layout.tv_title_dialog, null);
        title.setText(titleString);
        title.setTextColor(titleColor);
        title.setTextSize(titleTextSize);
        builder = new AlertDialog.Builder(getActivity());
        builder.setCustomTitle(title)
                .setMessage(bodyString)
                .setPositiveButton(positiveButtonString, (dialogInterface, i) -> {
                    onAccept();
                })
                .setNegativeButton(negativeButtonString, (dialogInterface, i) -> {
                    onCancel();
                });
        alertDialog = builder.create();
        alertDialog.setOnShowListener((dialog) -> {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveButtonColor);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(positiveButtonTextSize);
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(negativeButtonColor);
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(negativeButtonTextSize);

            int textColorId = getResources().getIdentifier("alertMessage", "id", "android");
            TextView textMessage = alertDialog.findViewById(textColorId);
            if (textMessage != null) {
                textMessage.setTextColor(bodyColor);
                textMessage.setTextSize(bodyTextSize);
            }
        });

        try {
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_corner_transparent);
        } catch (Exception e) {
            Log.e(TAG, "onCreateDialog: ", e);
        }
        return alertDialog;
    }
    //endregion

    //region Methods for/from SuperClass/Interfaces

    //endregion

    //region Methods
    public void initializeVaribles() {
        if (titleString == null || titleString.isEmpty()) {
            titleString = "Título";
        }
        if (bodyString == null || bodyString.isEmpty()) {
            bodyString = "Cuerpo";
        }
        if (positiveButtonString == null || positiveButtonString.isEmpty()) {
            positiveButtonString = "Aceptar";
        }
        if (negativeButtonString == null || negativeButtonString.isEmpty()) {
            negativeButtonString = "Cancelar";
        }
        if (positiveButtonColor == null) {
            positiveButtonColor = getResources().getColor(R.color.black);
        }
        if (negativeButtonColor == null) {
            negativeButtonColor = getResources().getColor(R.color.black);
        }
        if (titleColor == null) {
            titleColor = getResources().getColor(R.color.black);
        }
        if (bodyColor == null) {
            bodyColor = getResources().getColor(R.color.black);
        }
        if(titleTextSize ==null){
            titleTextSize = 16.0f;
        }

        if(bodyTextSize ==null){
            bodyTextSize = 16.0f;
        }

        if(positiveButtonTextSize ==null){
            positiveButtonTextSize = 16.0f;
        }

        if(negativeButtonTextSize ==null){
            positiveButtonTextSize = 16.0f;
        }
    }
    //endregion

    public void onCancel() {
        if (listener != null) {
            listener.onCancel();
        } else {
            Log.w(TAG, "onCancel: not implementing listener");
        }
        dismiss();
    }

    public void onAccept() {
        if (listener != null) {
            listener.onAccept();
        } else {
            Log.w(TAG, "onAccept: not implementing listener");
        }
        dismiss();
    }

    //endregion

    //region Getter & Setter

    //endregion

    //region Inner and Anonymous Classes
    public interface DialogDefaultListener extends Parcelable {
        void onAccept();

        void onCancel();
    }


//endregion
}