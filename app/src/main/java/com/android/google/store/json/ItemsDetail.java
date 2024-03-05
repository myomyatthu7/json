package com.android.google.store.json;

import static com.android.google.store.json.Items.KEY_CREATOR;
import static com.android.google.store.json.Items.KEY_IMAGEURL;
import static com.android.google.store.json.Items.KEY_LIKE;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ItemsDetail extends AppCompatActivity {
    TextView tvCreatorDetail,tvLikesDetail;
    ImageView ivJsonImageDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_detail);
        tvCreatorDetail = findViewById(R.id.tvCreatorDetail);
        tvLikesDetail = findViewById(R.id.tvLikesDetail);
        ivJsonImageDetail = findViewById(R.id.ivJsonImageDetail);

        String creatorDetail = getIntent().getStringExtra(KEY_CREATOR);
        String imageUrlDetail = getIntent().getStringExtra(KEY_IMAGEURL);
        String likeDetail = getIntent().getStringExtra(KEY_LIKE);
        //int likeDetail = getIntent().getIntExtra(KEY_LIKE,0);

        Picasso.with(ItemsDetail.this).load(imageUrlDetail)
                .fit().centerInside().into(ivJsonImageDetail);
        tvCreatorDetail.setText(creatorDetail);
        tvLikesDetail.setText(likeDetail);
    }
}
