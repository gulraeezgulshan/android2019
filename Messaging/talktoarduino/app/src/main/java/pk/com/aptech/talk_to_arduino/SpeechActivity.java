package pk.com.aptech.talk_to_arduino;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

import pk.com.aptech.talk_to_arduino.bluetooth.BluetoothUtils;

public class SpeechActivity extends AppCompatActivity {

    private static final int VOICE_RECOGNITION_REQUEST = 1;

    //Getting the name for Log Tags
    private final String LOG_TAG = SpeechActivity.class.getSimpleName();

    //Declare U.I Elements
    private Button startTalk;
    private Button refresh;
    private EditText speechInput;
    private TextView btv;

    private boolean areServicesAccessible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        startTalk = (Button) findViewById(R.id.talktoArduino);
        refresh = (Button) findViewById(R.id.refreshBtn);
        speechInput = (EditText) findViewById(R.id.recordedTalk);
        btv = (TextView) findViewById(R.id.btView);

        startTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordSpeech();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void recordSpeech() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "You can now send a command to the Arduino");

        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST);
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) {

            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String userInput = matches.get(0);

            TextView textSaid = (TextView) findViewById(R.id.recordedTalk);

            textSaid.setText(matches.get(0));

            //add an if else loop or case statement

        }
        super.onActivityResult(requestCode, resultCode, data);
    }






}
