package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class PanelAdmins extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_admins);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
