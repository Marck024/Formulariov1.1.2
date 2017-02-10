package com.example.administrador.formulariov112;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    //variables objetos
    private Spinner sp1;
    private ImageButton btnImg;
    private Button btnColor;


    //Variables locales
        //variable para comprobar que se cargo una imagen
    private static int RESULT_LOAD_IMAGE = 1;
        //variables para color picker
    private int mpickerColor = Color.RED;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner
        sp1=(Spinner)findViewById(R.id.spinner);
        sp1.setPrompt("Género");

        //Button Imagen
        btnImg=(ImageButton)findViewById(R.id.imageButton);
        btnImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hola",Toast.LENGTH_SHORT).show();
                Intent galeria2=new Intent(Intent.ACTION_GET_CONTENT);
                galeria2.setType("image/*");
                startActivityForResult(galeria2,RESULT_LOAD_IMAGE);
            }
        });

        //buton color
        mContext = getApplicationContext();
        final TextView cuadrito=(TextView) findViewById(R.id.txtColor);
        btnColor=(Button) findViewById(R.id.button2);
        btnColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"button de color",Toast.LENGTH_SHORT).show();
                GridView gv = (GridView) ColorPicker.getColorPicker(MainActivity.this);

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setView(gv);

                final AlertDialog dialog=builder.create();

                dialog.show();

                dialog.getWindow().setLayout();

            }
        });


    }

    // Custom method to get the screen width in pixels
    private Point getScreenSize() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        //Display dimensions in pixels
        display.getSize(size);
        return size;
    }

    // Custom method to get status bar height in pixels
    public int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_baprr_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }



    //sobre escritura para el boton imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){
            Uri imgUrl=data.getData();
            Toast.makeText(MainActivity.this,imgUrl.toString(),Toast.LENGTH_SHORT).show();
            btnImg.setImageURI(imgUrl);
        }
    }
}
