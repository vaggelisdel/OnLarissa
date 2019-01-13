package com.example.vagge.onlarissa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ThessalyCategory extends AppCompatActivity {


    private static String URL="https://www.onlarissa.gr/category/thessaly/";
    private RecyclerView recyclerview;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_thessaly_category );

        getSupportActionBar().setTitle("Thessaly News");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerview = (RecyclerView) findViewById (R.id.recyclerView);
        recyclerview.setHasFixedSize ( true );
        recyclerview.setLayoutManager ( new LinearLayoutManager ( this ) );

        listItems = new ArrayList<> ();

        new ThessalyCategory.loadRecyclerViewData ().execute();
    }


    private class loadRecyclerViewData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(ThessalyCategory.this);
            progressDialog.setTitle("Retrieving Data");
            progressDialog.setMessage("Please wait...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc= Jsoup.connect(URL).timeout(30*1000).get();
                Elements title=doc.select("li[class*=post-item]");

                for (int i=0; i<title.size(); i++){
                    ListItem item = new ListItem (
                            doc.select("h3[class=post-title]").eq (i).text (),
                            doc.select("p[class=post-excerpt]").eq (i).text (),
                            doc.select("img[class*=wp-post-image]").eq (i).first().attr ( "src" ),
                            doc.select("a[class*=post-thumb]").eq (i).first().attr ( "href" ),
                            doc.select("span[class*=date] > span:not([class*=fa])").eq (i).text ()

                    );
                    listItems.add ( item );
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter = new MyAdapter (listItems, getApplicationContext ());
            recyclerview.setAdapter ( adapter );
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish ();
        }

        return super.onOptionsItemSelected ( item );
    }
}
