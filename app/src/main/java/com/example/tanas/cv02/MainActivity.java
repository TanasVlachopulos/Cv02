package com.example.tanas.cv02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView myView1;
    TextView myView2;
    Button myButton1;
    ImageView myImage1;
    final int returnConst =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myView1 = (TextView) findViewById(R.id.textView);
        myView1.setText("Vyska");
        myView2 = (TextView) findViewById(R.id.textView2);
        myView2.setText("Vaha");
        myButton1 = (Button) findViewById(R.id.button);
        myButton1.setText("Vypocti");
        myImage1 = (ImageView) findViewById(R.id.imageView);

        myButton1.setOnClickListener(this);
    }

    // posluchac tlacitka
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            Intent i = new Intent(this, BmiActivity.class);

            EditText myEdit1 = (EditText) findViewById(R.id.editText);
            float height = 0;
            if (!myEdit1.getText().toString().equals("")) {
                height = Float.parseFloat(myEdit1.getText().toString());
            }
            height = height / 100;
            EditText myEdit2 = (EditText) findViewById(R.id.editText2);
            float weight = 0;
            if (!myEdit2.getText().toString().equals("")) {
                weight = Float.parseFloat(myEdit2.getText().toString());
            }

            i.putExtra("weight", (float) weight);
            i.putExtra("height", (float) height);

            startActivityForResult(i, returnConst);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == returnConst){
            float bmi = data.getFloatExtra("bmi", 0);
            TextView myView3 = (TextView) findViewById(R.id.textView3);

            myView3.setText("BMI = " + bmi);


            if (bmi < 18.5 || bmi > 30){
                myImage1.setImageResource(R.drawable.sad);
            } else {
                myImage1.setImageResource(R.drawable.happy);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
