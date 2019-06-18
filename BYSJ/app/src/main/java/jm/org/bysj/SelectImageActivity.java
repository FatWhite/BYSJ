package jm.org.bysj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jm.org.bysj.Entity.ImageLogs;
import jm.org.bysj.db.DbSession;
import jm.org.bysj.db.ImageLogsModels;
import jm.org.bysj.db.LogsDetailsModels;
import jm.org.bysj.util.FileUtil;
import jm.org.bysj.util.ImageUtils;
import jm.org.bysj.view.ILabelView;
import jm.org.bysj.view.InputDialog;

/**
 * 选择图片页
 */
public class SelectImageActivity extends Activity implements View.OnClickListener,InputDialog.InputDialogListener{

    public static final int RC_CHOOSE_PHOTO = 2;
    public static final String TAG=SelectImageActivity.class.getSimpleName();

    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private Button btnSelect;
    private Button btnAdd;
    private Button btnSave;
    private InputDialog inputDialog;
    private TextView tvTitle;
    private ImageView ivBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        initView();
    }

    private void initView(){
        inputDialog=new InputDialog(SelectImageActivity.this,R.style.Translucent_Dialog);
        relativeLayout=findViewById(R.id.rl_view);
        imageView=findViewById(R.id.iv_select_image);
        btnSelect=findViewById(R.id.btn_select);
        btnAdd=findViewById(R.id.btn_add_tag);
        btnSave=findViewById(R.id.btn_save_tag);
        tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("选择图片");
        ivBack=findViewById(R.id.iv_back);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        inputDialog.setInputDialogListener(this);
    }

    private void selectPhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_CHOOSE_PHOTO:
                if (data!=null){
                    Uri uri = data.getData();
                    String filePath = FileUtil.getFilePathByUri(this, uri);
                    if (!TextUtils.isEmpty(filePath)) {
                        Glide.with(this).load(filePath).into(imageView);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select:
                selectPhoto();
                break;
            case R.id.btn_add_tag:
                if (!inputDialog.isShowing()){
                    inputDialog.show();
                }
                break;
            case R.id.btn_save_tag:
                saveImageAndDB();
                Toast.makeText(SelectImageActivity.this,"记录成功",Toast.LENGTH_SHORT).show();
                removeTags();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void saveImageAndDB(){
        String name=System.currentTimeMillis()+"";
        ImageUtils.saveImage(SelectImageActivity.this,relativeLayout,name);
        List<ImageLogs> imageLogsList=new ArrayList<>();
        for (int i=0;i<relativeLayout.getChildCount();i++){
            if (relativeLayout.getChildAt(i) instanceof ILabelView){
                ImageLogs logs=new ImageLogs();
                logs.setImageLog(((ILabelView) relativeLayout.getChildAt(i)).getLogName());
                imageLogsList.add(logs);

                LogsDetailsModels detailsModels=new LogsDetailsModels();
                detailsModels.setImageName(name+"."+ImageUtils.IMAGE_PNG);
                detailsModels.setTagName(((ILabelView) relativeLayout.getChildAt(i)).getLogName());
                detailsModels.setTagDetail(((ILabelView) relativeLayout.getChildAt(i)).getLogDetal());
                DbSession.insertImageTextDetail(detailsModels);
            }
        }
        String jsonLogNames=new Gson().toJson(imageLogsList);
        Log.e(TAG,jsonLogNames);
        ImageLogsModels imageLogsModels=new ImageLogsModels();
        imageLogsModels.setName(name+"."+ImageUtils.IMAGE_PNG);
        imageLogsModels.setLogsJson(jsonLogNames);
        DbSession.insertImageTextLogs(imageLogsModels);
        Glide.with(this).load(ImageUtils.getSDPath()+ File.separator+name+"."+ImageUtils.IMAGE_PNG).into(imageView);
    }
    private void removeTags(){
        for (int i=0;i<relativeLayout.getChildCount();i++){
            if (relativeLayout.getChildAt(i) instanceof ILabelView){
                relativeLayout.removeViewAt(i);
            }
        }
    }

    @Override
    public void returnLogs(String name, String detail) {
        ILabelView labelView=new ILabelView(SelectImageActivity.this);
        labelView.setLogName(name);
        labelView.setLogDetal(detail);
        labelView.draw(relativeLayout,500,500);
    }
}
