package com.hotmoka.android.gallery.view;


import android.app.ListFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.model.Pictures;

import static com.hotmoka.android.gallery.model.Pictures.Event.*;

/**
 * A fragment containing the titles of the Flickr Gallery app.
 * Titles can be clicked to show their corresponding picture.
 * Titles can be reloaded through a menu item.
 */
public abstract class TitlesFragment extends ListFragment
        implements GalleryFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Show the titles, or the empty list if there is none yet
        String[] titles = MVC.model.getTitles();
        setListAdapter(new PictureTextList(getActivity(),titles == null ? new String[0]:titles,titles == null ? new Bitmap[0]:new Bitmap[titles.length]));
        // If no titles exist yet, ask the controller to reload them
        if (titles == null) {
            ((GalleryActivity) getActivity()).showProgressIndicator();
            MVC.controller.onTitlesReloadRequest(getActivity());
        }
        else
        {
            setListAdapter(new PictureTextList(getActivity(), MVC.model.getTitles(), MVC.model.getLowResBitmaps()));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This fragment uses menus
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Delegate to the controller
        MVC.controller.onTitleSelected(position);
    }

    @Override @UiThread
    public void onModelChanged(Pictures.Event event) {
        if (event == PICTURES_LIST_CHANGED) {
            String[] titles = MVC.model.getTitles();
            // Show the new list of titles
            setListAdapter(new PictureTextList(getActivity(), titles == null ? new String[0] : titles, titles == null ? new Bitmap[0]:new Bitmap[titles.length]));
        }
        if(event == LOWRES_BITMAP_CHANGED){
            ((PictureTextList) getListAdapter()).setImages(MVC.model.getLowResBitmaps());

        }
    }
}