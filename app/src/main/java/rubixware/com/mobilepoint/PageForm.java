package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageForm  extends Activity{

    Intent intent;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setFaIconsToButtons(Typeface font){
        Button goBack = (Button) findViewById(R.id.go_back);
        goBack.setTypeface(font);
    }

    public void goBack(View view){
        Intent intent = new Intent(PageForm.this, PagesOption.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    public void createPage(View view){

    }



}
