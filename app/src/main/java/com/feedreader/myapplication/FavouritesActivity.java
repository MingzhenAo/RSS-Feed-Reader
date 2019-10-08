package com.feedreader.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;

public class FavouritesActivity extends AppCompatActivity {
    RSSFeedparser parser = new RSSFeedparser();
    private FavouritesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.favourites_layout);
        Intent intent = getIntent();
        String url = intent.getStringExtra("webLink");
        MyApplication app = (MyApplication) getApplication();


        if (url != null) {
            app.setContent(new MyContent(url));
            app.getmDatas().add(app.getContent());
        }


        /*if (url != null) {

            boolean flag = true;

            if (app.getCollectionList() != null) {
                for (int i = 0; i < app.getCollectionList().size(); i++) {
                    if (url.equals(app.getCollectionList().get(i))) {
                        flag = false;
                    }
                }


                if (flag) {
                    app.getCollectionList().add(url);
                }

                for (int i = 0; i < app.getCollectionList().size(); i++) {

                    for (MyContent content : app.getmDatas()) {
                        content.setContent(app.getCollectionList().get(i));
                    }
                }


            }
        }*/




      /*  FavouritesAdapter adapter = new FavouritesAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);*/
        //ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, app.getCollectionList());
        adapter = new FavouritesAdapter(this, app.getmDatas());


        ListView listView = (ListView) findViewById(R.id.list);
        //listView.setAdapter(itemsAdapter);

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/delete_menu.xmlfile.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        deleteAll();
        return super.onOptionsItemSelected(item);
    }

    private void deleteAll() {

    }


}

