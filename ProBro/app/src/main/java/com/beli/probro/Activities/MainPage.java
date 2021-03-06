package com.beli.probro.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beli.probro.Adapter.TaskAdapter;
import com.beli.probro.Databases.MySQLiteHelper;
import com.beli.probro.Databases.TaskDataSource;
import com.beli.probro.Databases.Tasks;
import com.beli.probro.Fragments.CreateNewFragment;
import com.beli.probro.R;

import java.util.List;


public class MainPage extends ActionBarActivity {

    private TaskDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.container, new PlaceholderFragment()).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        datasource = new TaskDataSource(this);
        datasource.open();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Get the current instance of this list adapter
        ListView lv = (ListView) findViewById(android.R.id.list);
//        Tasks newTask = null;

        TaskAdapter taskAdapter = null;

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.new_task) {
            fragmentTransaction.replace(R.id.container, new CreateNewFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //Needs to go inside of the new fragment
//            newTask = datasource.createTask("Eat", 12);
//            taskAdapter = (TaskAdapter) lv.getAdapter();
//            taskAdapter.add(newTask);
        }

//        taskAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends ListFragment {

        private TaskDataSource datasource;

        public PlaceholderFragment() {
        }

//        @Override
//        public void onActivityCreated(Bundle savedInstanceState) {
//            super.onActivityCreated(savedInstanceState);
//
//            //Create new datasource object then open database to work with it
//            datasource = new TaskDataSource(getActivity());
//            datasource.open();
//            //Get all tasks already in database and put in list
//            List<Tasks> values = datasource.getAllTasks();
//
//            TaskAdapter adapter = new TaskAdapter(getActivity(), values);
//            setListAdapter(adapter);
//        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return super.onCreateView(inflater, container, savedInstanceState);
//            view = inflater.inflate(R.layout.fragment_, container, false);
//            return view;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            //Create new datasource object then open database to work with it
            datasource = new TaskDataSource(getActivity());
            datasource.open();
            //Get all tasks already in database and put in list
            List<Tasks> values = datasource.getAllTasks();

            TaskAdapter adapter = new TaskAdapter(getActivity(), values);
            setListAdapter(adapter);
        }

        private ArrayAdapter<Tasks> GetListAdapter() {
//            TaskAdapter adapter = (TaskAdapter) getListAdapter();
            ArrayAdapter<Tasks> adapter = (ArrayAdapter<Tasks>) getListAdapter();
            return adapter;
        }
    }
}
