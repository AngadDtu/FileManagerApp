package com.codingblocks.filemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;

/**
 * Created by nagarro on 04/10/15.
 */
public class FileArrayAdapter extends ArrayAdapter<File> {

    Context context;

    public FileArrayAdapter(Context context, File[] objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.file_item_layout, null);
            ViewHolder holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.fileDirIcon);
            holder.name = (TextView) convertView.findViewById(R.id.filename);
            holder.b = (Button) convertView.findViewById(R.id.favButton);
            convertView.setTag(holder);
        }



        ViewHolder holder = (ViewHolder)convertView.getTag();

        //holder.b.setTag(getItem(position));

        File currentFile = getItem(position);
        if (currentFile.isDirectory()) {
            holder.iv.setImageResource(android.R.drawable.ic_media_play);
        } else {
            holder.iv.setImageResource(android.R.drawable.ic_media_pause);
        }
        holder.name.setText(currentFile.getName());

        final File f = getItem(position);

        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManagerOpenHelper helper = new FileManagerOpenHelper(context, null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(FileManagerOpenHelper.FAV_TABLE_FILE_PATH, f.getAbsolutePath());
                Date d = new Date();

                cv.put(FileManagerOpenHelper.FAV_TABLE_CREATION_TIME, d.toString());
                db.insert(FileManagerOpenHelper.FAV_TABLE, null, cv);
                Log.i("clicked", f.getAbsolutePath());
            }
        });
        return convertView;
    }

    public class ViewHolder {
        ImageView iv;
        TextView name;
        Button b;
    }
}
