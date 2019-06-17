package jm.org.bysj;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import jm.org.bysj.permission.CheckPermissionView;
import jm.org.bysj.permission.PermissionsActivity;
import jm.org.bysj.permission.PermissionsChecker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG=MainActivity.class.getSimpleName();

    private Button btnAlbum;
    private Button btnAdd;
    private static final int PERMISSION_REQUEST_CODE=101;//权限请求码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermission();
    }

    private void initView(){
        btnAlbum =findViewById(R.id.btn_album);
        btnAdd=findViewById(R.id.btn_add);
        btnAlbum.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_album:
               startActivity(new Intent(MainActivity.this,AlbumActivity.class));
               break;
           case R.id.btn_add:
               startActivity(new Intent(MainActivity.this,SelectImageActivity.class));
               break;
       }
    }
    /**
     * 检测权限
     * **/
    private void checkPermission(){
        final ArrayList<String> permissions=new ArrayList<>();//权限列表
        permissions.add(Manifest.permission.CAMERA);//摄像头权限
        permissions.add(Manifest.permission.RECORD_AUDIO);
        permissions.add(Manifest.permission.WAKE_LOCK);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        final PermissionsChecker permissionsChecker=new PermissionsChecker(this);
        if(permissionsChecker.lacksPermissions(permissions)){//启动授权页面
            permissionsChecker.startCheckActivity(MainActivity.this,permissions,PERMISSION_REQUEST_CODE);
        }else{
//            permissionCheckResult(CheckPermissionView.CHECK_PERMISSION_OK);
            Log.e(TAG,"授权已通过");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Log.e(TAG,"授权已通过");
//            permissionCheckResult(CheckPermissionView.CHECK_PERMISSION_OK);
        }else{
//            permissionCheckResult(CheckPermissionView.CHECK_OERMISSION_REFUSE);
            Log.e(TAG,"授权失败");
        }
    }

}
