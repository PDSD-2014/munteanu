package utils;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.learngerman.R;

public class MyAdapter extends ArrayAdapter<String> {

    private ArrayList<String> items;
    public static HashSet<String> selected = new HashSet<>();
    Context context;

    private OnClickListener pressed = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked())
                selected.add(items.get((Integer)v.getTag()));
            else
                selected.remove(items.get((Integer)v.getTag()));

        }
    };

    public MyAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
            super(context, textViewResourceId, items);
            this.context = context;
            this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_list, null);
            }
            String o = items.get(position);
            if (o != null) {
                    TextView tt = (TextView) v.findViewById(R.id.toptext);
//                    TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                    if (tt != null) {
                          tt.setText(o);
                    }
//                    if(bt != null){
//                          bt.setText(o.description + "Pretul: " + o.price + " RON");
                    //}
            }
            CheckBox chkSelected = (CheckBox) v.findViewById(R.id.chkBox);
            chkSelected.setTag(position);
//            chkSelected.setChecked(o.selected);
            chkSelected.setOnClickListener(pressed);
            return v;
    }
}
