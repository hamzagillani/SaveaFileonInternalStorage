package com.digicon_valley.saveafileoninternalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText messageBox;
    TextView messageSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageBox = findViewById(R.id.edit_text);
        messageSave = findViewById(R.id.text_view);
        messageSave.setVisibility(View.VISIBLE);

    }

    public void SaveMessage(View view) {

        String Message = messageBox.getText().toString();
        String file_name = "hello_file";
        try {

            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Message Saved", Toast.LENGTH_SHORT).show();
            messageBox.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadMessage(View view) {

        try {

            String Message;
            FileInputStream fileInputStream=openFileInput("hello_file");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((Message = bufferedReader.readLine()) != null) {

                stringBuffer.append(Message + "\n");

            }
            messageSave.setText(stringBuffer.toString());
            messageSave.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}