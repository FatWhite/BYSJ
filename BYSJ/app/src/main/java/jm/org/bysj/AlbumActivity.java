package jm.org.bysj;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jm.org.bysj.Entity.AlbumEntity;
import jm.org.bysj.Entity.ImageTextTagsEntity;
import jm.org.bysj.adapter.AlbumAdapter;
import jm.org.bysj.db.DbSession;
import jm.org.bysj.db.ImageLogsModels;
import jm.org.bysj.db.LogsDetailsModels;
import jm.org.bysj.util.ImageUtils;
import jm.org.bysj.view.DetailDialog;
import jm.org.bysj.view.PinchImageView;

/**
 * 日记列表也
 */
public class AlbumActivity extends Activity implements AlbumAdapter.AlbumAdapterOnclick{

    private RecyclerView recyclerView;
    private PinchImageView imageView;
    private LinearLayout linearLayout;
    private AlbumAdapter albumAdapter;
    private List<AlbumEntity> albumEntityList=new ArrayList<>();
    private TextView tvTitle;
    private ImageView ivBack;
    private DetailDialog detailDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initView();
    }

    public void initView(){
        detailDialog=new DetailDialog(AlbumActivity.this,R.style.Translucent_Dialog);
        recyclerView=findViewById(R.id.rv_image);
        imageView=findViewById(R.id.iv_show_img);
        linearLayout=findViewById(R.id.ll_tag);
        tvTitle=findViewById(R.id.tv_title);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("查看日记");
        albumAdapter=new AlbumAdapter(this,albumEntityList,this);
        recyclerView.setAdapter(albumAdapter);
        imageView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        albumAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
    public void initData(){
        List<String> imagePathList= ImageUtils.getImagePathFromSD();
        for (String path:imagePathList){
            ImageLogsModels imageLogsModels = DbSession.queryLogsByName(path.replace(ImageUtils.getSDPath()+ File.separator,""));
            if (imageLogsModels!=null){
                AlbumEntity entity=new AlbumEntity();
                entity.setName(path);
                try {
                    JSONArray array=new JSONArray(imageLogsModels.getLogsJson());
                    List<ImageTextTagsEntity> imageTextTagsEntityList=new ArrayList<>();
                    for (int  i=0;i<array.length();i++){
                        String tagName=array.optJSONObject(i).optString("imageLog");
                        LogsDetailsModels detailsModels = DbSession.queryLogsDetail(path.replace(ImageUtils.getSDPath()+ File.separator,""), tagName);
                        ImageTextTagsEntity imageTextTagsEntity=new ImageTextTagsEntity();
                        imageTextTagsEntity.setTag(tagName);
                        imageTextTagsEntity.setDetail(detailsModels.getTagDetail());
                        imageTextTagsEntityList.add(imageTextTagsEntity);
                    }
                    entity.setImageTextTagsEntityList(imageTextTagsEntityList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                albumEntityList.add(entity);
            }
        }
    }

    @Override
    public void onClick(AlbumEntity entity) {
        linearLayout.removeAllViews();
        imageView.setVisibility(View.VISIBLE);
        Glide.with(this).load(entity.getName()).into(imageView);
        List<ImageTextTagsEntity> imageTextTagsEntityList = entity.getImageTextTagsEntityList();
        for (ImageTextTagsEntity entity1:imageTextTagsEntityList){
            TextView textView=new TextView(this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin=10;
            layoutParams.rightMargin=10;
            layoutParams.bottomMargin=10;
            textView.setPadding(20,10,20,10);
            textView.setTextColor(AlbumActivity.this.getResources().getColor(R.color.btn_text));
            textView.setTextSize(20);
            textView.setBackgroundResource(R.color.btn_bg2);
            textView.setText(entity1.getTag());
            textView.setTag(entity1.getDetail());
            textView.setOnClickListener(onClickListener);
            linearLayout.addView(textView,layoutParams);
        }
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String detail= (String) v.getTag();
            if (detailDialog!=null){
                detailDialog.showDetail(detail);
            }
        }
    };
}
