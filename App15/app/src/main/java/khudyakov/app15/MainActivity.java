package khudyakov.app15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = openOrCreateDatabase("DBName", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable (Name VARCHAR, Age Int);");
        displayData();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.name)).getText().toString();
                String ageString = ((EditText) findViewById(R.id.age)).getText().toString();

                if (!name.isEmpty() && !ageString.isEmpty()) {
                    int age = Integer.parseInt(ageString);
                    String sql = String.format(Locale.getDefault(),
                            "INSERT INTO MyTable VALUES ('%s', %d);", name, age);
                    db.execSQL(sql);
                    items.add(createItem(name, ageString));
                    adapter.notifyDataSetChanged();;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    void displayData() {
        Cursor cursor = db.rawQuery("SELECT * FROM MyTable", null);
        ListView listView = findViewById(R.id.list_view);
        items = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String age = cursor.getString(cursor.getColumnIndex("Age"));

                items.add(createItem(name, age));

            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        cursor.close();
    }

    String createItem(String name, String age) {
        return name + ",    " + age;
    }
}
