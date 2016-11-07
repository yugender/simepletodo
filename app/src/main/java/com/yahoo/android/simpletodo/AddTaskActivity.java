package com.yahoo.android.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTaskActivity extends AppCompatActivity {
    EditText etTaskName;
    Spinner spinnerPriorityLevel;
    String priorityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTaskName = (EditText) findViewById(R.id.etTaskName);
        spinnerPriorityLevel = (Spinner) findViewById(R.id.spinnerPriorityLevel);
        addListenerOnSpinnerItemSelection();
    }

    private void addListenerOnSpinnerItemSelection() {
        spinnerPriorityLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                priorityLevel = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void onAddTask(View view) {
        String taskName = etTaskName.getText().toString();
        Task task = new Task();
        task.setName(taskName);
        task.setPriority(this.priorityLevel);
        Intent data = new Intent();
        data.putExtra("task", task);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
