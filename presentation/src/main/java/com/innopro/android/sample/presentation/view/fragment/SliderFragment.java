package com.innopro.android.sample.presentation.view.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.utils.ScreenUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderFragment extends BaseFragment {

    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer position = getArguments().getInt("position");
        tv_text.setText("Texto correspondiente para la imagen del slider número y posición " + position);
        List<String> images = new ArrayList<>();
        images.add("https://cdn2.techadvisor.co.uk/cmsdata/features/3614881/Android_thumb800.jpg");
        images.add("https://cdn1.cnet.com/img/Gat2Cyj-OXukqJaugh6G4FNHRPg=/1600x900/2017/04/24/07ed121e-8cc9-403b-b2db-78be57830ac8/mejores-apps-android-google-play-awards.png");
        images.add("http://programaenlinea.net/wp-content/uploads/2018/02/android-2.jpg");
        images.add("https://tr2.cbsistatic.com/hub/i/r/2017/01/31/7e355c52-c68f-4389-825f-392f2dd2ac19/resize/770x/d19d6c021f770122da649e2a77bd1404/androiddatahero.jpg");
        images.add("https://www.ayudacelular.com/wp-content/uploads/2018/01/Trucos-para-Android.jpg");

        rl_progress.setVisibility(View.VISIBLE);

        Picasso.with(getContext()).load(images.get(position))
                //resize image to 40% of height of screen
                .resize((int) (ScreenUtils.getHeightScreen(getActivity())*0.4), (int) (ScreenUtils.getHeightScreen(getActivity())*0.4))
                .centerCrop()
                .into(iv_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        //set round image
                        Bitmap imageBitmap = ((BitmapDrawable) iv_image.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        iv_image.setImageDrawable(imageDrawable);

                        rl_progress.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {
//                        iv_image.setImageResource(R.drawable.default_image);
                        rl_progress.setVisibility(View.GONE);
                    }
                });

    }

}
