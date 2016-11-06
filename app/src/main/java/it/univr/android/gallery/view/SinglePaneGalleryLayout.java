package it.univr.android.gallery.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import it.univr.android.gallery.R;
import it.univr.android.gallery.model.Pictures;

public class SinglePaneGalleryLayout extends FrameLayout implements GalleryLayout {

    private FragmentManager getFragmentManager() {
        return ((Activity) getContext()).getFragmentManager();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Pictures.registerView(this);
        FragmentManager fragmentManager = getFragmentManager();
        // Show the titles fragment at start
        if (fragmentManager.findFragmentById(R.id.gallery_layout_container) == null)
            fragmentManager.beginTransaction()
                    .add(R.id.gallery_layout_container, new TitlesFragment()).commit();
    }

    @Override
    protected void onDetachedFromWindow() {
        Pictures.unregisterView(this);
        super.onDetachedFromWindow();
    }

    @Override
    public void onTitleSelected(int position) {
        // Create fragment and give it an argument for the selected picture
        getFragmentManager().beginTransaction()
            // Replace whatever is in the fragment_container view with this fragment
            .replace(R.id.gallery_layout_container, PictureFragment.mkInstance(position))
            // Add the transaction to the back stack so the user can navigate back
            .addToBackStack(null)
            // Commit the transaction
            .commit();
    }

    @Override
    public void onModelChanged() {
    }

    public SinglePaneGalleryLayout(Context context) {
        super(context);
    }
    public SinglePaneGalleryLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}