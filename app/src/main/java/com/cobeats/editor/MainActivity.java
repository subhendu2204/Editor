package com.cobeats.editor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.commonsware.cwac.richedit.RichEditText;
import com.commonsware.cwac.richtextutils.SpannableStringGenerator;
import com.commonsware.cwac.richtextutils.SpannedXhtmlGenerator;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.handlers.ImageHandler;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends Activity {
    RichEditText tv;
    RichEditText spText;
    public Spanned sp;
    public SpannableStringGenerator stringGenerator = null;
    WebView webView ;
    CheckedTextView bold;
    CheckedTextView italic;
    CheckedTextView underline;
    CheckedTextView strikeThru;
    CheckedTextView header1;
    CheckedTextView header2;
    CheckedTextView header3;
    CheckedTextView quote;
    CheckedTextView code;
    CheckedTextView unorderedlist;
    CheckedTextView orderedlist;
    CheckedTextView link;
    CheckedTextView horizontalRule;

    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // this.tv = (TextView)findViewById(R.id.htmlTextView);
       this.tv = (RichEditText) findViewById(R.id.htmlTextView);


       /* this.tv = (RichEditText) findViewById(R.id.htmlTextView);
        spText = (RichEditText) findViewById(R.id.spText);*/

        /*webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {

                String javascript="javascript:document.body.contentEditable=true;document.designMode='on';void(0);";
                view.loadUrl(javascript);
            }
        });*/

        //webView.loadData(html, "text/html", "UTF-8");
        //webView.loadUrl("http://www.bbc.com/");

        //spText.setText(html);


       /* try {
           // SpannedXhtmlGenerator xhtmlGenerator = new SpannedXhtmlGenerator();
           // String str = xhtmlGenerator.toXhtml(spText.getText());
           // Log.d("subhendu","XHTML == " + str);
            stringGenerator = new SpannableStringGenerator();
            Spannable fromHtml = stringGenerator.fromXhtml(html);
            Log.d("subhendu","spannable == " + fromHtml.toString());
            tv.setText(fromHtml);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*final HtmlSpanner htmlSpanner = new HtmlSpanner();

        sp = htmlSpanner.fromHtml(Input.html);
        tv.setText(sp);*/

        sp = Html.fromHtml(Input.html3,new ImageGetter(tv,this),null);
        tv.setText(sp);

       // WebView webView = (WebView) findViewById(R.id.webView);
       // webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initToolbar(){
        bold = (CheckedTextView) findViewById(R.id.bold);
        italic = (CheckedTextView) findViewById(R.id.italic);
        underline = (CheckedTextView) findViewById(R.id.underline);
        strikeThru = (CheckedTextView) findViewById(R.id.strikeThru);
        header1 = (CheckedTextView) findViewById(R.id.header1);
        header2 = (CheckedTextView) findViewById(R.id.header2);
        header3 = (CheckedTextView) findViewById(R.id.header3);
        code = (CheckedTextView) findViewById(R.id.code);
        quote = (CheckedTextView) findViewById(R.id.quote);
        unorderedlist = (CheckedTextView) findViewById(R.id.unorderedlist);
        orderedlist = (CheckedTextView) findViewById(R.id.orderedList);
        link = (CheckedTextView) findViewById(R.id.link);
        horizontalRule = (CheckedTextView) findViewById(R.id.horizontalRule);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFormat(v);
            }
        };

        bold.setOnClickListener(clickListener);
        italic.setOnClickListener(clickListener);
        underline.setOnClickListener(clickListener);
        strikeThru.setOnClickListener(clickListener);
        header1.setOnClickListener(clickListener);
        header2.setOnClickListener(clickListener);
        header3.setOnClickListener(clickListener);
        code.setOnClickListener(clickListener);
        quote.setOnClickListener(clickListener);
        unorderedlist.setOnClickListener(clickListener);
        orderedlist.setOnClickListener(clickListener);
        link.setOnClickListener(clickListener);
        horizontalRule.setOnClickListener(clickListener);
    }
    public void toggleFormat(View view){
        CheckedTextView ctv = (CheckedTextView)view;
        ctv.toggle();
        boolean ischeck = ctv.isChecked();
        switch (ctv.getId()){
            case R.id.bold:
                tv.applyEffect(RichEditText.BOLD, ischeck);
                break;
            case R.id.italic:
                tv.applyEffect(RichEditText.ITALIC,ischeck);
                break;
            case R.id.underline:
                tv.applyEffect(RichEditText.UNDERLINE,ischeck);
                break;
            case R.id.strikeThru:
                tv.applyEffect(RichEditText.STRIKETHROUGH, ischeck);
                break;
            case R.id.header1:
                break;
            case R.id.header2:
                break;
            case R.id.header3:
                break;
            case R.id.quote:
                break;
            case R.id.code:
                break;
            case R.id.unorderedlist:
                tv.applyEffect(RichEditText.BULLET,ischeck);
                break;
            case R.id.orderedList:
                break;
            case R.id.link:
                break;
            case R.id.horizontalRule:
                break;
            default:

                break;
        }
    }
}
