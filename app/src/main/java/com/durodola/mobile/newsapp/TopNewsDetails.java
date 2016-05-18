package com.durodola.mobile.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class TopNewsDetails extends AbstractFragment {
    private WebView mWebview;

    public TopNewsDetails() {
        // Required empty public constructor
    }

    public static TopNewsDetails newInstance() {
        return new TopNewsDetails();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_news_details2, container, false);
        //  mWebview = new WebView(getContext());

        mWebview = (WebView) view.findViewById(R.id.topnewsdeatilsweb);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("http://www.cbc.ca/news/canada/manitoba/donny-lalonde-golden-boy-panama-papers-1.3584073?cmp=rss");

        return view;

    }


}
