package com.example.android.bookappteste.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.ActivityDetailBinding;
import com.example.android.bookappteste.ui.bindingadapter.BindingAdapterLayout;
import com.example.android.bookappteste.viewmodel.BookViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding;
    private Item actualBook;
    private BookViewModel bookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setSupportActionBar(activityDetailBinding.toolbar);
        actualBook = (Item) getIntent().getSerializableExtra(BindingAdapterLayout.BOOK);

        populateView();

        activityDetailBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Not implemented :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateView(){
        activityDetailBinding.titleTextView.setText(actualBook.getVolumeInfo().getTitle());
        activityDetailBinding.authorTextView.setText(actualBook.getVolumeInfo().getAuthors().get(0));
        activityDetailBinding.descriptionTextView.setText(actualBook.getVolumeInfo().getDescription());

        Glide.with(this)
                .load(actualBook.getVolumeInfo().getImageLinks().getSmallThumbnail())
                .into(activityDetailBinding.imageViewDetails);
    }

    public void saveAsFavorite(View view) {
    }
}