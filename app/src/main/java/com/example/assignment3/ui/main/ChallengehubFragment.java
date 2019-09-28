package com.example.assignment3.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment3.FreePlayActivity;
import com.example.assignment3.R;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ChallengehubFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private ListView categories;
    private ListAdapter adapter;
    private int num;
    private final String[] categoryStrings = {
            "Motor",
            "Display",
            "Control",
            "Motor",
            "Display",
            "Control",
            "Motor",
            "Display",
            "Control",
            "Motor",
            "Display",
            "Control"
    };

    public static ChallengehubFragment newInstance(int index) {
        ChallengehubFragment fragment = new ChallengehubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num = categoryStrings.length;

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_challengehub, container, false);
        categories = root.findViewById(R.id.listView);
        adapter = new CategoryAdapter();
        categories.setAdapter(adapter);
        categories.setOnItemClickListener((parent, view, position, id) -> openCategoryChallenges(position));
        return root;
    }

    //Populates listview
    public class CategoryAdapter extends BaseAdapter {
        class ViewHolder {
            int position;
            TextView category;
        }
        @Override
        public int getCount() {
            return num;
        }
        //Not used
        @Override
        public Object getItem(int i) {
            return null;
        }
        //Not used
        @Override
        public long getItemId(int i) {
            return i;
        }

        //Populates a view
        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            ViewHolder vh;
            if (convertView == null) {
                //If not recycled, inflate from xml
                convertView = getLayoutInflater().inflate(R.layout.category,  viewGroup, false);
                //Create Viewholder for it
                vh=new ViewHolder();
                vh.category=convertView.findViewById(R.id.categoryItem);
                convertView.setTag(vh);
            } else
                vh=(ViewHolder)convertView.getTag();        //Otherwise get the viewHolder
            //Sets position
            vh.position = i;
            vh.category.setText(categoryStrings[i]);
                //Make an AsyncTask to load the image
//            new AsyncTask<ViewHolder, Void, Bitmap>() {
//                private ViewHolder vh;
//                @Override
//                protected Bitmap doInBackground(ViewHolder... params) {
//
//                }
//
//                @Override
//                protected void onPostExecute(Bitmap bmp) {
//                    //Only set the imageview if the position hasn't changed.
//                    if (vh.position == i) {
//                        vh.image.setImageBitmap(bmp);
//                    }
//                    //Add image to cache for later retrieval
//                    addImageToCache(Integer.toString(i),bmp);
//                }
//            }.executeOnExecutor(executor,vh);
            return convertView;
        }
    }

    public void openCategoryChallenges(int position){
        Intent intent = new Intent(getContext(), FreePlayActivity.class);           //TEMPORARY
        startActivity(intent);
    }
}
