package com.hotmoka.android.gallery.view.two;

import android.annotation.TargetApi;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;
import com.hotmoka.android.gallery.view.GalleryActivity;

/**
 * The picture fragment for a two panes layout.
 */
public class PictureFragment extends com.hotmoka.android.gallery.view.PictureFragment {

    @TargetApi(14)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_share){
            ImageView image=(ImageView)getView().findViewById(R.id.picture);
            startActivity(Intent.createChooser(MVC.controller.shareImage(image), "Share Image"));
            return true;
        }
        else
            return super.onOptionsItemSelected(item);

    }
}
