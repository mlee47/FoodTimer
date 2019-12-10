package projects.example.newfoodtimer;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.security.Permission;
import java.util.Locale;


    public class GeneralTimer extends AppCompatActivity {
        private EditText mEditTextInput_min;
        private EditText mEditTextInput_sec;
        private TextView mTextViewCountDown;
        private Button mButtonSet;
        private Button mButtonStart;
        private Button mButtonReset;


        private Button mButtonPause;
        private Button mButtonContinue;



        private Button mButtonplus30s;
        private Button mButtonminus30s;



        private CountDownTimer mCountDownTimer;

        private boolean mTimerRunning;

        private long mStartTimeInMillis;
        private long mTimeLeftInMillis;
        private long mEndTime;


        private int it_checked;
        private int close;


        private BackPressCloseHandler backPressCloseHandler;

        private Vibrator vibrator;

        private final static int PERMISSION_REQ_CODE = 100;

        private final static int REQUEST_ACT = 1;

        Button intent_btn;
        View settings;


        NumberPicker numberpicker1;
        NumberPicker numberpicker2;

        int picked_min;
        int picked_sec;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.generaltimer);

            backPressCloseHandler = new BackPressCloseHandler(this);

            intent_btn = findViewById(R.id.button);

            intent_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    mTimerRunning = false;

                    Intent intent = new Intent(projects.example.newfoodtimer.GeneralTimer.this,Selection.class);
                    startActivity(intent);
                }
            });



            settings = findViewById(R.id.setting);

            settings.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(projects.example.newfoodtimer.GeneralTimer.this, SettingsActivity.class);
                    //startActivityForResult(intent, REQUEST_ACT);
                    startActivity(intent);
                }
            });




//            Intent it = getIntent();
//            it_checked = it.getExtras().getInt("it_checked");


//            mEditTextInput_min = findViewById(R.id.edit_text_input_min);
//            mEditTextInput_sec = findViewById(R.id.edit_text_input_sec);
            mTextViewCountDown = findViewById(R.id.text_view_countdown);

            mButtonSet = findViewById(R.id.button_set);
            mButtonStart = findViewById(R.id.button_start);
            mButtonReset = findViewById(R.id.button_reset);

            mButtonplus30s = findViewById(R.id.plus30s);
            mButtonminus30s = findViewById(R.id.minus30s);


            mButtonPause = findViewById(R.id.button_pause);
            mButtonContinue = findViewById(R.id.button_continue);



//            mButtonSet.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String input_min = mEditTextInput_min.getText().toString();
//                    if (input_min.length() == 0) {
//                        Toast.makeText(projects.example.newfoodtimer.GeneralTimer.this, "양쪽에 숫자를 채워주세요\nPlease enter number on both sides", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    long millisInput_min = Long.parseLong(input_min) * 60000;
////
//
//                    String input_sec = mEditTextInput_sec.getText().toString();
//                    if (input_sec.length() == 0) {
//                        Toast.makeText(projects.example.newfoodtimer.GeneralTimer.this, "양쪽에 숫자를 채워주세요\nPlease enter number on both sides", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    long millisInput_sec = Long.parseLong(input_sec) * 1000;
////
//
//                    if (millisInput_min==0 & millisInput_sec==0){
//                        Toast.makeText(projects.example.newfoodtimer.GeneralTimer.this, "0분 0초는 실행할 수 없습니다\nPlease enter a positive number", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//
//                    long millisInput = millisInput_min + millisInput_sec;
//
//                    setTime(millisInput);
//                    mEditTextInput_min.setText("");
//                    mEditTextInput_sec.setText("");

            numberpicker1 = (NumberPicker)findViewById(R.id.numberPicker1);
            numberpicker2 = (NumberPicker)findViewById(R.id.numberPicker2);


            numberpicker1.setMinValue(0);
            numberpicker1.setMaxValue(90);

            numberpicker2.setMinValue(0);
            numberpicker2.setMaxValue(5);

            NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
                @Override
                public String format(int value) {
                    int temp = value * 10;
                    return "" + temp;
                }
            };
            numberpicker2.setFormatter(formatter);


//
//                }
//            });

            mButtonSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    picked_min = numberpicker1.getValue();
                    picked_sec = numberpicker2.getValue();

                    setTime(picked_min * 60000 + picked_sec * 10000);

//                    Intent intent = new Intent(projects.example.newfoodtimer.GeneralTimer.this,SettingsActivity.class);
//                    startActivityForResult(intent, REQUEST_ACT);


