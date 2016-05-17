package com.durodola.mobile.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainFragment extends Fragment {
    String tagname;
    RecyclerView rv;
    LinearLayoutManager llm;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        new LoadFeed().execute("http://www.cbc.ca/cmlink/rss-topstories");
        return view;
    }

    class LoadFeed extends AsyncTask<String, Void, ArrayList<TopStories>> {

        @Override
        protected ArrayList<TopStories> doInBackground(String... params) {


            ArrayList<TopStories> feeds = new ArrayList<TopStories>();
            XmlPullParserFactory factory = null;
            XmlPullParser parser = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                parser = factory.newPullParser();
                ///
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                InputStream is = conn.getInputStream();
                parser.setInput(new InputStreamReader(is));
                // parser.setInput(new                                               InputStreamReader(getUrlData("http://www.techgig.com/newsfeed")));
                int eventType = parser.getEventType();
                TopStories feed = new TopStories();
                String text = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    tagname = parser.getName();
                    //Log.d(tagname, "tagname");
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagname.equalsIgnoreCase("item")) {
                                feed = new TopStories();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            text = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if (tagname.equalsIgnoreCase("item")) {
                                feeds.add(feed);
                            } else if (tagname.equalsIgnoreCase("title")) {
                                feed.setTitle(text);
                            } else if (tagname.equalsIgnoreCase("description")) {
                                feed.setDescription(text);
                                // Log.d("txt", text);
                            } else if (tagname.equalsIgnoreCase("link")) {
                                feed.setLink(text);
                            }
                            break;
                        default:
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return feeds;
            // return null;
        }

        @Override
        protected void onPostExecute(ArrayList<TopStories> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Log.e("RESULT", " " + result);
            NewsAdapter adapter = new NewsAdapter(getContext(), result);
            rv.setAdapter(adapter);


        }
    }


}
