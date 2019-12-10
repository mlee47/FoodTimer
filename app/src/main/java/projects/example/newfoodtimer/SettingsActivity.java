package projects.example.newfoodtimer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity{
        SeekBar seekbar;
        TextView textView;
        AudioManager audioManager;
        RadioGroup radioGroup;
        RadioButton radio1;
        RadioButton radio2;
        RadioButton radio3;


        Button save;

        int modeset = 0;
        int volume = 0;

        private int REQUEST_TEST = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings);

            seekbar = (SeekBar) findViewById(R.id.seekBar);
            textView = (TextView) findViewById(R.id.textView1);
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radio1 = (RadioButton) findViewById(R.id.radio1);
            radio2 = (RadioButton) findViewById(R.id.radio2);
            radio3 = (RadioButton) findViewById(R.id.radio3);

            save = findViewById(R.id.save);

            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));


//        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);

//        seekbar.setProgress();


            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                SharedPreferences key_2 = getSharedPreferences("key_2",MODE_PRIVATE);
                SharedPreferences.Editor editor = key_2.edit();

                String Alarm_volume = getResources().getString(R.string.alarmvolume);

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    textView.setText("\n" + Alarm_volume + " " + i );
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                    volume = i;

                    editor.putInt("volumeset",volume);
                    editor.commit();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


            save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Intent it = new Intent(SettingsActivity.this, GeneralTimer.class);

                    //it.putExtra("close",4);

                    SharedPreferences key_1 = getSharedPreferences("key_1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = key_1.edit();

                    if (radio1.isChecked())
                      {
                          //it.putExtra("it_checked", 1);
                          modeset = 1;
                        }

                    else if (radio2.isChecked())
                        {
                            //it.putExtra("it_checked", 2);
                            modeset = 2;
                        }
                    else if (radio3.isChecked())
                        {
                            //it.putExtra("it_checked", 3);
                            modeset = 3;
                        }


                    editor.putInt("modeset",modeset);
                    editor.commit();

                    //setResult(RESULT_OK,it);
                    finish();
                }
            });

            SharedPreferences key_1 = getSharedPreferences("key_1", MODE_PRIVATE);
            int amodeset = key_1.getInt("modeset",0);

            if(amodeset == 1)
                radio1.setChecked(true);
            else if(amodeset == 2)
                radio2.setChecked(true);
            else if(amodeset == 3)
            {
                radio3.setChecked(true);
            }

            SharedPreferences key_2 = getSharedPreferences("key_2",MODE_PRIVATE);
            int volumeset = key_2.getInt("volumeset",0);
            seekbar.setProgress(volumeset);


        }

}





