package com.codingblocks.filemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;


public class FavActivity extends ActionBarActivity {
    FileArrayAdapter adapter;

    public String[] getFavFiles() {
        FileManagerOpenHelper helper = new FileManagerOpenHelper(this, null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {FileManagerOpenHelper.FAV_TABLE_FILE_PATH};


        String order = FileManagerOpenHelper.FAV_TABLE_CREATION_TIME + " DESC ";
        Cursor c = db.query(FileManagerOpenHelper.FAV_TABLE, columns, null, null, null, null, order);

        String[] output = new String[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            output[i++] = c.getString(c.getColumnIndex(FileManagerOpenHelper.FAV_TABLE_FILE_PATH));
        }
        return output;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] fileData = getFavFiles();
        File[] files = new File[fileData.length];
        for (int i = 0; i < fileData.length; i++) {
            files[i] = new File(fileData[i]);
        }
        adapter = new FileArrayAdapter(this, files);
        ListView lv = (ListView) findViewById(R.id.fileListView);
        lv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
