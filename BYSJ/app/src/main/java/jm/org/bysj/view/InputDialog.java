package jm.org.bysj.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jm.org.bysj.R;

public class InputDialog extends Dialog implements View.OnClickListener{

    private Context mContext;
    private EditText etName;
    private EditText etDetail;
    private Button btnCancel;
    private Button btnOk;
    private InputDialogListener inputDialogListener;

    private void init(){
        setContentView(R.layout.input_logs_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        getWindow().setAttributes(params);
        etName=findViewById(R.id.et_name);
        etDetail=findViewById(R.id.et_detail);
        btnCancel=findViewById(R.id.btn_cancel);
        btnOk=findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }


    public InputDialog(@NonNull Context context) {
        super(context);
        mContext=context;
        init();
    }

    public InputDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext=context;
        init();
    }

    protected InputDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext=context;
        init();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btn_cancel:
              dismiss();
              break;
          case R.id.btn_ok:
              if (TextUtils.isEmpty(etName.getText().toString())){
                  Toast.makeText(mContext,"请输入标题",Toast.LENGTH_SHORT).show();
                  return;
              }
              if (TextUtils.isEmpty(etDetail.getText().toString())){
                  Toast.makeText(mContext,"请输入详情",Toast.LENGTH_SHORT).show();
                  return;
              }
              if (etDetail.getText().toString().length()>170){
                  Toast.makeText(mContext,"请输入170字以内",Toast.LENGTH_SHORT).show();
                  return;
              }
              if (inputDialogListener!=null){
                  inputDialogListener.returnLogs(etName.getText().toString(),etDetail.getText().toString());
              }
              etName.setText(null);
              etDetail.setText(null);
              dismiss();
              break;
      }
    }

    public void setInputDialogListener(InputDialogListener inputDialogListener) {
        this.inputDialogListener = inputDialogListener;
    }

    public interface  InputDialogListener{
        void returnLogs(String name,String detail);
    }
}
