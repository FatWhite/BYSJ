package jm.org.bysj.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class PermissionsChecker {

    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    // 判断权限集合--查看是否有缺少的权限
    public boolean lacksPermissions(ArrayList<String> permissions) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){//android 6.0之后采用动态授权
            return false;
        }
        for (String permission : permissions) {//缺少权限
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断权限是否缺少权限
     * **/
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    /**
     * 启动检测权限的页面
     * 说明：
     *      a、在Activity中能采用这种方式去启动请求权限的Activity
     *      b、在Fragment中无法采用这种方式去启动因为 在启动Activity的时候
     *          如果采用getActivity().startActivityForResult的方式是无法在
     *          Fragment的onActivity中收到结果的
     *
     * **/
    public void startCheckActivity(Activity activity, ArrayList<String> permissions, int requestCode){
        Intent intent = new Intent(activity, PermissionsActivity.class);
        intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, permissions);
        activity.startActivityForResult(intent, requestCode);
    }
}
