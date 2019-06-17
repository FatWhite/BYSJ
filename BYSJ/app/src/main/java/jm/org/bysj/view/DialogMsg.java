package jm.org.bysj.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import jm.org.bysj.R;


public class DialogMsg {

    public static Dialog buildEnsureDialog(final Context context, String title, String message,
                                           DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage("\n" + message + "\n");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.str_ok, clickListener);
        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }


    public static Dialog buildChooseDialog(final Context context, String title, String message,
                                           DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancleListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage("\n" + message + "\n");
        builder.setPositiveButton(R.string.str_ok, okListener);
        builder.setNegativeButton(R.string.str_cancel, cancleListener);
        return builder.create();
    }

    //断网弹窗
    public static boolean isShow=false;
    public static Dialog buildNoNetDialog(final Context context, String title, String message) {
            isShow=true;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage("\n" + message + "\n");
            builder.setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isShow=false;
                }
            });
            builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isShow=false;
                }
            });
        return builder.create();
    }
}
