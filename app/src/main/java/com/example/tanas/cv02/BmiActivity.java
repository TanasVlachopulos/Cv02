package com.example.tanas.cv02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BmiActivity extends Activity implements View.OnClickListener {

    private Button btnBack;
    private Button btnInfo;
    private Button btnCamera;
    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        btnBack = (Button) findViewById(R.id.button2);
        btnBack.setOnClickListener(this);
        btnInfo = (Button) findViewById(R.id.button3);
        btnInfo.setOnClickListener(this);
        btnCamera = (Button) findViewById(R.id.button4);
        btnCamera.setOnClickListener(this);

        float weight = getIntent().getFloatExtra("weight", 0);
        float height = getIntent().getFloatExtra("height", 0);

        TextView bmiTitle = (TextView) findViewById(R.id.textView4);
        bmi = this.countBmi(weight, height);
        bmiTitle.setText("BMI = " + bmi);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnBack.getId()) {
            Intent i = new Intent();
            i.putExtra("bmi", bmi);
            setResult(RESULT_OK, i);
            super.finish();
        }
        else if (v.getId() == btnInfo.getId()){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cs.wikipedia.org/wiki/Index_t%C4%9Blesn%C3%A9_hmotnosti"));
            startActivity(i);
        }
        else if (v.getId() == btnCamera.getId()) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(i, 2);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
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

    private float countBmi(float weight, float height) {
        float bmi = 0;
        if (height != 0 && weight != 0) {
            bmi = weight / (height * height);
        }
        return bmi;
    }



}
