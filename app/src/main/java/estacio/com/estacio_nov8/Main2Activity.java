package estacio.com.estacio_nov8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main2Activity extends AppCompatActivity {
    private Button cbutton;
    TextView tMsg;
    EditText eStudent, eSection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tMsg = findViewById(R.id.tvMsg);
        cbutton = (Button) findViewById(R.id.button);
        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
    }
    private void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void displayMsg(View v){
        SharedPreferences sp = getSharedPreferences("data1",Context.MODE_PRIVATE);
        String name = sp.getString("name", String.valueOf(eStudent));
        String section = sp.getString("sec", String.valueOf(eSection));
        String message = "Good afternoon " + name + ". Your section is " + section;
        tMsg.setText(message);
    }
    public void displayInternal(View v){
        try {
            FileInputStream fin = openFileInput("data2.txt");
            int c;
            StringBuffer buffer = new StringBuffer();
            while((c=fin.read()) != -1){
                buffer.append((char)c);
            }
            String message = "Good afternoon" + buffer;
            tMsg.setText(message);
        } catch (Exception e){
            Toast.makeText(this, "Error reading...", Toast.LENGTH_LONG).show();
        }
    }

}
