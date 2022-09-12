package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.e_learning.Database.MyDatabase;
import com.project.e_learning.Model.Common;
import com.project.e_learning.R;
import com.project.e_learning.databinding.ActivityQuestionBinding;
import com.project.e_learning.ui.Interfaces.ItemClickListener;

import java.util.Collections;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityQuestionBinding binding;
    final static long INTERVAL = 1000;
    final static long TIMEOUT = 10000;
    int progressValue = 0;
    int textcolor=0;
    CountDownTimer mCountDown;
    private MyDatabase mydb;
    private String Subject;
    int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer,incorrectAnswer,notAttempt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mydb = new MyDatabase(this);
        Common.list_question = mydb.getQuestions("");
        totalQuestion = Common.list_question.size();
        Collections.shuffle(Common.list_question);
        binding.mainRl.setBackgroundColor(textcolor);
        binding.btnAnswerA.setTextColor(textcolor);
        binding.btnAnswerB.setTextColor(textcolor);
        binding.btnAnswerC.setTextColor(textcolor);
        binding.btnAnswerD.setTextColor(textcolor);
        binding.btnAnswerA.setOnClickListener(this);
        binding.btnAnswerB.setOnClickListener(this);
        binding.btnAnswerC.setOnClickListener(this);
        binding.btnAnswerD.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (index < totalQuestion) {
            Boolean optbtn = false;
            Boolean ansbtn = false;
            String AnsStr = null;
            String AnsTag = null;
            Button clickedButton = (Button) view;
            //int color = ((ColorDrawable)clickedButton.getBackground()).getColor();
            switch (clickedButton.getId()){
                case R.id.LeftSideBtn:
                    if (clickedButton.getText().toString().equals("Submit")){
                        if (optbtn){
                            if(ansbtn){
                                score += 10;
                                correctAnswer++;
                                ansbtn = false;
                                SetBtnStyle(AnsStr,AnsTag,false);
                            } else {
                                incorrectAnswer++;
                                SetBtnStyle(AnsStr,AnsTag,true);
                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ClearBtnStyle();
                            optbtn = false;
                            showQuestion(++index);
                        } else {
                            Toast.makeText(QuestionActivity.this, "Select Answer First or Skip the Question", Toast.LENGTH_SHORT).show();
                        }
                    }
                        break;
                case R.id.RightSideBtn:
                    if (clickedButton.getText().toString().equals("Skip")){
                        notAttempt++;
                    }
                    break;
                case R.id.btnAnswerA:
                case R.id.btnAnswerB:
                case R.id.btnAnswerC:
                case R.id.btnAnswerD:
                    optbtn = true;
                    AnsTag = clickedButton.getTag().toString();
                    AnsStr = Common.list_question.get(index).getCorrectAnswer();
                    if (AnsTag.equals(AnsStr)){
                       ansbtn = true;
                    }
                    break;
                default:
                    break;
            }
            } else {
                Intent intent = new Intent(this, ResultActivity.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("ATTEMPT",totalQuestion-notAttempt);
                dataSend.putInt("CORRECT", correctAnswer);
                dataSend.putInt("INCORRECT", incorrectAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            binding.txtScore.setText(String.format("%d", score));
        }


    private void showQuestion(int index) {
        if (index < totalQuestion) {
            thisQuestion++;
            binding.txtTotalQuestion.setText(String.format("%d / %d", thisQuestion, totalQuestion));

            if (Common.list_question.get(index).getIsImageQuestion().equals("true")) {
//                Picasso.with(getBaseContext())
//                        .load(Common.list_question.get(index).getQuestion())
//                        .into(question_image);
//                question_image.setVisibility(View.VISIBLE);
//                question_text.setVisibility(View.INVISIBLE);

            } else {
                binding.questionText.setText("Q. "+Common.list_question.get(index).getQuestion());
                //binding.questionImage.setVisibility(View.INVISIBLE);
                //binding.questionText.setVisibility(View.VISIBLE);
            }

            binding.btnAnswerA.setText(Common.list_question.get(index).getAnswerA());
            binding.btnAnswerB.setText(Common.list_question.get(index).getAnswerB());
            binding.btnAnswerC.setText(Common.list_question.get(index).getAnswerC());
            binding.btnAnswerD.setText(Common.list_question.get(index).getAnswerD());
        } else {

            Intent intent = new Intent(this, ResultActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            dataSend.putInt("INCORRECT", incorrectAnswer);
            dataSend.putString("SUBJECT", Subject);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();

        }

    }

    public void SetBtnStyle(String btnans, String btntag,boolean wrong){
        if (binding.btnAnswerA.getTag().toString().equals(btnans)) {
            binding.btnAnswerA.setBackgroundColor(Color.GREEN);
        }else if (binding.btnAnswerB.getTag().toString().equals(btnans)) {
            binding.btnAnswerB.setBackgroundColor(Color.GREEN);
        }else if (binding.btnAnswerC.getTag().toString().equals(btnans)) {
            binding.btnAnswerC.setBackgroundColor(Color.GREEN);
        }else if (binding.btnAnswerD.getTag().toString().equals(btnans)) {
            binding.btnAnswerD.setBackgroundColor(Color.GREEN);
        }
        if(wrong){
            if (binding.btnAnswerA.getTag().toString().equals(btntag)) {
                binding.btnAnswerA.setBackgroundColor(Color.RED);
            }else if (binding.btnAnswerB.getTag().toString().equals(btntag)) {
                binding.btnAnswerB.setBackgroundColor(Color.RED);
            }else if (binding.btnAnswerC.getTag().toString().equals(btntag)) {
                binding.btnAnswerC.setBackgroundColor(Color.RED);
            }else if (binding.btnAnswerD.getTag().toString().equals(btntag)) {
                binding.btnAnswerD.setBackgroundColor(Color.RED);
            }
        }
        //button.setBackgroundColor(color);
    }

    public void ClearBtnStyle(){
        binding.btnAnswerA.setBackgroundColor(Color.WHITE);
        binding.btnAnswerB.setBackgroundColor(Color.WHITE);
        binding.btnAnswerC.setBackgroundColor(Color.WHITE);
        binding.btnAnswerD.setBackgroundColor(Color.WHITE);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        totalQuestion = Common.list_question.size();
//        showQuestion(index);
//    }
}