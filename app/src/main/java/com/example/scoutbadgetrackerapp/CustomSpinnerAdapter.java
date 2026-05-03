package com.example.scoutbadgetrackerapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
//    Used to allow for spinner customisation.
    private Context context;
    private List<String> values;
    private int customLayoutId;

    public CustomSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
        this.customLayoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    private View createCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(customLayoutId, parent, false);
        TextView textView = view.findViewById(R.id.spinnerTextView);
        textView.setText(values.get(position));
        return view;
    }
}
