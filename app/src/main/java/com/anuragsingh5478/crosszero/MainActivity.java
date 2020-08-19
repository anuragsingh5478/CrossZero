package com.anuragsingh5478.crosszero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] button = new Button[3][3];
    private int chanceCount = 0;
    private boolean player1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                String str = "button_" + i + j;
                int resId = getResources().getIdentifier(str, "id", getPackageName());
                button[i][j] = findViewById(resId);
                button[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcReset();
            }
        });
    }



    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        chanceCount++;
        if(checkForWin()){
            String result ;
            if(player1)
                result = "PLAYER 1 WINS";
            else
                result = "PLAYER 2 WINS";

            Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
            toast.show();


        }

        if(!checkForWin() && chanceCount==9){
            Toast toast = Toast.makeText(getApplicationContext(),"DRAW",Toast.LENGTH_LONG);
            toast.show();
        }

        player1 = !player1;
    }

    private boolean checkForWin(){
        String[][] str = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                str[i][j] = button[i][j].getText().toString();
            }
        }
        //rows
        for(int i=0; i<3; i++){
            if(str[i][0].equals(str[i][1]) && str[i][0].equals(str[i][2]) && !str[i][0].equals(""))
                return true;
        }
        //column
        for(int i=0; i<3; i++){
            if(str[0][i].equals(str[1][i]) && str[0][i].equals(str[2][i]) && !str[0][i].equals(""))
            return true;
        }

        if(str[0][0].equals(str[1][1]) && str[0][0].equals(str[2][2]) && !str[0][0].equals(""))
            return true;

        if(str[0][2].equals(str[1][1]) && str[0][2].equals(str[2][0]) && !str[0][2].equals(""))
            return true;

        return  false;
    }

    private void funcReset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
            }
        }
        player1 = true;
        chanceCount = 0;
    }
}
