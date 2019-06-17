package jm.org.bysj.permission;


public interface CheckPermissionView{

    public static int CHECK_PERMISSION_OK=10;//授权成功
    public static int CHECK_OERMISSION_REFUSE=20;//拒绝

    public void checkPermissionResult();
}
