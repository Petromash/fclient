package com.example.maria_fclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.maria_fclient.databinding.ActivityMainBinding;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'maria_fclient' library on application startup.
    static {
        System.loadLibrary("maria_fclient");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ----------Random generator----------
//        int res = initRng();
//        byte[] v = randomBytes(10);
//
//        TextView tv = binding.sampleText;
//        tv.setText(Arrays.toString(v));

//        ----------Test encrypt/decrypt----------
        TextView tv = binding.sampleText;

        byte[] key = "1234567890123456".getBytes(StandardCharsets.UTF_8);
        byte[] data = "Hello Alena!!! :)) (pssssssssss)".getBytes(StandardCharsets.UTF_8);

        byte[] encData = encrypt(key, data);
        byte[] decData = decrypt(key, encData);

        tv.setText(new String(data, StandardCharsets.UTF_8));
        tv.append("\n");
        tv.append(new String(decData, StandardCharsets.UTF_8));
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