package com.example.b1014001.database;

/**
 * Created by b1014001 on 2016/11/10.
 */
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
public class ShowDataBase extends Activity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        MyOpenHelper helper = new MyOpenHelper(this);
        final  SQLiteDatabase db = helper.getReadableDatabase();
        // queryメソッドの実行例
        Cursor c = db.query("person", new String[] { "name", "age","food" }, null,
        null, null, null, null);
        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d歳 :%d", c.getString(0),
                    c.getInt(1),c.getInt(2)));
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        // レイアウトの縦横幅を最大
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        // レイアウト横方向中央寄せ
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(layout);

        // editTextの設定
        editText = new EditText(this);
        // 必要に応じて
//        editText.setText("sample");
        editText.setHint("入力フォーム");
        // マージン 30dp に設定
        float scale = getResources().getDisplayMetrics().density;
        int margins = (int)(30 * scale);
        int editTextWidth = (int)(150 * scale);
        // 横幅 150dp, 縦 100dp に設定
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                editTextWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        // マージン 30dp に設定
        margins = (int)(30 * scale);
        editTextParams.setMargins(margins, margins, margins, margins);
        editText.setLayoutParams(editTextParams);
        layout.addView(editText);
        // ボタンの設定
        Button button = new Button(this);
        button.setText("Button");


        int buttonWidth = (int)(150 * scale);
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
                // エディットテキストのテキストを取得
                String food = editText.getText().toString();
                ContentValues updateValues = new ContentValues();
                updateValues.put("food", food);
                db.update("person", updateValues, "name=?", new String[] { DatabaseActivity.name });
                db.close();
                Intent dbIntent = new Intent(ShowDataBase.this,ShowDataBase2.class);
                startActivity(dbIntent);
            }
        });
    }
}