package com.example.android.bookappteste.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.ActivityDetailBinding;
import com.example.android.bookappteste.ui.bindingadapter.BindingAdapterLayout;
import com.example.android.bookappteste.viewmodel.BookViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding activityDetailBinding;
    private Item actualBook;
    private BookViewModel bookViewModel;

    private boolean isBookInDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setSupportActionBar(activityDetailBinding.toolbar);
        actualBook = (Item) getIntent().getSerializableExtra(BindingAdapterLayout.BOOK);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        populateView();

        bookViewModel.getFavoriteBookByID(actualBook.getId());
        observeData();
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

    public void saveDeleteFavorite(View view) {
        if (isBookInDatabase) bookViewModel.deleteBook(actualBook);
        else bookViewModel.insertBook(actualBook);
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


    private void observeData(){
        bookViewModel.getDataWasInsert().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(DetailActivity.this, "Book was add", Toast.LENGTH_SHORT).show();
                    activityDetailBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailActivity.this, R.color.yellow_background));
                    isBookInDatabase = true;
                } else {
                    Toast.makeText(DetailActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookViewModel.getDataWasDeleted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(DetailActivity.this, "Book was deleted", Toast.LENGTH_SHORT).show();
                    activityDetailBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailActivity.this, R.color.colorAccent));
                    isBookInDatabase = false ;
                } else {
                    Toast.makeText(DetailActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookViewModel.getFavoriteBooks().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if (!items.isEmpty()){
                    activityDetailBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailActivity.this, R.color.yellow_background));
                    isBookInDatabase = true;
                } else {
                    activityDetailBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailActivity.this, R.color.colorAccent));
                    isBookInDatabase = false;
                }
            }
        });
    }
}