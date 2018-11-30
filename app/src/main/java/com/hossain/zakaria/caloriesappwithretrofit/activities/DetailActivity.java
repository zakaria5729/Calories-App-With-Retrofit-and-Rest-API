package com.hossain.zakaria.caloriesappwithretrofit.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hossain.zakaria.caloriesappwithretrofit.R;

import java.util.Objects;

import butterknife.ButterKnife;

import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.CALORIE_KEY;
import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.IMAGE_PATH_KEY;
import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.NAME_KEY;

public class DetailActivity extends AppCompatActivity {

    private ImageView postImageView;
    private TextView nameTv;
    private TextView calorieTv;
    private Toolbar detailsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        detailsToolbar = findViewById(R.id.detailsToolbar);
        nameTv = findViewById(R.id.nameTv);
        calorieTv = findViewById(R.id.calorieTv);
        postImageView = findViewById(R.id.postImageView);

        setSupportActionBar(detailsToolbar);

        String name = getIntent().getStringExtra(NAME_KEY);
        String calorie = getIntent().getStringExtra(CALORIE_KEY);
        String imagePath = getIntent().getStringExtra(IMAGE_PATH_KEY);

        setData(name, calorie, imagePath);
    }

    private void setData(String name, String calorie, String imagePath) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
        detailsToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        //for stop shared fade transition of actionbar and navigationBar
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }

        if (name != null && calorie != null && imagePath != null) {
            nameTv.setText(name);
            calorieTv.setText(calorie);

            Glide.with(this)
                    .load(imagePath)
                    .into(postImageView);
        }
    }
}
