package com.hotmoka.android.gallery.view.two;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;
import com.hotmoka.android.gallery.view.GalleryActivity;

/**
 * The titles fragment for a two panes layout. It modifies the standard
 * behavior by making the selected item remain highlighted.
 */
public class TitlesFragment extends com.hotmoka.android.gallery.view.TitlesFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Make the clicked item remain visually highlighted
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Keep the selected item checked also after click
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_titles, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @TargetApi(14)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_load) {
            android.util.Log.v("Load","touched load");
            ((GalleryActivity) getActivity()).showProgressIndicator();
            MVC.controller.onTitlesReloadRequest(getActivity());
            return true;
        }
        else {
            if(item.getItemId() == R.id.menu_item_share){
                //MenuItem shareItem = item.findItem(R.id.menu_item_share);
                // MenuItem shareItem= item;
                // mShare =(ShareActionProvider) shareItem.getActionProvider();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "culo");
                shareIntent.setType("text/plain");
                //setShareIntent(shareIntent);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                return true;
            }else
                return super.onOptionsItemSelected(item);
        }
    }
}
