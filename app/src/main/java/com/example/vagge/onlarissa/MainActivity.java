package com.example.vagge.onlarissa;

import android.content.Context;
import android.content.Intent;
import android.media.MicrophoneInfo;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        getSupportActionBar ( ).setTitle ( "OnLarissa News" );
        getSupportActionBar().hide();

        if (!amIConnected ()){
            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Δεν υπάρχει σύνδεση στο Internet", Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Δοκιμή ξανά", new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (!amIConnected()){
                        Snackbar.make(findViewById(android.R.id.content), "Δεν έχετε συνδεθεί στο Internet",Snackbar.LENGTH_INDEFINITE).show();
                    }
                }
            }).show();
        }



    }

    private boolean amIConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService ( Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo ();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected ();
    }

    public void goToLarissaCategory(View view) {
        Intent larissa = new Intent ( MainActivity.this, LarissaCategory.class );
        startActivity ( larissa );
    }
    public void goToThessalyCategory(View view) {
        Intent thessaly = new Intent ( MainActivity.this, ThessalyCategory.class );
        startActivity ( thessaly );
    }

    public void goToGreeceCategory(View view) {
        Intent greece = new Intent ( MainActivity.this, GreeceCategory.class );
        startActivity ( greece );
    }
}