//                    numberpicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                        @Override
//                        public void onValueChange(NumberPicker picker, int oldVal_min, int newVal_min) {
//                            setTime(mTimeLeftInMillis + (newVal_min-oldVal_min)*60000);
//                        }
//
//                    });
//
//                    numberpicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                        @Override
//                        public void onValueChange(NumberPicker picker, int oldVal_sec, int newVal_sec) {
//                            setTime(mTimeLeftInMillis + (newVal_sec-oldVal_sec)*1000);
//                        }
//                    });

                }

            });


            mButtonplus30s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTimeLeftInMillis += 30000;
                    setTime(mTimeLeftInMillis);
                }
            });

            mButtonminus30s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mTimeLeftInMillis >= 30000){
                    mTimeLeftInMillis -= 30000;
                    setTime(mTimeLeftInMillis);
                    }
                }
            });

            mButtonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (mTimerRunning) {
//                        pauseTimer();
//                    } else {
//
//                    }

                    startTimer();
                }
            });

            mButtonPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pauseTimer();
                }
            });

            mButtonContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startTimer();
                }
            });

            mButtonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTime(0);
                    //resetTimer();
                }
            });

            vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        }



        @Override
        protected void onActivityResult(int requestCode, int resultCode, final Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_ACT) {
                if (resultCode == RESULT_OK) {
                    it_checked = data.getIntExtra("it_checked",0);
//                    close = data.getIntExtra("close",0);
//                    if (close == 4){
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                startTimer();
//                            }
//                        }, 1000);
//
//                    }

                }

            }

//            if (resultCode == RESULT_OK){
//                return;
//            }
//
//            if (requestCode == REQUEST_ACT){
//                Intent it = getIntent();
//                it_checked = it.getExtras().getInt("it_checked");
//
//                finish();
//            }
        }






//        private void checkpermission() {
//            // 권한 허용은 마시멜로우 이상에서만 사용하므로 버전검사를 한다.
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                int hasVibratePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE);
//                if(hasVibratePermission != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, PERMISSION_REQ_CODE);
//
//                    // onRequestPermissionResult 함수로 권한허용에 대한 콜백을 받는다.
//                }
//            }
//        }

        private void setTime(long milliseconds) {
            mStartTimeInMillis = milliseconds;
            resetTimer();
        }

        private void startTimer() {
            mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }


                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    updateWatchInterface();

                    SharedPreferences key_1 = getSharedPreferences("key_1", MODE_PRIVATE);
                    int amodeset = key_1.getInt("modeset",0);


                    AlarmManager mAudioManger;
                    mAudioManger = (AlarmManager)getSystemService(ALARM_SERVICE);

                    long[] pattern = {500, 500, 500, 500, 500, 500}; // 진동의 멈춤, 시작, 멈춤, 시작의 반복패턴임.
                    // 진동 시작

                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);


                    switch (amodeset){
                        case 1:
                            mp.setLooping(true);
                            mp.start();
                            break;

                        case 2:
                            vibrator.vibrate(pattern, 0);
                        break;

                        case 3:
                            vibrator.vibrate(pattern, 0);
                            mp.setLooping(true);
                            mp.start();//
                            break;
                    }

//                    long[] pattern = {500, 500, 500, 500, 500, 500}; // 진동의 멈춤, 시작, 멈춤, 시작의 반복패턴임.
//                    // 진동 시작
//                    vibrator.vibrate(pattern, 0);   // 0은 무한반복 설정임.

