package com.yahoo.android.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 20;
    private final int REQUEST_CODE_ADD = 30;
    ArrayList<Task> tasks;
    TasksAdapter tasksAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        lvItems = (ListView) findViewById(R.id.lvItems);
        getToDoItems();
        lvItems.setAdapter(tasksAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.remove(i);
                tasksAdapter.notifyDataSetChanged();
                task.delete();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), EditItemActivity.class);
                intent.putExtra("task", tasks.get(i));
                intent.putExtra("index", i);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    public void getToDoItems() {
        tasks = new ArrayList<>();
        List<Task> taskList = SQLite.select().from(Task.class).queryList();
        if (taskList != null) {
            tasks.addAll(taskList);
        }
        tasksAdapter = new TasksAdapter(this, tasks);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                int index = data.getIntExtra("index", -1);
                Task task = (Task) data.getSerializableExtra("task");
                if (index != -1) {
                    tasks.set(index, task);
                    tasksAdapter.notifyDataSetChanged();
                    task.save();
                }
            } else if (requestCode == REQUEST_CODE_ADD) {
                Task task = (Task) data.getSerializableExtra("task");
                tasksAdapter.add(task);
                task.save();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Intent  i =  new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(i, REQUEST_CODE_ADD);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
