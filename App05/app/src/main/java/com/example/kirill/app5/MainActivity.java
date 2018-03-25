package com.example.kirill.app5;

import android.app.ListActivity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1, R.id.textView,
                getResources().getStringArray(R.array.images)));
    }

    public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, int textViewResourceId, String[] string){
            super(context, resource, textViewResourceId, string);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_item, parent, false);
            String[] items = getResources().getStringArray(R.array.images);
            ImageView image = (ImageView) row.findViewById(R.id.imageView);
            TextView text = (TextView) row.findViewById(R.id.textView);
            text.setText(items[position]);

            switch (items[position]) {
                case "Moscow":
                    image.setImageResource(R.drawable.moscow);
                    break;
                case "Saint-Petersburg":
                    image.setImageResource(R.drawable.spb);
                    break;
                case "Tomsk":
                    image.setImageResource(R.drawable.tomsk);
                    break;
                case "Sochi":
                    image.setImageResource(R.drawable.sochi);
                    break;
            }

            return row;
        }
    }
}
