package sg.edu.rp.c346.id21045028.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd;
    Button btnRem;
    Button btnDel;
    ListView lvTasks;
    Spinner spinner;
    ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.EditTasksText);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRem = findViewById(R.id.buttonRemove);
        btnDel = findViewById(R.id.buttonDelete);
        lvTasks = findViewById(R.id.listViewTasks);
        spinner = findViewById(R.id.spinner);

        tasks = new ArrayList<String>();

        ArrayAdapter tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,tasks);

        lvTasks.setAdapter(tasksAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position , long id){
                switch(position){
                    case 0:
                        etElement.setHint(getString(R.string.edithint));
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        etElement.getText().clear();
                        etElement.setInputType( InputType.TYPE_CLASS_TEXT );
                        break;
                    case 1:
                        etElement.setHint(getString(R.string.edithint2));
                        btnDel.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etElement.getText().clear();
                        etElement.setText("0");
                        etElement.setInputType( InputType.TYPE_CLASS_NUMBER );
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addtask = etElement.getText().toString();
                tasks.add(addtask);
                tasksAdapter.notifyDataSetChanged();
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.clear();
                tasksAdapter.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = etElement.getText().toString();
                int pos = Integer.parseInt(number);
                if(tasks.isEmpty()){
                    Toast.makeText(getApplicationContext(),"There is nothing in the array to delete",Toast.LENGTH_SHORT).show();
                }else if(pos > tasks.size() || pos <= 0){
                    Toast.makeText(getApplicationContext(),"The number entered is smaller than the array or bigger",Toast.LENGTH_SHORT).show();
                }else{
                    tasks.remove((pos-1));
                    tasksAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}