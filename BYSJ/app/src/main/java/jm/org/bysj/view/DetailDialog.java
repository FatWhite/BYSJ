package jm.org.bysj.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import jm.org.bysj.R;

public class DetailDialog extends Dialog implements View.OnClickListener{

    private TextView textView;
    private ImageView imageView;

    private void init(){
        setContentView(R.layout.show_detail_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        getWindow().setAttributes(params);
        textView=findViewById(R.id.tv_dialog_detail);
        imageView=findViewById(R.id.iv_dialog_close);
        imageView.setOnClickListener(this);
    }

    public void showDetail(String detail){
        textView.setText(detail);
        if (!isShowing()){
            show();
        }

    }

    public DetailDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected DetailDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.iv_dialog_close:
              textView.setText(null);
              dismiss();
              break;
      }
    }

}
