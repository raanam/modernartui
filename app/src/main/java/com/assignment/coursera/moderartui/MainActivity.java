package com.assignment.coursera.moderartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener,
        Dialog.OnClickListener{

    private ImageView[] colorTiles;
    private SeekBar seekBar;
    private int lastProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colorTiles = new ImageView[4];
        colorTiles[0] = (ImageView) findViewById(R.id.imageView1);
        colorTiles[1] = (ImageView) findViewById(R.id.imageView2);
        colorTiles[2] = (ImageView) findViewById(R.id.imageView3);
        colorTiles[3] = (ImageView) findViewById(R.id.imageView4);

        seekBar = (SeekBar) findViewById(R.id.seekBarChangeColor);
        seekBar.setOnSeekBarChangeListener(this);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     //   .setAction("Action", null).show();
            //}
        //});
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
            showInfoDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int progressDiff = progress - lastProgress;
        lastProgress = progress;
        for(int imgCtr = 0; imgCtr < colorTiles.length; imgCtr++){
            ColorDrawable drawable = (ColorDrawable) colorTiles[imgCtr].getBackground();
            int currentColor = drawable.getColor();
            drawable.setColor(currentColor + progressDiff);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void showInfoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Visit MOMA", this);
        builder.setNegativeButton("Not Now", this);
        builder.setMessage("Inspired by the works of artists such as" +
                " Piet Mondrian and Ben Nicholson." +
                "\n\n Click below to learn more!");
        AlertDialog dialog = builder.show();
        TextView txtMessage = (TextView)dialog.findViewById(android.R.id.message);
        txtMessage.setGravity(Gravity.CENTER);
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == -1){
            Uri momaUri = Uri.parse("http://moma.org");
            Intent openMomaIntent = new Intent(Intent.ACTION_VIEW, momaUri);
            startActivity(openMomaIntent);
        }
    }
}
