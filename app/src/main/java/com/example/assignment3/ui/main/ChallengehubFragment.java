package com.example.assignment3.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.CampaignActivity;
import com.example.assignment3.MainActivity;
import com.example.assignment3.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ChallengehubFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;
    public static ListView Categories;          //< Categories List
    public static ListAdapter Adapter;          //< Adapter to populate the category list
    private int mCategoryCount;                 //< Number of categories
    private final String[] categoryStrings = {  //< Category titles
            "Motor power and timer",
            "Motor direction and colour",
            "Music and Display",
            "Blank key and loop",
            "Message send and receive",
            "Sound Sensor part 1",
            "Sound Sensor part 2",
            "Display Input"
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
        mCategoryCount = categoryStrings.length;
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
        Categories = root.findViewById(R.id.listView);
        Adapter = new CategoryAdapter();
        Categories.setAdapter(Adapter);
        Categories.setOnItemClickListener((parent, view, position, id) -> openCategoryChallenges(position));
        return root;
    }

    //Populates listview
    public class CategoryAdapter extends BaseAdapter {
        class ViewHolder {
            public int position;
            public TextView category;
        }
        @Override
        public int getCount() {
            return mCategoryCount;
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
            if(i >= MainActivity.getLevel()) {
                //If locked
                vh.category.setBackgroundColor(Color.argb(128,255,0,0));
            } else {
                //If unlocked
                vh.category.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            return convertView;
        }
    }
    //Opens next level challenge when button is clicked
    public void openCategoryChallenges(int position){
        if(position < MainActivity.getLevel()) {
            //If level is unlocked, open it
            Intent intent = new Intent(getContext(), CampaignActivity.class);
            intent.putExtra("Level", position);
            startActivity(intent);
        } else {
            //If locked, notify user
            Toast toast = Toast.makeText(getContext(),"LOCKED! Complete prior levels to unlock.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
