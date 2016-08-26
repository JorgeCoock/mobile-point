package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        DataBaseHelper mDatabaseHelper = new DataBaseHelper(this, null, null, 1);

        ArrayList<Admin> admin_list =  mDatabaseHelper.getAdminIndex();

        launchNextActivity(admin_list.isEmpty());


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    public void launchNextActivity(final boolean option){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (option){
                    startActivity(new Intent(WelcomePage.this, AdminFormPage.class));
                }else{
                    startActivity(new Intent(WelcomePage.this, PointPage.class));
                }
            }
        }, 2000);

    }


}
