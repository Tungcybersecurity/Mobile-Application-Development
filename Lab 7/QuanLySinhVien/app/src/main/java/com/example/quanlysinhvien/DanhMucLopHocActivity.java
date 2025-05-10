package com.example.quanlysinhvien;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlysinhvien.adapter.LopHocAdapter;
import com.example.quanlysinhvien.model.LopHoc;
import com.example.quanlysinhvien.sqlite.LopHocDAO;

import java.util.List;

public class DanhMucLopHocActivity extends AppCompatActivity implements View.OnClickListener {

    TextView edtTenLopHoc;

    ListView lvDanhSachLopHoc;

    List<LopHoc> lopHocList;

    LopHocAdapter lopHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_muc_lop_hoc);

        findViewById(R.id.btnLuuLopHoc).setOnClickListener(this);
        findViewById(R.id.btnThoatLopHoc).setOnClickListener(this);
        edtTenLopHoc = findViewById(R.id.edtTenLopHoc);
        lvDanhSachLopHoc = findViewById(R.id.lvdanhsachlophoc);
        fillLopHocListView();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.danhMucLopHocActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLuuLopHoc:
                LopHoc lopHoc = new LopHoc();
                lopHoc.setTenlophoc(edtTenLopHoc.getText().toString());
                LopHocDAO lopHocDAO = new LopHocDAO(this);
                lopHocDAO.insert(lopHoc);
                fillLopHocListView();
                Toast.makeText(this, "Đã lưu lớp học", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnThoatLopHoc:
                finish();
                break;
        }
    }

    private void fillLopHocListView() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        lopHocAdapter = new LopHocAdapter(this, lopHocList);
        lvDanhSachLopHoc.setAdapter(lopHocAdapter);
        lvDanhSachLopHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                LopHocDAO lopHocDAO1 = new LopHocDAO(DanhMucLopHocActivity.this);
                LopHoc lopHoc = lopHocList.get(i);
                lopHocDAO.delete(lopHoc.getId());
                fillLopHocListView();
                return false;
            }
        });
    }
}