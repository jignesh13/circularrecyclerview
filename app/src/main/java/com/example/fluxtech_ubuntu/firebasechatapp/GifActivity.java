package com.example.fluxtech_ubuntu.firebasechatapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class GifActivity extends AppCompatActivity {
    private ImageView img;
    private OkHttpClient mOkHttpClient;
    private final static String DOWNLOAD_URL = "http://snapaddapp.returnzerollc.netdna-cdn.com/profiles/user_2067989_thumbnail_5adae805e4c64_1524295685.jpg";
    private SubscriptionManager mSubscriptionManager;
    private List<SubscriptionInfo> subInfoList;
    ArrayList<String> Numbers;
    private boolean isMultiSimEnabled;
    private ArrayList<ImageModel> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        try {
            JSONObject jsonObject=new JSONObject(readJsonFile(this,"kickgirls1.json"));
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            data.add(null);
            data.add(null);
            data.add(null);
            ArrayList<ImageModel> imageModels=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                ImageModel imageModel=new ImageModel(jsonObject1.getString("display_name"),jsonObject1.getString("thumbnail_url"));
                imageModels.add(imageModel);
            }
            data.addAll(imageModels);
            data.addAll(imageModels);
            data.addAll(imageModels);
            data.addAll(imageModels);
            data.addAll(imageModels);




        } catch (JSONException e) {
            e.printStackTrace();
        }




        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MyAdapter());
        String uniqueID = UUID.randomUUID().toString();
        Log.e("uuid",uniqueID);
    }
    public static String readJsonFile(Context context,String filename)
    {
        StringBuilder buf=new StringBuilder();
        try {

            InputStream json=context.getAssets().open(filename);
            BufferedReader in=
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str=in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        }
        catch (IOException e)
        {

        }
        finally {

            return buf.toString();
        }


    }
    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private Set<Integer> animatedPosition = new HashSet();
        @Override
        public int getItemViewType(int position)
        {
            if (position==0||position==2)
            {
                return 1;

            }
            else if (position==1)
            {
                return 2;
            }
            else
            {
                return 3;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("call","call");
            if (viewType==1)
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emptypeopleview, null);
                EmptyViewHolder rcv = new EmptyViewHolder(layoutView);
                return rcv;
            }
            else if (viewType==2)
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_head, null);
                AvatarViewHolder rcv = new AvatarViewHolder(layoutView);
                return rcv;
            }
            else
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userviewholder, null);
                PeopleViewHolder rcv = new PeopleViewHolder(layoutView);
                return rcv;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof PeopleViewHolder)
            {
                if ((position+1)%3==0)
                {
                    PeopleViewHolder peopleViewHolder= (PeopleViewHolder) holder;
                    peopleViewHolder.username.setText(data.get(position).getName());
                    Glide.with(GifActivity.this).load(data.get(position).getProfilepic()).apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.drawable.userplaceholder)).into(((PeopleViewHolder) holder).profileimageView);
                }
                else
                {

                    PeopleViewHolder peopleViewHolder= (PeopleViewHolder) holder;
                    peopleViewHolder.username.setText(data.get(position).getName());
                    Glide.with(GifActivity.this).load(data.get(position).getProfilepic()).apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.drawable.userplaceholder)).into(((PeopleViewHolder) holder).profileimageView);

                }


            }
            setAnimation(holder,position);
        }
        protected void setAnimation(RecyclerView.ViewHolder holder, int position) {
            if (this.animatedPosition.contains(Integer.valueOf(position))) {
                holder.itemView.clearAnimation();
                return;
            }
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_slide_from_bottom));
            this.animatedPosition.add(Integer.valueOf(position));
        }
        @Override
        public int getItemCount() {
            return data.size();
        }
        class EmptyViewHolder extends RecyclerView.ViewHolder
        {

            public EmptyViewHolder(View itemView) {
                super(itemView);
            }
        }

        class PeopleViewHolder extends RecyclerView.ViewHolder
        {
            public TextView username;
            public ImageView profileimageView;

            public PeopleViewHolder(View itemView) {
                super(itemView);

                username=itemView.findViewById(R.id.user);
                profileimageView=itemView.findViewById(R.id.profile);
                profileimageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Toast.makeText(GifActivity.this, ""+data.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                profileimageView.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                ImageView view = (ImageView) v;
                                //overlay is black with transparency of 0x77 (119)
                                view.getDrawable().setColorFilter(0x77FF4081,PorterDuff.Mode.SRC_ATOP);
                                view.invalidate();
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL: {
                                ImageView view = (ImageView) v;
                                //clear the overlay
                                view.getDrawable().clearColorFilter();
                                view.invalidate();
                                break;
                            }
                        }

                        return false;
                    }
                });

            }
        }
        class AvatarViewHolder extends RecyclerView.ViewHolder
        {
            public AvatarViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

}