//                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
//                    mp.setLooping(true);
//                    mp.start();


                    // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
                    AlertDialog.Builder alBuilder = new AlertDialog.Builder(GeneralTimer.this);
                    alBuilder.setCancelable(false);
                    alBuilder.setTitle("알람");
                    alBuilder.setMessage("시간이 종료되었습니다.");

                    // "예" 버튼을 누르면 실행되는 리스너
                    alBuilder.setPositiveButton("알람 끄기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            vibrator.cancel();
                            mp.stop();
                        }
                    });

                    alBuilder.show();
                }
            }.start();

            mTimerRunning = true;
            updateWatchInterface();
        }

        private void pauseTimer() {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            updateWatchInterface();
        }

        private void resetTimer() {
            mTimeLeftInMillis = mStartTimeInMillis;
            updateCountDownText();
            updateWatchInterface();
        }

        private void updateCountDownText() {
            int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
            int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted;
            if (hours > 0) {
                timeLeftFormatted = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, seconds);
            } else {
                timeLeftFormatted = String.format(Locale.getDefault(),
                        "%02d:%02d", minutes, seconds);
            }

            mTextViewCountDown.setText(timeLeftFormatted);
        }

        private void updateWatchInterface() {
            if (mTimerRunning) {
//                mEditTextInput_min.setVisibility(View.INVISIBLE);
//                mEditTextInput_sec.setVisibility(View.INVISIBLE);
                mButtonSet.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.INVISIBLE);
                mButtonContinue.setVisibility(View.INVISIBLE);
                //mButtonStartPause.setText("일시정지");
                mButtonStart.setVisibility(View.INVISIBLE);
                mButtonPause.setVisibility(View.VISIBLE);
                //mButtonStartPause.setBackgroundColor(getColor(R.color.pausebutton));

            } else if ( mTimeLeftInMillis < mStartTimeInMillis){
                mButtonSet.setVisibility(View.VISIBLE);
                //mButtonStartPause.setText("계속");
                //mButtonStartPause.setBackgroundColor(getColor(R.color.continuebutton));
                mButtonStart.setVisibility(View.INVISIBLE);
                mButtonPause.setVisibility(View.INVISIBLE);
                mButtonContinue.setVisibility(View.VISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
                else{
//                mEditTextInput_min.setVisibility(View.VISIBLE);
//                mEditTextInput_sec.setVisibility(View.VISIBLE);
                mButtonSet.setVisibility(View.VISIBLE);
                mButtonStart.setVisibility(View.VISIBLE);
                //mButtonStartPause.setText("시작");
               // mButtonStartPause.setBackgroundColor(getColor(R.color.startbutton));

                if (mTimeLeftInMillis < 1000) {
                    mButtonStart.setVisibility(View.INVISIBLE);
                    mButtonContinue.setVisibility(View.INVISIBLE);
                    mButtonPause.setVisibility(View.INVISIBLE);
                } else {
                    mButtonStart.setVisibility(View.VISIBLE);
                    mButtonContinue.setVisibility(View.INVISIBLE);
                    mButtonPause.setVisibility(View.INVISIBLE);
                }

                if (mTimeLeftInMillis < mStartTimeInMillis) {
                    mButtonReset.setVisibility(View.VISIBLE);
                } else {
                    mButtonReset.setVisibility(View.INVISIBLE);
                }
            }
        }



        @Override
        protected void onStop() {
            super.onStop();

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putLong("startTimeInMillis", mStartTimeInMillis);
            editor.putBoolean("timerRunning", mTimerRunning);
            editor.putLong("endTime", mEndTime);

            editor.apply();

            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }
        }

        @Override
        protected void onStart() {
            super.onStart();

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

            Intent intent = getIntent();

            float fromButton = intent.getExtras().getFloat("key",0);

            float from_Button = fromButton * 60000;


            mStartTimeInMillis = (long) prefs.getFloat("millisLeft", from_Button);
            mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
            mTimerRunning = prefs.getBoolean("timerRunning", false);

            updateCountDownText();
            updateWatchInterface();

            if (mTimerRunning) {
                mEndTime = prefs.getLong("endTime", 0);
                mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

                if (mTimeLeftInMillis < 0) {
                    mTimeLeftInMillis = 0;
                    mTimerRunning = false;
                    updateCountDownText();
                    updateWatchInterface();

                } else {
                    startTimer();
                }
            }

        }

        public class BackPressCloseHandler {
            String back_btn = getResources().getString(R.string.backbtn);

            private long backKeyPressedTime = 0;
            private Toast toast;
            private Activity activity;
            public BackPressCloseHandler(Activity context) {
                this.activity = context;
            }
            public void onBackPressed() {
                if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                    backKeyPressedTime = System.currentTimeMillis();
                    showGuide();
                    return;
                }
                if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                    activity.finish();
                    toast.cancel();
                }
            }
            public void showGuide() {
                toast = Toast.makeText(activity, back_btn, Toast.LENGTH_SHORT);
                toast.show();
            }
        }


        @Override
        public void onBackPressed() {
            backPressCloseHandler.onBackPressed();

        }
//            // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
//            AlertDialog.Builder alBuilder = new AlertDialog.Builder(GeneralTimer.this);
//            alBuilder.setMessage("종료하시겠습니까?");
//
//            // "예" 버튼을 누르면 실행되는 리스너
//            alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            // "아니오" 버튼을 누르면 실행되는 리스너
//            alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    return;
//                }
//            });
//            alBuilder.setTitle("앱 종료");
//            alBuilder.show();
//        }

    }

