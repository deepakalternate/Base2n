package com.deeroid.base2n;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.deeroid.wrapper.CommonCode;

public class Home extends ActionBarActivity {

    private Button copy, paste, share;
    private EditText inp;
    private TextView opt;
    private Spinner inp_lan, op_lan;
    private KeyListener originalKL;
    private int locInput, locOutput;
    private CommonCode caller;

    //OnCreate Start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        copy = (Button) findViewById(R.id.copy_button);
        paste = (Button) findViewById(R.id.paste_button);
        share = (Button) findViewById(R.id.share_button);

        inp_lan = (Spinner) findViewById(R.id.input_lang);
        op_lan = (Spinner) findViewById(R.id.output_lang);

        inp = (EditText) findViewById(R.id.input_et);
        opt = (TextView) findViewById(R.id.output_tv);

        originalKL = inp.getKeyListener();

        //Copy Button Start
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cmc = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String toCopy = opt.getText().toString();
                if (toCopy.length() == 0){
                    Toast.makeText(getApplicationContext(), "Nothing to copy.", Toast.LENGTH_SHORT).show();
                }
                else {
                    ClipData copiedText = ClipData.newPlainText("Base 2n copy", toCopy);
                    cmc.setPrimaryClip(copiedText);
                    Toast.makeText(getApplicationContext(), "Text Copied.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Copy Button End

        //Paste Button Start
        paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cmp = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String toPaste = "";

                if (!(cmp.hasPrimaryClip())) {

                } else if (!(cmp.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {

                } else {
                    ClipData.Item item = cmp.getPrimaryClip().getItemAt(0);
                    toPaste = item.getText().toString();
                    inp.getText().insert(inp.getSelectionStart(), toPaste);
                    //yourEditText.getText().insert(yourEditText.getSelectionStart(), "string goes here");
                }

            }
        });
        //Paste Button End

        //Share Button Start
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, opt.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share to:"));
            }
        });
        //Share Button End

        final String[] languages = {CommonCode.TEX, CommonCode.BIN, CommonCode.OCT, CommonCode.HEX};
        final String[] lan_text = {CommonCode.BIN, CommonCode.OCT, CommonCode.HEX};
        final String[] lan_binary = {CommonCode.TEX, CommonCode.OCT, CommonCode.HEX};
        final String[] lan_octal = {CommonCode.TEX, CommonCode.BIN, CommonCode.HEX};
        final String[] lan_hex = {CommonCode.TEX, CommonCode.BIN, CommonCode.OCT};

        //Input Spinner Start
        ArrayAdapter<String> inpAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, languages);
        inp_lan.setAdapter(inpAdapter);

        inp_lan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
                //Output Spinner Start
                ArrayAdapter<String> opAdapter;
                locInput = position;
                switch (position){
                    case 0:
                        opAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, lan_text);
                        inp.setText("");
                        inp.setKeyListener(originalKL);
                        break;
                    case 1:
                        opAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, lan_binary);
                        inp.setText("");
                        inp.setKeyListener(DigitsKeyListener.getInstance("01 "));
                        break;
                    case 2:
                        opAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, lan_octal);
                        inp.setText("");
                        inp.setKeyListener(DigitsKeyListener.getInstance("01234567 "));
                        break;
                    case 3:
                        opAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, lan_hex);
                        inp.setText("");
                        inp.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEFabcdef "));
                        break;
                    default:
                        opAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_base, languages);
                }
                op_lan.setAdapter(opAdapter);
            }
            //Output Spinner End

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Input Spinner End

        //Output Spinner Location Start
        op_lan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locOutput = position;
                String curr = inp.getText().toString();
                String op = caller.callingFunction(locInput, locOutput, curr);
                opt.setText(op);
                opt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            });

        //Output Spinner Location End

    }
    //OnCreate End

    //OnStart Start
    @Override
    protected void onStart() {
        super.onStart();

        caller = new CommonCode();

        //EditText Listener Start
        inp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String curr = inp.getText().toString();
                String op = caller.callingFunction(locInput, locOutput, curr);
                opt.setText(op);
                opt.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //EditText Listener End

    }
    //OnStart End

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
