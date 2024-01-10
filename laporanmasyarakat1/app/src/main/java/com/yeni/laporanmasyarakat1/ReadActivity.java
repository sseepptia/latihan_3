package com.yeni.laporanmasyarakat1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yeni.laporanmasyarakat1.model.AdapterReportRecyclerView;
import com.yeni.laporanmasyarakat1.model.Report;
import java.util.ArrayList;
public class ReadActivity extends AppCompatActivity implements
        AdapterReportRecyclerView.FirebaseDataListener {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Report> listReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("report").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listReport = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Report report = noteDataSnapshot.getValue(Report.class);
                    report.setKey(noteDataSnapshot.getKey());

                    listReport.add(report);
                }

                adapter = new AdapterReportRecyclerView(listReport,
                        ReadActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, ReadActivity.class);
    }

    @Override
    public void onDeleteData(Report report, final int position) {

        if (database != null) {
            database.child("report").child(report.getKey()).removeValue().addOnSuccessListener
                    (new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ReadActivity.this, "success delete",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}

