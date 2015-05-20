package com.beli.probro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.beli.probro.Databases.Tasks;
import com.beli.probro.R;

import java.util.List;

/**
 * Created by allmine on 4/17/15.
 */
public class TaskAdapter extends ArrayAdapter<Tasks> {
    Context context;
    List<Tasks> data;
    private /*static*/ LayoutInflater inflater = null;

    public TaskAdapter(Context context, List<Tasks> data) {
        // TODO Auto-generated constructor stub
        super(context, R.layout.task_list_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

//    @Override
//    public String getItem(int position) {
//        return super.getItem(position).toString();
//    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.task_list_item, null);

        TextView text = (TextView) vi.findViewById(R.id.task_name);
        text.setText(data.get(position).toString());
//        text.setText(data[position]);
        return vi;
    }
}
