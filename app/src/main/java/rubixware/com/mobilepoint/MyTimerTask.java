package rubixware.com.mobilepoint;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.File;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {

    WelcomePage welcomepage;
    boolean checkFile;


    public MyTimerTask(WelcomePage welcomepage, boolean checkfile) {
        this.welcomepage = welcomepage;
        this.checkFile = checkfile;
    }

    public boolean isCheckFile() {
        return checkFile;
    }

    @Override
    public void run(){
        boolean check = isCheckFile();
        welcomepage.changeWelcomeLayout(check);
    }



}
