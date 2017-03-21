package com.hotmoka.android.gallery.view.single;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.UiThread;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotmoka.android.gallery.MVC;
import com.hotmoka.android.gallery.R;
import com.hotmoka.android.gallery.view.GalleryActivity;

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

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
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
            if(item.getItemId() == R.id.menu_item_share){
                //MenuItem shareItem = item.findItem(R.id.menu_item_share);
                // MenuItem shareItem= item;
                // mShare =(ShareActionProvider) shareItem.getActionProvider();
                Drawable img=((ImageView)getView().findViewById(R.id.picture)).getDrawable();
                Bitmap bitmap=drawableToBitmap(img);
                if(img==null){
                    android.util.Log.v("boh","ok");
                }
                android.util.Log.v("boh","me");

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
