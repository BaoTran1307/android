package com.baotran.k22411c_firstdegree;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

 //Khai báo các biến để quản lý các ô nhớ của các view
    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 👉 Load ngôn ngữ đã lưu
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = prefs.getString("My_Lang", "en");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // 👉 Gọi super
        super.onCreate(savedInstanceState);

        // 👉 Thiết lập giao diện và các view
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addViews();

        // 👉 Thiết lập padding cho hệ thống (nếu cần)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void addViews() {
        edtCoefficientA= findViewById(R.id.edtCoefficientA);
        edtCoefficientB= findViewById(R.id.edtCoefficientB);
        txtResult=findViewById(R.id.txtResult);
    }

    public void do_solution(View view) {

        /// Lấy hệ số a trên giao diện
        String hsa=edtCoefficientA.getText().toString();
        double a=Double.parseDouble(hsa);
        /// Lấy hệ số b trên giao diện
        double b=Double.parseDouble(edtCoefficientB.getText().toString());
        if (a==0 && b==0)
        {
//            txtResult.setText("Infinity");
            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if(a==0 && b!=0)
        {
//            txtResult.setText("No Solution!");
            txtResult.setText(getResources().getText(R.string.title_no_solution));

        }
        else
        {
            double x=-b/a;
            txtResult.setText("x="+x);
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        //di chuyển con trỏ nhập liệu vào HSA để nhập cho lẹ
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
    // ---------- Đổi ngôn ngữ ----------
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Lưu vào SharedPreferences để nhớ ngôn ngữ
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        prefs.edit().putString("My_Lang", lang).apply();

        recreate(); // Áp dụng lại Activity
    }

    public void setLangVi(View view) {
        setLocale("vi");
    }

    public void setLangEn(View view) {
        setLocale("en");
    }

    public void setLangFr(View view) {
        setLocale("fr");
    }

    public void setLangEs(View view) {
        setLocale("es");
    }

}