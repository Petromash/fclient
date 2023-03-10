package com.example.maria_fclient;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maria_fclient.databinding.ActivityMainBinding;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'maria_fclient' library on application startup.
    static {
        System.loadLibrary("maria_fclient");
        System.loadLibrary("mbedcrypto");
    }

    ActivityResultLauncher activityResultLauncher;



    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ----------Random generator----------
        int res = initRng();
//        byte[] v = randomBytes(10);
//
//        TextView tv = binding.sampleText;
//        tv.setText(Arrays.toString(v));

//        ----------Test encrypt/decrypt----------
        /*TextView tv = binding.sampleText;

        byte[] key = "1234567890123456".getBytes(StandardCharsets.UTF_8);
        byte[] data = "Hello Alena!!! :)) (pssssssssss)".getBytes(StandardCharsets.UTF_8);

        byte[] encData = encrypt(key, data);
        byte[] decData = decrypt(key, encData);

        tv.setText(new String(data, StandardCharsets.UTF_8));
        tv.append("\n");
        tv.append(new String(decData, StandardCharsets.UTF_8));*/
        activityResultLauncher  = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback() {
                    @Override
                    public void onActivityResult(Object resultObject) {
                        ActivityResult result = (ActivityResult) resultObject;
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            // обработка результата
                            String pin = data.getStringExtra("pin");
                            Toast.makeText(MainActivity.this, pin, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onButtonClick(View v)
    {
       /* byte[] key = stringToHex("0123456789ABCDEF0123456789ABCDE0");
        byte[] enc = encrypt(key, stringToHex("000000000000000102"));
        byte[] dec = decrypt(key, enc);
        String s = new String(Hex.encodeHex(dec)).toUpperCase();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();*/
        Intent it = new Intent(this, PinpadActivity.class);
        //startActivity(it);
        activityResultLauncher.launch(it);
    }





    public static byte[] stringToHex(String s)
    {
        byte[] hex;
        try
        {
            hex = Hex.decodeHex(s.toCharArray());
        }
        catch (DecoderException ex)
        {
            hex = null;
        }
        return hex;
    }






    /**
     * A native method that is implemented by the 'maria_fclient' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static native byte[] encrypt(byte[] key, byte[] data);
    public static native byte[] decrypt(byte[] key, byte[] data);
}