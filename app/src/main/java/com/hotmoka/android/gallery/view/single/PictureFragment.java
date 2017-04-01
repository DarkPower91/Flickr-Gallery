package com.hotmoka.android.gallery.view.single;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;

/**
 * The picture fragment for a single pane layout.
 * It adds to ability to create the fragment programmatically
 * and redefines the behavior at picture show up by also
 * reporting the title of the picture below it.
 */
public class PictureFragment extends com.hotmoka.android.gallery.view.PictureFragment {

    /**
     * Convenience method to create a fragment that shows the picture
     * for the title corresponding to the given position.
     *
     * @param position
     * @return the fragment that has been created
     */

    @UiThread
    public static PictureFragment mkInstance(int position) {
        PictureFragment fragment = new PictureFragment();
        fragment.init(position);

        return fragment;
    }

    @Override @UiThread
    protected boolean showBitmapIfDownloaded(int position) {
        boolean shown = super.showBitmapIfDownloaded(position);
        // If the picture has been shown, report its title below it
        if (shown)
            ((TextView) getView().findViewById(R.id.picture_title))
                    .setText(MVC.model.getTitles()[position]);

        return shown;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_titles, menu);
        menu.removeItem(R.id.menu_item_load);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @TargetApi(14)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.menu_item_share && MVC.controller.isIdle()){
                ImageView image=(ImageView)getView().findViewById(R.id.picture);
                TextView text=(TextView) getView().findViewById(R.id.picture_title);
                startActivity(Intent.createChooser(MVC.controller.shareImage(text,image), "Share Image"));
                return true;
            }else
                return super.onOptionsItemSelected(item);
    }

}
