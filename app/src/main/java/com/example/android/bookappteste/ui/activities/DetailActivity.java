package com.example.android.bookappteste.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
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
    }

    private void populateView(){
        activityDetailBinding.titleTextView.setText(actualBook.getVolumeInfo().getTitle());
        activityDetailBinding.authorTextView.setText(actualBook.getVolumeInfo().getAuthors().get(0));
        activityDetailBinding.descriptionTextView.setText(actualBook.getVolumeInfo().getDescription());

        if (checkBookHasBuyLink()) activityDetailBinding.buyButton.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(actualBook.getVolumeInfo().getImageLinks().getSmallThumbnail())
                .into(activityDetailBinding.imageViewDetails);
    }

    public void saveAsFavorite(View view) {
        Toast.makeText(DetailActivity.this, "Not implemented :(", Toast.LENGTH_SHORT).show();
    }

    public void openBuyLink(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actualBook.getSaleInfo().getBuyLink()));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        browserIntent.setPackage("com.android.chrome");
        startActivity(browserIntent);
    }

    private boolean checkBookHasBuyLink(){
        return actualBook != null
                && actualBook.getSaleInfo().getBuyLink() != null
                && actualBook.getSaleInfo().getSaleability() != null
                && !actualBook.getSaleInfo().getBuyLink().isEmpty()
                && actualBook.getSaleInfo().getSaleability().equals("FOR_SALE");
    }

}