package com.phong.hoccontentprovider_mediastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lvMedia;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readMediaStore();
    }

    private void readMediaStore() {
        String projection[] = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE};
        CursorLoader cursorLoader = new CursorLoader(
                MainActivity.this,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,null,null,null);
        Cursor cursor = cursorLoader.loadInBackground();
        while (cursor.moveToNext()){
            String ten = cursor.getString(0);
            adapter.add(ten);
        }
        cursor.close();
    }

    private void addControls() {
        lvMedia = findViewById(R.id.lvMedia);
        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvMedia.setAdapter(adapter);
    }
}
