package com.yeni.laporanmasyarakat1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseActivity extends AppCompatActivity {

    private Button btCreateDB;
    private Button btViewDB;
    private Button btLogout;
    private TextView tvUser;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btCreateDB = (Button) findViewById(R.id.bt_createdata);
        btViewDB = (Button) findViewById(R.id.bt_viewdata);
        btLogout = (Button) findViewById(R.id.bt_logout);
        tvUser = (TextView) findViewById(R.id.tv_user);
        fAuth = FirebaseAuth.getInstance();

        fStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.v(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {

                    Toast.makeText(ChooseActivity.this, "User Logout\n",
                            Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "onAuthStateChanged:signed_out");
                    startActivity(new Intent(ChooseActivity.this,
                            LoginActivity.class));
                    finish();
                }
            }
        };
        checkLogin();
    }

    public void report(View v) {

        startActivity(CreateActivity.getActIntent(ChooseActivity.this));
    }

    public void history(View v) {

        startActivity(ReadActivity.getActIntent(ChooseActivity.this));
    }

    public void logout(View v) {
        fAuth.signOut();
        startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
    }

    private void checkLogin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            tvUser.setText("Welcome, " + email);
        }
    }
}