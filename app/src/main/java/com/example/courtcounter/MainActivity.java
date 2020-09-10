package com.example.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int ScoreA = 0;
    int ScoreB = 0;
    String TeamA = "Team A";
    String TeamB = "Team B";
    Toast toastMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * Team A Score increased By 3
    * */
    public void threeForTeamA(View view) {
        ScoreA+=3;
        viewToast("+3 for "+TeamA);
        displayA();
    }

    /*
     * Team A Score increased By 2
     * */
    public void twoForTeamA(View view) {
        ScoreA+=2;
        viewToast("+2 for "+TeamA);
        displayA();
    }

    /*
     * Team A Score increased By 1
     * */
    public void oneForTeamA(View view) {
        ScoreA+=1;
        viewToast("+1 for "+TeamA);
        displayA();
    }

    /*
     * Team B Score increased By 3
     * */
    public void threeForTeamB(View view) {
        ScoreB+=3;
        viewToast("+3 for "+TeamB);
        displayB();
    }

    /*
     * Team B Score increased By 2
     * */
    public void twoForTeamB(View view) {
        ScoreB+=2;
        viewToast("+2 for "+TeamB);
        displayB();
    }

    /*
     * Team B Score increased By 1
     * */
    public void oneForTeamB(View view) {
        ScoreB+=1;
        viewToast("+1 for "+TeamB);
        displayB();
    }

    /*
     * Show Toast Message on Score Update!
     * */
    public void viewToast(String message){
        if(toastMessage!=null){
            toastMessage.cancel();
        }
        toastMessage = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toastMessage.show();
    }

    /*
     * Update Team A Score
     * */
    void displayA(){
        TextView TeamAScore = (TextView) findViewById(R.id.teamA_score);
        TeamAScore.setText(String.valueOf(ScoreA));
    }

    /*
     * Update Team B Score
     * */
    void displayB(){
        TextView TeamBScore = (TextView) findViewById(R.id.teamB_score);
        TeamBScore.setText(String.valueOf(ScoreB));
    }

    /*
     * Calculate Result on Game Over!
     * */
    public void gameOver(View view) {
        AlertDialog.Builder gameOverAlert = new AlertDialog.Builder(this);

        gameOverAlert.setTitle("Game Over!");
        gameOverAlert.setMessage("Are you sure, you want to reset scores??");
        if(ScoreA > ScoreB) gameOverAlert.setMessage(TeamA +" won by "+ (ScoreA - ScoreB) +" points!");
        else if(ScoreA < ScoreB) gameOverAlert.setMessage(TeamB  +" won by "+ (ScoreB - ScoreA) +" points!");
        else gameOverAlert.setMessage("Match Draw!!!");
        gameOverAlert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetScores();
            }
        });
        gameOverAlert.show();
    }

    /*
     * Display Result on Game Over!
     * */
    void displayResult(String result){
        TextView Result = (TextView) findViewById(R.id.result_text_view);
        Result.setText(result);
    }

    /*
     * Show Reset dialog and
     * Reset scores when reset button is clicked
     * else cancel the alert
     * */
    public void reset(View view) {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);

        resetAlert.setTitle("Alert!");
        resetAlert.setMessage("Are you sure, you want to reset scores??");
        resetAlert.setPositiveButton("Yes, reset scores!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetScores();
            }
        });
        resetAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        resetAlert.show();
    }

    /*
    * Reset scores when Reset is called
    * */
    void resetScores(){
        ScoreA = 0;
        ScoreB = 0;
        TeamA = "Team A";
        TeamB = "Team B";
        displayA();
        displayB();
//        displayResult("");
        UpdateName(TeamA, 'A');
        UpdateName(TeamB, 'B');
    }
    /*
     * Rename Team A name
     * */
    public void RenameA(View view){
        rename('A');
    }

    /*
     * Rename Team B name
     * */
    public void RenameB(View view){
        rename('B');
    }

    /*
     * Rename dialog Box appear when rename is clicked
     * */
    void rename(final char c){
        AlertDialog.Builder renameAlert = new AlertDialog.Builder(this);

        renameAlert.setTitle("Enter Name For Team " + (c=='A'?"A":"B"));

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        renameAlert.setView(input);

        renameAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(c =='A'){
                    TeamA = input.getText().toString();;
                    UpdateName(TeamA, c);

                }else if(c =='B'){
                    TeamB = input.getText().toString();
                    UpdateName(TeamB, c);
                }
            }
        });
        renameAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        renameAlert.show();
    }

    void UpdateName(String name, char team){
        if(team == 'A'){
            TextView teamATextView = (TextView) findViewById(R.id.teamA_text_view);
            teamATextView.setText(name);
        }else if(team == 'B'){
            TextView teamBTextView = (TextView) findViewById(R.id.teamB_text_view);
            teamBTextView.setText(name);
        }
    }
}