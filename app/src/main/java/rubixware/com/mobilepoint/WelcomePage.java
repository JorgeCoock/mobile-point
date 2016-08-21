package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import java.io.File;
import java.util.Timer;

public class WelcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        boolean file = checkPasswordFile("etc");
        startTimer(file);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    private void startTimer(boolean checkfile){
        MyTimerTask myTask = new MyTimerTask(this,checkfile);
        Timer myTimer = new Timer();
        myTimer.schedule(myTask, 2666);
    }

    public void changeWelcomeLayout(boolean check){

        if(check){Intent launchAct = new Intent(this,PointPage.class);
            startActivity(launchAct);}
        else{Intent launchAct = new Intent(this,FormPage.class);
            startActivity(launchAct);}
        finish();
    }

    private boolean checkPasswordFile(String filename){
        File file = getBaseContext().getFileStreamPath(filename);

        return file.exists();
    }





}
