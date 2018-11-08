package estacio.com.estacio_nov8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private Button cbutton;
    EditText eStudent, eSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eStudent = findViewById(R.id.etStudent);
        eSection = findViewById(R.id.etSection);

        cbutton = (Button) findViewById(R.id.button);
        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void saveSP(View v) {
        SharedPreferences sp = getSharedPreferences("data1", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        String name = eStudent.getText().toString();
        String section = eSection.getText().toString();
        writer.putString("name", name);
        writer.putString("sec", section);
        writer.commit();
        Toast.makeText(this, "data saved...", Toast.LENGTH_LONG).show();

    }

    public void saveInternal(View v) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data2.txt", Context.MODE_PRIVATE);
            String name2 = eStudent.getText().toString();
            fos.write(name2.getBytes());
            Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
