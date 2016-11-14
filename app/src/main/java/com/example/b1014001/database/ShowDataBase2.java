package com.example.b1014001.database;

/**
 * Created by b1014001 on 2016/11/10.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDataBase2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();
        // queryメソッドの実行例
        Cursor c = db.query("person", new String[]{"name", "age", "food"}, null,
                null, null, null, null);
        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d歳 :%s", c.getString(0),
                    c.getInt(1), c.getString(2)));
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        // ボタンの設定
        Button button = new Button(this);
        button.setText("Button");

        // マージン 30dp に設定
        float scale = getResources().getDisplayMetrics().density;
        int margins = (int) (30 * scale);
        int buttonWidth = (int) (150 * scale);
        // 横幅 150dp, 縦 100dp に設定
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                buttonWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        buttonLayoutParams.setMargins(margins, margins, margins, margins);
        button.setLayoutParams(buttonLayoutParams);
        layout.addView(button);
        // リスナーをボタンに登録
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.close();
                Intent dbIntent = new Intent(ShowDataBase2.this,DatabaseActivity.class);
                startActivity(dbIntent);
            }
        });
    }
}