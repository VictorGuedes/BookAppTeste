package com.example.android.bookappteste.ui.bindingadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.ui.activities.DetailActivity;

import java.io.Serializable;

public class BindingAdapterLayout {

    public static final String BOOK = "BOOK";

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("bookOnClick")
    public static void onBookClick(ImageView imageView, Item book){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = imageView.getContext();
                Intent intent = new Intent(context, DetailActivity.class);

                if (view.getContext() instanceof Activity) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context,
                            imageView,
                            ViewCompat.getTransitionName(imageView)
                    );
                    book.setBookInDatabase(false);
                    intent.putExtra(BOOK, (Serializable) book);
                    context.startActivity(intent, options.toBundle());
                } else {
                    book.setBookInDatabase(true);
                    intent.putExtra(BOOK, (Serializable) book);
                    context.startActivity(intent);
                }
            }
        });

    }

}
