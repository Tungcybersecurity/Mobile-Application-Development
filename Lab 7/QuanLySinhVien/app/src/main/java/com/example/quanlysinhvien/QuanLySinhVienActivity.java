package com.example.quanlysinhvien;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlysinhvien.adapter.LopHocAdapter;
import com.example.quanlysinhvien.adapter.SinhVienAdapter;
import com.example.quanlysinhvien.helper.DateTimeHelper;
import com.example.quanlysinhvien.model.LopHoc;
import com.example.quanlysinhvien.model.SinhVien;
import com.example.quanlysinhvien.sqlite.LopHocDAO;
import com.example.quanlysinhvien.sqlite.SinhVienDAO;

import java.util.List;

public class QuanLySinhVienActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtMaSV, edtHotenSV, edtNgaySinhSV;
    ListView lvDanhsachSinhvien;
    Spinner spLopHoc;
    SinhVienAdapter sinhVienAdapter;
    private List<LopHoc> lopHocList;
    private List<SinhVien> sinhVienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_sinh_vien);

        findViewById(R.id.btnLuuSinhVien).setOnClickListener(this);
        findViewById(R.id.btnThoatSinhVien).setOnClickListener(this);
        edtMaSV = findViewById(R.id.edtMaSV);
        edtHotenSV = findViewById(R.id.edtHotenSV);
        edtNgaySinhSV = findViewById(R.id.edtNgaySinhSV);
        spLopHoc = findViewById(R.id.spLopHoc);
        fillLopHocToSpinner();
        lvDanhsachSinhvien = findViewById(R.id.lvDanhsachSinhvien);
        fillLopHocToListView();

        spLopHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillLopHocToListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quanLySinhVienActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fillLopHocToListView() {
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        try {
            int lopHocid = lopHocList.get(spLopHoc.getSelectedItemPosition()).getId();
            sinhVienList = sinhVienDAO.getAllByLophoc(lopHocid);
            sinhVienAdapter = new SinhVienAdapter(this, sinhVienList);
            lvDanhsachSinhvien.setAdapter(sinhVienAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillLopHocToSpinner() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        LopHocAdapter lopHocAdapter = new LopHocAdapter(this, lopHocList);
        spLopHoc.setAdapter(lopHocAdapter);
    }

    @Override
    public void onClick(View view) {
        SinhVienDAO sinhVienDAO = new SinhVienDAO(this);
        switch (view.getId()) {
            case R.id.btnLuuSinhVien:
                try {
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setId(edtMaSV.getText().toString());
                    sinhVien.setHoten(edtHotenSV.getText().toString());
                    sinhVien.setNgaysinh(DateTimeHelper.toDate(edtNgaySinhSV.getText().toString()));
                    Toast.makeText(this, "AAA", Toast.LENGTH_LONG).show();
                    int lopHoc = spLopHoc.getSelectedItemPosition();
                    sinhVien.setLophocid(lopHocList.get(lopHoc).getId());
                    Toast.makeText(this, "" + lopHocList.get(lopHoc).getId(), Toast.LENGTH_LONG).show();
                    sinhVienDAO.insert(sinhVien);
                    Toast.makeText(this, "Sinh viên đã được lưu", Toast.LENGTH_LONG).show();
                    fillLopHocToListView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case R.id.btnThoatSinhVien:
                finish();
                break;
        }
    }

}