package com.example.b1014001.database;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class DatabaseActivity extends Activity {
    public static String name;
    public static String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText ageText = (EditText) findViewById(R.id.editAge);
        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                age = ageText.getText().toString();
                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("age", age);
                long id = db.insert("person", name, insertValues);
            }
        });
        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(DatabaseActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("age", age);
                    db.update("person", updateValues, "name=?", new String[] { name });
                }
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(DatabaseActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("person", "name=?", new String[] { name });
                }
            }
        });
        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                db.delete("person", null, null);
            }
        });
        Button detaBaseButton = (Button) findViewById(R.id.dataBase);
        detaBaseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(DatabaseActivity.this,
                        ShowDataBase.class);
                startActivity(dbIntent);
            }
        });
    }
}