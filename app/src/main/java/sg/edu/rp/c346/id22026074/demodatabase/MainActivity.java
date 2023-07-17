package sg.edu.rp.c346.id22026074.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask, etDate;
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
//        final int[] insertCounter = {0};

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                insertCounter[0]++;
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());
                etTask.setText(null);
                etDate.setText(null);

                // Retrieve tasks from the database
//                ArrayList<Task> tasks = db.getTasks();
                db.close();

//                // Add tasks to the taskList
//                for (int i = 0; i < tasks.size(); i++) {
//                    Task task = tasks.get(i);
//                    String taskString = (insertCounter[0] + i) + " (" + task.getId() + ")\n" +
//                            task.getDescription() + "\n" +
//                            task.getDate() + "\n" +
//                            "MainActivity.java";
//                    al.add(taskString);
//                }
//
//                // Notify the ArrayAdapter that the data has changed
//                aa.notifyDataSetChanged();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();

                ArrayList<Task> data1 = db.getTasks();

                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }

                al.clear();

                tvResults.setText(txt);


                al.addAll(data1);
                aa.notifyDataSetChanged();
            }
        });

    }
}