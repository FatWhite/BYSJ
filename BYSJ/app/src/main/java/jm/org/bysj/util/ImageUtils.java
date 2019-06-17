package jm.org.bysj.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    private static final String IMAGE_JPG = "jpg";
    private static final String IMAGE_PNG = "png";

    public static void saveImage(Context context,View view,String name){
        Bitmap bitmap=convertViewToBitmap(view);
        String savePath=getBaseFilePath(context);
        saveBitmap(bitmap,savePath,100,IMAGE_PNG,name);
    }
    /**
     * 获取布局截图
     * @param view
     * @return
     */
    private static Bitmap convertViewToBitmap(View view){
        Bitmap shareBitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(shareBitmap);
        view.draw(c);

        return shareBitmap;
    }

    /**
     * 获取保存路径
     * @param context
     * @return
     */
    private static String getBaseFilePath(Context context) {
        if (checkSDCard())
            return Environment.getExternalStorageDirectory().getPath()//获取SD卡--不会随着用户卸载App而删除
                    + File.separator + "ImageTextLog/Logs";//需要申请写入权限 但是android6.0在写入私有目录和扩展目录的时候需要不需要申请写入权限
        else
            return context.getCacheDir().getAbsolutePath();//获取App的私有存储--App的安装目录其他应用无法查看
    }

    /**
     * 检测是否有sd卡
     * @return
     */
    private static boolean checkSDCard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    /**
     * @param path 文件存放路径
     *@param ext 文件扩展名
     *@param qulity 文件压缩比例
     * **/
    private static String saveBitmap(Bitmap bmp, String path,int qulity, String ext,String name) {
        String picName;
        File clipDir = new File(path);
        if (!clipDir.exists()) {
            clipDir.mkdirs();
        }
        if (TextUtils.isEmpty(ext)) {
            picName = name + ".jpg";
        } else {
            picName = name + "." + ext;

        }
        File f = new File(clipDir.getPath(), picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.JPEG, qulity, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return f.getPath();
    }
}
