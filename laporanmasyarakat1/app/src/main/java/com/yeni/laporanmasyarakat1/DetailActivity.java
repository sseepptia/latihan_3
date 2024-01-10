package com.yeni.laporanmasyarakat1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.yeni.laporanmasyarakat1.model.Report;

public class DetailActivity extends AppCompatActivity {
    private Button btSubmit;
    private EditText etTitle;
    private EditText etAsal;
    private EditText etReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etTitle = (EditText) findViewById(R.id.et_title);
        etAsal = (EditText) findViewById(R.id.et_asal);
        etReport = (EditText) findViewById(R.id.et_report);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        etTitle.setEnabled(false);
        etAsal.setEnabled(false);
        etReport.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Report report = (Report) getIntent().getSerializableExtra("report");
        if(report!=null){
            etTitle.setText(report.getTitle());
            etAsal.setText(report.getAsal());
            etReport.setText(report.getReport());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailActivity.class);
    }
}