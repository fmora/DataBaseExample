package cl.telematica.databaseexample;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.databaseexample.adapters.RssAdapter;
import cl.telematica.databaseexample.adapters.RssAdapter2;
import cl.telematica.databaseexample.database.DataBaseClass;
import cl.telematica.databaseexample.models.EarthQuakeDataModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
import android.widget.ListView;

public class DetailsActivity extends Activity {
	
//	private String url = null;
//	private WebView webView;
//	private ProgressBar mProgressBar;
//	private RelativeLayout hPBarLayout;

	private List<EarthQuakeDataModel> list = new ArrayList<EarthQuakeDataModel>();
	private ListView listView;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);	
		listView = (ListView) findViewById(R.id.listView1);
		get7last();
		RssAdapter2 adapter = new RssAdapter2(getApplicationContext(), R.string.app_name, list);
		listView.setAdapter(adapter);
		
//		webView = (WebView) findViewById(R.id.webView);
//		hPBarLayout = (RelativeLayout) findViewById(R.id.hPBarLayout);
//		mProgressBar = (ProgressBar) findViewById(R.id.legacy_navigation_progressBar);
//		
//		webView.getSettings().setJavaScriptEnabled(true);
//		webView.getSettings().setBuiltInZoomControls(true);
//		
//		webView.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress)
//            {
//                mProgressBar.setProgress(progress);
//            }
//        });
//  
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
//            {
//                // Handle the error
//            }
//  
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                view.loadUrl(url);
//                return true;
//            }
//            
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon){
//            	hPBarLayout.setVisibility(View.VISIBLE);
//            	super.onPageStarted(view, url, favicon);
//            }
//            
//            @Override
//            public void onPageFinished(WebView view, String url){
//            	hPBarLayout.setVisibility(View.GONE);
//            	super.onPageFinished(view, url);
//            }
//        });
//		webView.loadUrl(url);
		
	}
	//metodo para obtener los 7 primeros
    public int get7last(){
    	int count = 7;
    	DataBaseClass  dbInstance = new DataBaseClass(this);
    	SQLiteDatabase db = dbInstance.getReadableDatabase();
    	EarthQuakeDataModel aux;
    	if(db != null){
    		Cursor c = db.rawQuery("SELECT * FROM alumnos",null);
    		if(c.moveToFirst()){
    			do{
    				aux = new EarthQuakeDataModel();
    				aux.title = c.getString(1);
    				aux.magnitude = c.getString(2);
    				aux.location = c.getString(3);
    				aux.depth = c.getString(4);
    				aux.latitude = c.getString(5);
    				aux.longitude = c.getString(6);
    				aux.dateTime = c.getString(7);
    				aux.link = c.getString(8);
    				list.add(aux);
    				count--;
    				Log.d("size", String.valueOf(count));
    			}while(c.moveToNext() && count > 0);
    		}
    		c.close();
    	}
    	db.close();
    	dbInstance.close();
    	return (7-count);
    }
}
