package com.example.demolistviewcoban;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayMonhoc;
    ArrayAdapter<String> adapter;
    ListView lsView;
    Button btnThem, btnCapnhat;
    EditText editText;
    int pos = -1; // Khởi tạo mặc định là -1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        lsView = findViewById(R.id.lsView);
        btnThem = findViewById(R.id.btnThem);
        btnCapnhat = findViewById(R.id.btnCapnhat);
        editText = findViewById(R.id.edText);

        // Khởi tạo dữ liệu
        arrayMonhoc = new ArrayList<>();
        arrayMonhoc.add("Android");
        arrayMonhoc.add("Java");
        arrayMonhoc.add("PHP");
        arrayMonhoc.add("Hadoop");
        arrayMonhoc.add("Sap");
        arrayMonhoc.add("Python");
        arrayMonhoc.add("Ajax");
        arrayMonhoc.add("C++");
        arrayMonhoc.add("Ruby");
        arrayMonhoc.add("Rails");

        // Khởi tạo adapter
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayMonhoc
        );
        lsView.setAdapter(adapter);

        // Thêm môn học
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayMonhoc.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        // Cập nhật môn học
        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos != -1) {
                    arrayMonhoc.set(pos, editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    pos = -1;
                }
            }
        });

        // Xử lý khi click item
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(arrayMonhoc.get(i));
                pos = i;
            }
        });

        // Xử lý khi long click item (xoá)
        lsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayMonhoc.get(i), Toast.LENGTH_LONG).show();
                arrayMonhoc.remove(i);
                adapter.notifyDataSetChanged();
                return true; // nên trả về true để không gọi tiếp onItemClick
            }
        });
    }
}
