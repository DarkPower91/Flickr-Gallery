package com.hotmoka.android.gallery.view;

import android.app.Activity;
import android.database.AbstractCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hotmoka.android.gallery.R;

/**
 * Created by Alessio on 21/03/2017.
 */

public class PictureTextList extends ArrayAdapter<String> {

    private final Activity context;
    private final String [] names;
    private final Integer[] imageId;

    public PictureTextList (Activity context, String[] names, Integer[] imageId)
    {
        super(context, R.layout.list_single, names);
        this.context = context;
        this.names = names;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view , ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(names[position]);

        try {
            imageView.setImageResource(imageId[position]);
            imageView.setVisibility(View.VISIBLE);
            rowView.findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        }
        catch(RuntimeException e){
            imageView.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }
}
