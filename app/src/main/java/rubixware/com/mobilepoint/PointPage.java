package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PointPage extends ActionBarActivity {

    private WebView mWebView;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    public static String filename = "directionsStorage";
    public ArrayList<String> readDirections;
    private Storage s1;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String mCameraPhotoPath;
    private ValueCallback<Uri[]> mFilePathCallback;
    private static final int INPUT_FILE_REQUEST_CODE = 1;
    private static final String EXTRA_FROM_NOTIFICATION = "EXTRA_FROM_NOTIFICATION";
    Intent intent;
    private String username;
    User user;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    PageQueries pageQueries = new PageQueries();
    UserQueries userQueries = new UserQueries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Metodos normales para el layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);


        //Creamos objeto storage para usar el internal storage
        s1 = new Storage(getApplicationContext());
        //Llenamos un arraylist con las direcciones
        readDirections = s1.readMultipleStorage(filename);


        //Creamos un objeto de la clase Webview
        mWebView = new WebView(this);
        //Relacionamos el objeto webview con el webview del .xml
        mWebView = (WebView) findViewById(R.id.webview);
        //Asignamos que el webview acepte cosas de JS
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        //mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        mWebView.setWebViewClient(new HelloWebViewClient(this));

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title){
                getWindow().setTitle(title);
            }
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                    } catch (IOException ex) {
                        Log.e("Photo", "Unable to create Image File", ex);
                    }

                    if (photoFile != null) {
                        mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("image/*");

                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

                startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
                return true;
            }

        });

        //asignamos la primera dirección
        mWebView.loadUrl("www.google.com");

        //Hacemos que nuestro webview sea parte de la clase HelloWebViewClient


        //Creamos un ListView con nuestro ListView ya definido
        mDrawerList = (ListView) findViewById(R.id.navList);


        //Creamos un DrawerLayout con el DrawerLayout del .xml
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //Al string este, le damos el valor del titulo
        mActivityTitle = getTitle().toString();


        //Llenamos el navigator drawer y activamos el onClickListener
        addDrawerItems();


        //Metodo para el navigator drawer y su interacción/cambios
        setupDrawer();


        //Hacemos el toggle switch
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //Traems el ListView al frente de la pantalla al ser llamado
        mDrawerList.bringToFront();


        //Forzamos a que se abra el layout del navigation drawer
        mDrawerLayout.requestLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        readDirections = s1.readMultipleStorage(filename);
        addDrawerItems();
        setupDrawer();




    }

    //Manejamos los estados de la actividad si se preciona la tecla KeyDown
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //Creamos el actionbar-overflow
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_point_page, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }


    //ponemos en el overflow nuestras opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.userLogin) {
            changeLayout();
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Guardamos los datos de la actividad que estamos ejecutando
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }


    //Esto hace que no se reinicie cuando el dispositivo rote.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //nada
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Nada
        }
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    //Metodo para cambiar de layout y actividad
    public void changeLayout() {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }


    //Metodo para llenar el Nav Drawer
    private void addDrawerItems() {

        //Llenamos el adaptador con el arrraylist correspondiente
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getUserPages());

        //Le asignamos el adaptador al listview
        mDrawerList.setAdapter(mAdapter);

        //Sobreescribimos el metodo onclick para que haga lo que queramos
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView) view).getText().toString();
                mWebView.loadUrl(item);
                Toast.makeText(PointPage.this, "Opening: " + item, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                String CLICK = "click";
                Log.d(CLICK, "CLICK");
            }
        });
    }


    //Con esto preparamos el navigation drawer
    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Páginas");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()

            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    private boolean checkBlankFile() {
        File file = getBaseContext().getFileStreamPath(filename);
        return file.exists();
    }
    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName  = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile  = File.createTempFile(imageFileName, ".jpg", storageDir);
        return imageFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null){
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        Uri[] results = null;

        if (resultCode == Activity.RESULT_OK){
            if (data == null || data.getData() == null){
                if (mCameraPhotoPath != null){
                    results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                }
            }else{
                String dataString = data.getDataString();
                if (dataString != null){
                    results = new Uri[]{Uri.parse(dataString)};
                }
            }
        }
        mFilePathCallback.onReceiveValue(results);
        mFilePathCallback = null;
        return;
    }

    private ArrayList<String> getUserPages(){
        ArrayList<Page> pagesCollection = pageQueries.getUserPages(user.getId(), dbHelper);
        ArrayList<String> pagesArray = new ArrayList<>();
        for (int i =0; i < pagesCollection  .size(); i++){
            pagesArray.add(pagesCollection.get(i).getUrl());
        }
        return pagesArray;
    }

    }
