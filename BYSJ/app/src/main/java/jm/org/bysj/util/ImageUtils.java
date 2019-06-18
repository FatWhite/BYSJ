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
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_PNG = "png";

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
            return  getSDPath();
        else
            return context.getCacheDir().getAbsolutePath();//获取App的私有存储--App的安装目录其他应用无法查看
    }

    /**
     * sd卡路径
     */
    public static String getSDPath(){
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "ImageTextLog/Logs";
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

    /**
     * 从sd卡内获取保存的图片
     * @return
     */
    public static List<String> getImagePathFromSD() {
        List<String> imagePathList = new ArrayList<String>();
        String filePath = getSDPath();
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        return imagePathList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    private static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp") ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

}
