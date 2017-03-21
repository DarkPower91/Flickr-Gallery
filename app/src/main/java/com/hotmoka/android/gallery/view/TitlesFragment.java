package com.hotmoka.android.gallery.view;

import android.annotation.TargetApi;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.UiThread;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;
import com.hotmoka.android.gallery.model.Pictures;

import static com.hotmoka.android.gallery.model.Pictures.Event.PICTURES_LIST_CHANGED;

/**
 * A fragment containing the titles of the Flickr Gallery app.
 * Titles can be clicked to show their corresponding picture.
 * Titles can be reloaded through a menu item.
 */
public abstract class TitlesFragment extends ListFragment
        implements GalleryFragment {

    //private ShareActionProvider mShare;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Show the titles, or the empty list if there is none yet
        String[] titles = MVC.model.getTitles();
        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                titles == null ? new String[0] : titles));

        // If no titles exist yet, ask the controller to reload them
        if (titles == null) {
            ((GalleryActivity) getActivity()).showProgressIndicator();
            MVC.controller.onTitlesReloadRequest(getActivity());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This fragment uses menus
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       // inflater.inflate(R.menu.fragment_titles, menu);
        //menu.removeItem(R.id.menu_item_share);
    }
/*
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
/*
    @TargetApi(14)
    private void setShareIntent(Intent shareIntent){
       // if(mShare!=null){
            mShare.setShareIntent(shareIntent);
       // }
    }
*/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Delegate to the controller
        MVC.controller.onTitleSelected(position);
    }

    @Override @UiThread
    public void onModelChanged(Pictures.Event event) {
        if (event == PICTURES_LIST_CHANGED)
            // Show the new list of titles
            setListAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_activated_1,
                    MVC.model.getTitles()));
    }
}