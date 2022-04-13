package com.example.runconnect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = MainActivity2.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    int lastState = TelephonyManager.CALL_STATE_IDLE;
    private TelephonyManager mTelephonyManager;
    private MainActivity2.MyPhoneCallListener mListener;
    boolean isIncoming;
    Context context = null;
    private TextView textView;
    List<String> listOfStrings
            = new ArrayList<String>();
    String s = new String();
    char ch;
    MediaPlayer mediaPlayer;
    String listOfNumber[] = {"002217788121171", "002217788121171"};
    MediaPlayer mp;
    String stringOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        int PERMISSION_ALL = 1;
        Button button1 = (Button) findViewById(R.id.buttonCopy);
        Button buttonLog = (Button) findViewById(R.id.button);
        mp=MediaPlayer.create(this,R.raw.speech1);

        String[] PERMISSIONS = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.READ_CALL_LOG,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.ANSWER_PHONE_CALLS,
                android.Manifest.permission.WRITE_CONTACTS,
                android.Manifest.permission.WRITE_CALL_LOG,
                android.Manifest.permission.READ_CONTACTS

        };
        textView = findViewById(R.id.textView);
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (isTelephonyEnabled()) {
            Log.d(TAG, getString(R.string.telephony_enabled));
            //checkForPhonePermission();

            if (!hasPermissions(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            }
            mListener = new MainActivity2.MyPhoneCallListener();
            mTelephonyManager.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
        } else {
            Toast.makeText(this,
                    R.string.telephony_not_enabled, Toast.LENGTH_LONG).show();
            Log.d(TAG, getString(R.string.telephony_not_enabled));
            // Disable the call button.
            disableCallButton();
        }
        buttonLog.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

               /* stringOutput = "";
                Uri uriCallLogs = Uri.parse("content://call_log/calls");
                Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
                cursorCallLogs.moveToFirst();
                do {
                    @SuppressLint("Range") String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
                    @SuppressLint("Range") String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    @SuppressLint("Range") int stringDuration = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
                    @SuppressLint("Range") int stringType = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
                    @SuppressLint("Range") String stringDate = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));
                    if(stringType==2 && stringDuration>0){
                        stringOutput=stringOutput+" Number: " + stringNumber
                                + "\n Name: " + stringName
                                + "\n"+ "active"+"\n"+"_____________________________________"+"\n\n";
                    }
                    else{
                        stringOutput=stringOutput+"Number: " + stringNumber
                                + "\n Name: " + stringName
                                + "\n"+ " non active xx"+"\n"+"_____________________________________"+"\n\n";
                    }
                }while (cursorCallLogs.moveToNext());
                System.out.println(stringOutput);*/
                Intent intent =new Intent(getApplicationContext(),choise.class);

                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
               /* ClipboardManager clipboard = (ClipboardManager)   getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied", stringOutput);
                clipboard.setPrimaryClip(clip);*/
                stringOutput = "";
                Uri uriCallLogs = Uri.parse("content://call_log/calls");
                Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
                cursorCallLogs.moveToFirst();
                if(cursorCallLogs.getCount()>0){
                do {
                    @SuppressLint("Range") String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
                    @SuppressLint("Range") String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    @SuppressLint("Range") int stringDuration = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
                    @SuppressLint("Range") int stringType = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
                    @SuppressLint("Range") String stringDate = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));
                    if(stringType==2 && stringDuration>0){
                        stringOutput=stringOutput+" Number: " + stringNumber
                                + "\n Name: " + stringName
                                + "\n"+ "active"+"\n"+"_____________________________________"+"\n\n";
                    }
                    else{
                        stringOutput=stringOutput+"Number: " + stringNumber
                                + "\n Name: " + stringName
                                + "\n"+ " non active xx"+"\n"+"_____________________________________"+"\n\n";
                    }
                }while (cursorCallLogs.moveToNext());
                    System.out.println(" delete");

                    getContentResolver().delete(CallLog.Calls.CONTENT_URI, null, null);
                    Intent intent =new Intent(getApplicationContext(),MainActivity2.class);

                    startActivity(intent);
                }
                else {
                    System.out.println("not delet");
                    Intent intent =new Intent(getApplicationContext(),MainActivity2.class);

                    startActivity(intent);
                }


            }
        });

    }
    private void checkForPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted));

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

        } else {
            enableCallButton();
        }
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buttonCallLogs(){
        stringOutput = "";

        Uri uriCallLogs = Uri.parse("content://call_log/calls");
        Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
        cursorCallLogs.moveToFirst();
        do {
            @SuppressLint("Range") String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
            @SuppressLint("Range") String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
            @SuppressLint("Range") int stringDuration = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
            @SuppressLint("Range") int stringType = cursorCallLogs.getInt(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
            @SuppressLint("Range") String stringDate = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));
            if(stringType==2 && stringDuration>0){
                stringOutput=stringOutput+"Number: " + stringNumber
                        + "\nName: " + stringName
                        + "\n\n";
            }
        }while (cursorCallLogs.moveToNext());
        System.out.println(stringOutput);
    }
    private boolean isTelephonyEnabled() {
        System.out.println("*******************isTelephonyEnabled()*******************");
        if (mTelephonyManager != null) {
            if (mTelephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // For each permission, check if it is granted or not.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.CALL_PHONE)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable call button.
                    //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, PackageManager.PERMISSION_GRANTED);

                    enableCallButton();
                } else {
                    // Permission denied.
                    // Disable the functionality that depends on this permission.
                    Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this, getString(R.string.failure_permission),
                            Toast.LENGTH_SHORT).show();
                    // Disable the call button.
                    disableCallButton();
                }
            }
        }
    }

    public static ArrayList<String> mySplit(String toSplit, char del) {
        ArrayList<String> result = new ArrayList<String>(); // create an empty array list to store/hold the result

        String currentWord = ""; // create a string which will hold the current word we are looking to add to the array
        for(int i = 0; i < toSplit.length(); i++) { // loop through every letter in the toSplit word
            char currentChar = toSplit.charAt(i); // get the current character

            if(currentChar != del) { // if the current character doesn't equal the "splitting character" then add it to our currentWord variable
                currentWord += currentChar; // add the current character to the end of our currentWord string
            } else { // the current char is the "splitting character"
                result.add(currentWord); // add the current word to the results array
                currentWord = ""; // set the current word back to empty (so the next word can use it)
            }
        }
        result.add(currentWord);
        return result;
    }
    public void callNumber(View view) {

        ArrayList<String> replace ;
        //String text="002217788121171-002217788121171-002217788121171-002217788121171";
        //String[] lines = text.split("-");
        System.out.println("*******************callNumber()*******************");
        String normalizedPhoneNumber;
        // Find the editText_main view and assign it to editText.
        EditText editText = (EditText) findViewById(R.id.editText_main);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "Running version earlier than Lollipop. Can't normalize number.");
            normalizedPhoneNumber = editText.getText().toString();
            System.out.println("------------------1----------------");
            System.out.println(normalizedPhoneNumber);
            replace=mySplit(normalizedPhoneNumber,'\n');
            System.out.println(replace);
            System.out.println("----------------------------------");
        } else {
            normalizedPhoneNumber =editText.getText().toString();
            System.out.println("---------------2-------------------");
            System.out.println(normalizedPhoneNumber);
            replace=mySplit(normalizedPhoneNumber,'\n');
            System.out.println(replace);
            System.out.println("----------------------------------");
        }
        MainActivity2.MyPhoneCallListener callListener = new MainActivity2.MyPhoneCallListener();
        TelephonyManager mTM = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTM != null) mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
        else Log.d(TAG, "TelephonyManager null");
        ///////////////////////////////////////////////////

        // Set the data for the intent as the phone number.
        Thread thread = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                int x = 0;
                while (x < replace.size())
                {
                    //call( x);
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    /*if(x==replace.size()){
                        System.out.println("all the calls is finish..........");
                        System.out.println("informations calls :");
                        buttonCallLogs();
                    }*/
                    //else{

                    System.out.println(x);
                    String phoneNumber = String.format("tel: %s", replace.get(x));
                    // Log the concatenated phone number for dialing.
                    Log.d(TAG, getString(R.string.dial_number) + phoneNumber);
                    ////////////////////////////////////////////////
                    callIntent.setData(Uri.parse(phoneNumber));
                    if (callIntent.resolveActivity(getPackageManager()) != null) {
                        checkForPhonePermission();
                        // start call
                        startActivity(callIntent);
                        try {
                            Thread.sleep(60000);
                            System.out.println("here finish***********");
                            //endCall();
                            mp.start();
                            // Thread.sleep(8000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_CALL Intent.");
                    }
                    // }
                    try {
                        //Thread.sleep(10000);
                        // System.out.println("here finish***********");
                        //endCall();
                        // mp.start();
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x++;
                }
            }
        };

        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void  call(int x){

        if(x==listOfNumber.length){
            System.out.println("all the calls is finish..........");
            System.out.println("informations calls :");
            buttonCallLogs();
        }
        else{

            System.out.println(x);
            String phoneNumber = String.format("tel: %s", listOfNumber[x]);
            // Log the concatenated phone number for dialing.
            Log.d(TAG, getString(R.string.dial_number) + phoneNumber);
            ////////////////////////////////////////////////
            Intent intent =new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
            ///////////////////////////////////////////////
           /* Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phoneNumber));

            if (callIntent.resolveActivity(getPackageManager()) != null) {
                //checkForPhonePermission();
                startActivity(callIntent);
            } else {
                Log.e(TAG, "Can't resolve app for ACTION_CALL Intent.");
            }*/
        }
        try {
            Thread.sleep(10000);
            //System.out.println("here finish***********");

            Thread.sleep(8000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private class MyPhoneCallListener extends PhoneStateListener {
        private boolean returningFromOffHook = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            // Define a string for the message to use in a toast.

            super.onCallStateChanged(state, incomingNumber);
            if (lastState == state) {
                //No change
                return;
            }
            String message = getString(R.string.phone_status);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // Incoming call is ringing (not used for outgoing call).

                    TextView incomingView = (TextView) findViewById(R.id.incoming);
                    incomingView.setText(incomingNumber);
                    incomingView.setVisibility(View.VISIBLE);
                    System.out.println("Raining..........................");
                    Log.i(TAG, message);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // Phone call is active -- off the hook.
                    message = message + getString(R.string.offhook);
                    Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, message);
                    returningFromOffHook = true;
                    System.out.println("OFFHOOK..........................");
                    if (lastState != TelephonyManager.CALL_STATE_RINGING) {

                        System.out.println("****outgoing call started ringing****");
                        //outgoing call started



                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    // Phone is idle before and after phone call.
                    // If running on version older than 19 (KitKat),
                    // restart activity when phone call ends.
                    message = message + getString(R.string.idle);
                    Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                    System.out.println("IDLE..........................");
                    Log.i(TAG, message);
                    if (returningFromOffHook) {
                        // No need to do anything if >= version KitKat.
                        System.out.println("miss call..........................");
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                            Log.i(TAG, getString(R.string.restarting_app));
                            // Restart the app.
                            Intent i = getPackageManager()
                                    .getLaunchIntentForPackage(getPackageName());
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }
                    break;
                default:
                    message = message + "Phone off";
                    Toast.makeText(MainActivity2.this, message, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, message);
                    break;
            }
        }
    }



    /**
     * Makes the call button (phone icon) invisible so that it can't be used,
     * and makes the Retry button visible.
     */
    private void disableCallButton() {
        System.out.println("*******************disableCallButton()*******************");
        Toast.makeText(this, R.string.phone_disabled, Toast.LENGTH_LONG).show();
        ImageButton callButton = (ImageButton) findViewById(R.id.phone_icon);
        callButton.setVisibility(View.INVISIBLE);
        if (isTelephonyEnabled()) {
            Button retryButton = (Button) findViewById(R.id.button_retry);
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Makes the call button (phone icon) visible so that it can be used.
     */
    private void enableCallButton() {
        System.out.println("*******************enableCallButton()*******************");
        ImageButton callButton = (ImageButton) findViewById(R.id.phone_icon);
        callButton.setVisibility(View.VISIBLE);
    }

    /**
     * Enables the call button, and sends an intent to start the activity.
     */
    public void retryApp(View view) {
        System.out.println("*******************retryApp()*******************");
        enableCallButton();
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isTelephonyEnabled()) {
            System.out.println("*******************onDestroy()*******************");
            mTelephonyManager.listen(mListener, PhoneStateListener.LISTEN_NONE);
        }
    }

}