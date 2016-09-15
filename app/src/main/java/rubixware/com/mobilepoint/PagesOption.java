package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PagesOption extends Activity{

    Intent intent;
    String username;
    TextView title;
    String textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_option);
        intent = getIntent();
        username = intent.getStringExtra("username");
        textViewTitle = "Páginas de: "+username;
        title = (TextView) findViewById(R.id.page_option_title);
        title.setText(textViewTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button pageButton = (Button) findViewById(R.id.page_button);
        Button recommendedPageButton = (Button) findViewById(R.id.recommended_page_button);
        Button goBack = (Button) findViewById(R.id.goBack);
        pageButton.setTypeface(font);
        recommendedPageButton.setTypeface(font);
        goBack.setTypeface(font);
    }

    public void goBack(View view){
        Intent intent = new Intent(PagesOption.this, PanelUsersOption.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    public void openPageForm(View view){
        Intent intent = new Intent(PagesOption.this, PageForm.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void openRecommendedPages(View view){
        Intent intent = new Intent(PagesOption.this, RecommendedPagesForm.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
