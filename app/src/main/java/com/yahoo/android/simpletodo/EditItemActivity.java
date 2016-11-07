package com.yahoo.android.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
    EditText etEditTask;
    int index;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etEditTask = (EditText) findViewById(R.id.etEditItem);
        //String task = getIntent().getStringExtra("task");
        task = (Task) getIntent().getSerializableExtra("task");
        index = getIntent().getIntExtra("index", -1);
        etEditTask.setText(task.getName());
    }

    public void onSaveEdit(View view) {
        String taskName = etEditTask.getText().toString();
        //Task task = new Task();
        task.setName(taskName);
        //task.setPriority(this.task.getPriority());
        Intent data = new Intent();
        data.putExtra("task", task);
        data.putExtra("index", index);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
