package com.feedreader.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class RSSfeedshow extends AppCompatActivity {
    ArrayList<RSSElement> a = new ArrayList<>();
    RSSFeedparser parser = new RSSFeedparser();
    Button buttonHome;
    String url;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, addSitesShow.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_layout);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        putLayout putlayout = new putLayout();
        putlayout.execute(url);

        String tag = "imageButtonRefreshInFeedLayout";
        LinearLayout layout = findViewById(R.id.linearLayout2);
        ImageButton refreshBtn = layout.findViewWithTag(tag);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = findViewById(R.id.linearLayout);
                layout.removeAllViews();
                putLayout putlayout = new putLayout();
                putlayout.execute(url);
            }
        });
    }




    public class putLayout extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... args) {
            String url = args[0];
            a = parser.getRSSfeedFromUrl(url);

            runOnUiThread(new Runnable() {
                public void run() {
                    for (int i = 0; i < a.size(); i++) {
                        LinearLayout layout = findViewById(R.id.linearLayout);
                        Button new_button = new Button(getApplicationContext());
                        int number = i + 1;
                        final String newsTitle = a.get(i).title;
                        new_button.setText(number + ". " + newsTitle + "\r\n" + a.get(i).pubdate+"\r\n"+a.get(i).category);
                        new_button.setLayoutParams(new ViewGroup.LayoutParams(1450, 300));
                        new_button.setX(0);
                        new_button.setY(0);
                        new_button.setAllCaps(false);
                        new_button.setTag(a.get(i).link);
                        new_button.setBackgroundColor(Color.WHITE);
                        new_button.setFadingEdgeLength(10);
                        new_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), webView.class);
                                intent.putExtra("url", v.getTag().toString());
                                intent.putExtra("title", newsTitle);
                                startActivity(intent);
                            }
                        });
                        new_button.setGravity(0);//Text to the left
                        layout.addView(new_button);
                    }
                }
            });
            return null;
        }


    }

    public void openFavourites(View v) {
        Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
        startActivity(intent);
    }

    public void addSitePage(View v) {
        Intent intent = new Intent(getApplicationContext(), addSitesShow.class);
        startActivity(intent);
    }
}
