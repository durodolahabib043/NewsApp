package com.durodola.mobile.newsapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mobile on 2016-04-01.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.PersonViewHolder> {

    ArrayList<TopStories> topStoriesArrayList;
    Context context;
    /*  HealthCare contact;
      MapFragment mapFragment;*/
    private static MyItemClickListener mItemClickListener;

    public NewsAdapter(Context context, ArrayList<TopStories> topStoriesArrayList) {
        this.topStoriesArrayList = topStoriesArrayList;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        // contact = HealthCare.getInstance();
        // mapFragment = new MapFragment();
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {

        holder.nametxt_cardview.setText(topStoriesArrayList.get(position).getTitle());
        String html = "<img SRC=\"whatever\">whatever</img>";
        String imgRegex = "<[iI][mM][gG][^>]+[sS][rR][cC]\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";


        final String regex = "(?<=<img src=\")[^\"]*";
        // final Pattern p = Pattern.compile(regex);


        Pattern p = Pattern.compile(imgRegex);
        final Matcher m = p.matcher(topStoriesArrayList.get(position).getDescription());
        //  Matcher m = p.matcher(html);
      /*  while (m.find()) {
            Log.d("image list", m.group());
            holder.lgatxt_cardview.setText(m.group());
            //imgloader.DisplayImage(m.group(),loader, iv);
        }*/
        if (m.find()) {
            String imgSrc = m.group(1);
          //  holder.lgatxt_cardview.setText(imgSrc);
            Picasso.with(this.context).load(imgSrc).into(holder.personPhoto);
        }

      /*  String regularExpression = "src=\"(.*)\" class";
      //  String html = "<description> <![CDATA[ <img width=\"745\" height=\"410\" src=\"http://example.com/image.png\" class=\"attachment-large wp-post-image\" alt=\"alt tag\" style=\"margin-bottom: 15px;\" />description text ]]> </description>"; // Create a Pattern object
        Pattern pattern = Pattern.compile(regularExpression); // Now create matcher object.
        Matcher matcher = pattern.matcher(topStoriesArrayList.get(position).getDescription());
        if (matcher.find( ))

        {
            holder.lgatxt_cardview.setText(""+ matcher.group(1));
            System.out.println("Found value: " + matcher.group(1) );
             Log.e("Found valueqwefrwerw: " , " " + matcher.group(1));
            //It's prints Found value: http://example.com/image.png } } - See more at: http://www.mzan.com/article/35435725-rss-feed-parse-extract-src-image-tag-inside-description-tag-in-java.shtml#sthash.fh5aXRn0.dpuf
        }*/

        //  holder.lgatxt_cardview.setText(topStoriesArrayList.get(position).getLink());
   /*     } else {
            // holder.lgatxt_cardview.setText(contactResultArrayList.get(position).getLga());
            holder.lgatxt_cardview.setText(topStoriesArrayList.get(position).getLga());
        }
        if (topStoriesArrayList.get(position).getName() == null) {
            holder.nametxt_cardview.setText(topStoriesArrayList.get(position).getContractor());
        } else {

            holder.nametxt_cardview.setText(topStoriesArrayList.get(position).getName());
        }*/


    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView rec yclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/

    @Override
    public int getItemCount() {
        return topStoriesArrayList.size();
    }

    public interface MyItemClickListener {
        public void onItemClick(int position, View v);

    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView lgatxt_cardview;
        TextView nametxt_cardview;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            lgatxt_cardview = (TextView) itemView.findViewById(R.id.name_cardview);
            nametxt_cardview = (TextView) itemView.findViewById(R.id.lga_cardview);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getAdapterPosition(), v);

            }
        }
    }

    public void SetOnItemCLickListener(MyItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
