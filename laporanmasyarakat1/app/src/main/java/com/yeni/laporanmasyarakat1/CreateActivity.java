package com.yeni.laporanmasyarakat1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yeni.laporanmasyarakat1.model.Report;

public class CreateActivity extends AppCompatActivity {

    private DatabaseReference database;
    private Button btSubmit;
    private EditText ettitle;
    private EditText etasal;
    private EditText etreport;
    private Report report = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ettitle = (EditText) findViewById(R.id.et_title);
        etasal = (EditText) findViewById(R.id.et_asal);
        etreport = (EditText) findViewById(R.id.et_report);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://laporanmasyarakat1-616c4-default-rtdb.firebaseio.com/");
        report = (Report) getIntent().getSerializableExtra("data");
        if (report != null) {
            ettitle.setText(report.getTitle());
            etasal.setText(report.getAsal());
            etreport.setText(report.getReport());
        }
    }
    public void save(View v) {
        if (report != null) {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    report.setTitle(ettitle.getText().toString());
                    report.setAsal(etasal.getText().toString());
                    report.setReport(etreport.getText().toString());
                    updateReport(report);
                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(ettitle.getText().toString()) &&  !isEmpty(etasal.getText().toString())
                            && !isEmpty(etreport.getText().toString()))
                            submitReport(new Report(ettitle.getText().toString(),
                                    etasal.getText().toString(), etreport.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data report tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            ettitle.getWindowToken(), 0);
                }
            });
        }
    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }
    private void updateReport(Report report) {
        database.child("report")
                .child(report.getKey())
                .setValue(report)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override

                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil diupdatekan",
                                Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }
    private void submitReport(Report report) {
        database.child("report").push().setValue(report).addOnSuccessListener(this, new
                OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ettitle.setText("");
                        etasal.setText("");
                        etreport.setText("");
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, CreateActivity.class);
    }
}