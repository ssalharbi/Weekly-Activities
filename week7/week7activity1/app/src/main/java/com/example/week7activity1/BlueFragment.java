package com.example.week7activity1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlueFragment extends Fragment {

    // this fragment shows a ListView
    MainActivity main;
    Context context = null;
    String message = "";
    // data to fill-up the ListView
    private String items[] = {"Text-on-Line-00", "Text-on-Line-01", "Text-on-Line-02", "Text-on-Line-03", "Text-on-Line-04", "Text-on-Line-05", "Text-on-Line-06", "Text-on-Line-07", "Text-on-Line-08", "Text-on-Line-09", "Text-on-Line-10",};

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static BlueFragment newInstance(String strArg) {
        BlueFragment fragment = new BlueFragment();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // inflate res/layout_blue.xml to make GUI holding a TextView and a ListView
        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);
// plumbing – get a reference to textview and listview
        final TextView txtBlue = (TextView) layout_blue.findViewById(R.id.textView1Blue);
        ListView listView = (ListView) layout_blue.findViewById(R.id.listView1Blue);
        listView.setBackgroundColor(Color.parseColor("#ffccddff"));
// define a simple adapter to fill rows of the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
// show listview from the top
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);
// react to click events on listview’s rows
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
// inform enclosing MainActivity of the row’s position just selected
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + position);
                txtBlue.setText("Blue selected row=" + position);
            }
        });
// do this for each row (ViewHolder-Pattern could be used for better performance!)
        return layout_blue;
    }// onCreateView
}