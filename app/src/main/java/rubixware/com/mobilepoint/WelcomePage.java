package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

public class WelcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        boolean file = checkPasswordFile("etc");
        startTimer(file);
        DataBaseHelper mDatabaseHelper = new DataBaseHelper(this, null, null, 1);
        ArrayList<Admin> admin_list =  mDatabaseHelper.getAdminIndex();
        Log.e("TAG","000");
        System.out.println("/////////////////////////////////");
        if (admin_list.isEmpty()){
            System.out.println("Esta vacia :( eeeee uwu");
        }else{
            System.out.println("ño");
        }
        System.out.println(admin_list);

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
