package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt (View v){

        EditText keyView = findViewById(R.id.key);
        String key = keyView.getText().toString();

        EditText dataView = findViewById(R.id.data);
        String data = dataView.getText().toString();

        CipherModel myCipher = new CipherModel(key);
        String encrypt = myCipher.encrypt(data);

        ((TextView) findViewById(R.id.data)).setText(encrypt);
    }

    public void onDecrypt (View v){

        EditText keyView = findViewById(R.id.key);
        String key = keyView.getText().toString();

        EditText dataView = findViewById(R.id.data);
        String data = dataView.getText().toString();

        CipherModel myCipher = new CipherModel(key);
        String decrypt = myCipher.decrypt(data);

        ((TextView) findViewById(R.id.data)).setText(decrypt);
    }

    public void onSave(View v){

        try{

            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            String show = "";
            File dir = this.getFilesDir();
            File file = new File(dir,name);

            FileWriter fw = new FileWriter(file);

            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();

            ((EditText) findViewById(R.id.data)).setText(show);

            Toast label = Toast.makeText(this,"Note Saved", Toast.LENGTH_LONG);
            label.show();
        }

        catch(Exception e){

            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onLoad(View v){

        try{

            String name = ((EditText) findViewById(R.id.file)).getText().toString();

            File dir = this.getFilesDir();
            File file = new File(dir,name);

            FileReader fr = new FileReader(file);

            String show = "";

            for(int c = fr.read(); c != -1; c = fr.read()){

                show += (char) c;
            }

            fr.close();

            ((EditText) findViewById(R.id.data)).setText(show);
            Toast label = Toast.makeText(this,"Note Loaded", Toast.LENGTH_LONG);
            label.show();

        }

        catch(Exception e){

            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

}
