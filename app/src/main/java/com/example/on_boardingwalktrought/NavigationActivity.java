package com.example.on_boardingwalktrought;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity {

    ViewPager sliderViewpager;
    LinearLayout dotIndicator;
    viewpagerAdapter viewpagerAdapter;
    Button backButton, skipButton, nextButton;

    TextView[] dots;

    ViewPager.OnPageChangeListener viewpagerLister = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);

            if (position > 0) {
                backButton.setVisibility(View.VISIBLE);

            } else {
                backButton.setVisibility(View.INVISIBLE);

            }
            if (position == 2) {
                nextButton.setText("Finish");
            } else {
                nextButton.setText("Next");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        backButton=findViewById(R.id.backButton);
        nextButton=findViewById(R.id.NextButton);
        skipButton=findViewById(R.id.SkipButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)>0){
                    sliderViewpager.setCurrentItem(getItem(-1),true);
                }
            }
        });
nextButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(getItem(0)<2){
            sliderViewpager.setCurrentItem(getItem(1),true);
        }
        else {
            Intent intent=new Intent(NavigationActivity.this,GetStarted.class);
            startActivity(intent);
            finish();
        }
    }
});
skipButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(NavigationActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
});

sliderViewpager=(ViewPager) findViewById(R.id.slideViewpager);
dotIndicator=(LinearLayout)  findViewById(R.id.doyIndicator);

viewpagerAdapter =new viewpagerAdapter(this);
sliderViewpager.setAdapter(viewpagerAdapter);

setDotIndicator(0);
sliderViewpager.addOnPageChangeListener(viewpagerLister);

    }

    public  void setDotIndicator(int position){
        dots=new TextView[3];
        dotIndicator.removeAllViews();

        for(int i=0; i< dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.gray, getApplicationContext().getTheme()));
            dotIndicator.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.Lavender,getApplicationContext().getTheme()));

    }
    private int getItem(int i){
        return sliderViewpager.getCurrentItem() + i ;
    }
}