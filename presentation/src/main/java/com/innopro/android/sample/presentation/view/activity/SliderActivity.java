package com.innopro.android.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import com.innopro.android.sample.presentation.R;
import com.innopro.android.sample.presentation.constants.AppConstants;
import com.innopro.android.sample.presentation.view.adapter.SliderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class SliderActivity extends BaseActivity {

    @BindView(R.id.vp_slider)
    ViewPager vp_slider;
    @BindView(R.id.ci_slider)
    CircleIndicator ci_slider;
    @BindView(R.id.b_slider_jump)
    Button b_slider_jump;
    @BindView(R.id.b_slider_next)
    Button b_slider_next;

    Integer currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sharedPreferencesManager.getBoolean(AppConstants.FIRST_INSTALLATION)){
            navigator.navigateToLogin(this);
        }else{
            setContentView(R.layout.slider_activity);
            ButterKnife.bind(this);
            vp_slider.setAdapter(new SliderAdapter(getSupportFragmentManager()));
            ci_slider.setViewPager(vp_slider);

            vp_slider.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    currentPosition = position;
                    if (position == vp_slider.getAdapter().getCount() - 1) {
                        b_slider_jump.setVisibility(View.GONE);
                        b_slider_next.setText(getString(R.string.begin));
                    } else {
                        b_slider_jump.setVisibility(View.VISIBLE);
                        b_slider_next.setText(getString(R.string.next));
                    }
                }
            });
        }

    }

    @OnClick(R.id.b_slider_next)
    public void OnClickNext(){
        if(currentPosition < vp_slider.getAdapter().getCount() - 1){
            vp_slider.setCurrentItem(vp_slider.getCurrentItem() + 1);
        }else{
            navigator.navigateToLogin(this);
            setFirstInstallation(true);
        }
    }

    private void setFirstInstallation(boolean installation) {
        if(!sharedPreferencesManager.getBoolean(AppConstants.FIRST_INSTALLATION)){
            sharedPreferencesManager.setDataPersist(AppConstants.FIRST_INSTALLATION, installation);
        }
    }

    @OnClick(R.id.b_slider_jump)
    public void OnClickJump(){
        navigator.navigateToLogin(this);
        setFirstInstallation(true);
    }


}
