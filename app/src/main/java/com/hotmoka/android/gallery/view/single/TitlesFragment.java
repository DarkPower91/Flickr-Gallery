package com.hotmoka.android.gallery.view.single;

import android.annotation.TargetApi;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;
import com.hotmoka.android.gallery.view.GalleryActivity;

/**
 * The titles fragment for a single pane layout.
 */
public class TitlesFragment extends com.hotmoka.android.gallery.view.TitlesFragment {
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.fragment_titles, menu);
        menu.removeItem(R.id.menu_item_share);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @TargetApi(14)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_load) {
            android.util.Log.v("Load", "touched load");
            ((GalleryActivity) getActivity()).showProgressIndicator();
            MVC.controller.onTitlesReloadRequest(getActivity());
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}

