package jm.org.bysj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import jm.org.bysj.util.FileUtil;
import jm.org.bysj.view.ILabelView;

/**
 * 选择图片页
 */
public class SelectImageActivity extends Activity implements View.OnClickListener{

    public static final int RC_CHOOSE_PHOTO = 2;

    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private Button btnSelect;
    private Button btnAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        initView();
    }

    private void initView(){
        relativeLayout=findViewById(R.id.rl_view);
        imageView=findViewById(R.id.iv_select_image);
        btnSelect=findViewById(R.id.btn_select);
        btnAdd=findViewById(R.id.btn_add_tag);
        btnSelect.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
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
                Uri uri = data.getData();
                String filePath = FileUtil.getFilePathByUri(this, uri);
                if (!TextUtils.isEmpty(filePath)) {
                    //将照片显示在 ivImage上
                    Glide.with(this).load(filePath).into(imageView);
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
                   ILabelView labelView=new ILabelView(SelectImageActivity.this);
                   labelView.draw(relativeLayout,500,500);
                   break;
           }
    }
}
