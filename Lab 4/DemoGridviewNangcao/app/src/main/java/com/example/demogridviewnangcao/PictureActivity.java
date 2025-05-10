package com.example.demogridviewnangcao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PictureActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_picture);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        HinhAnh hinhAnh = (HinhAnh)bundle.getSerializable("ha");
        imageView.setImageResource(hinhAnh.getHinh());
    }
    public void BackActivity(View view) {
        finish();
    }
}