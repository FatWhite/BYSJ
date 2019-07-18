package jm.org.bysj.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import jm.org.bysj.R;

/**
 * Created by FatWhite on 2015/11/25.
 */
public class ILabelView extends RelativeLayout {
    private static final int ANIMATIONEACHOFFSET = 600;
    //圆点中心点
    private int mTop;
    private int mLeft;
    //计算在父布局位置参数
    private int _xDelta;
    private int _yDelta;
    private ImageView labelIcon;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private int type = 1;
    private int parentWidth = 0;
    int iconWidth = 0;
    int iconHeight = 0;
    private ArrayList<TextView> aList = new ArrayList<TextView>();
    private String logName;
    private String logDetal;

    public ILabelView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.label_view, this);
        labelIcon =  findViewById(R.id.label_icon);
        text1 =  findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 =  findViewById(R.id.text6);
        aList.add(text1);
        aList.add(text2);
        aList.add(text3);
        aList.add(text4);
        aList.add(text5);
        aList.add(text6);
        updateVisibility();
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        labelIcon.measure(w, h);
        iconWidth = labelIcon.getMeasuredWidth();
        iconHeight = labelIcon.getMeasuredHeight();
        labelIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                type = type == 6 ? 1 : ++type;
                updateVisibility();
                updateLocation(mLeft, mTop);
            }
        });
    }

    public ILabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.label_view, this);
        labelIcon = findViewById(R.id.label_icon);
        text1 = findViewById(R.id.text1);
        text2 =  findViewById(R.id.text2);
    }
    //初次调用画布局，啦啦啦啦
    public void draw(ViewGroup parent, int mLeft, int mTop) {
        this.parentWidth = parent.getWidth();
        this.mLeft = mLeft;
        this.mTop = mTop;
        updateLocation(mLeft, mTop);
        parent.addView(this);

    }

    //点击的时候更换label背景
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateLocation(int mLeft, int mTop) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        LayoutParams iconparams = (LayoutParams) labelIcon.getLayoutParams();
        switch (type) {
            case 1:
                LayoutParams p1 = (LayoutParams) text1.getLayoutParams();
                p1.setMargins(0, 0, iconWidth / 2, iconHeight / 2);
                text1.setLayoutParams(p1);
                text1.measure(w, h);
                params.width = text1.getMeasuredWidth() + iconWidth / 2;
                params.height = text1.getMeasuredHeight() + iconHeight / 2;
                iconparams.removeRule(RelativeLayout.CENTER_VERTICAL);
                iconparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                iconparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMargins(mLeft - text1.getMeasuredWidth(), mTop - text1.getMeasuredHeight(), 0, 0);
                break;
            case 2:
                LayoutParams p2 = (LayoutParams) text2.getLayoutParams();
                p2.setMargins(iconWidth / 2, 0, 0, iconHeight / 2);
                text2.setLayoutParams(p2);
                text2.measure(w, h);
                params.width = text2.getMeasuredWidth() + iconWidth / 2;
                params.height = text2.getMeasuredHeight() + iconHeight / 2;
                iconparams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                iconparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.setMargins(mLeft - iconWidth / 2, mTop - text2.getMeasuredHeight(), 0, 0);
                break;
            case 3:
                LayoutParams p3 = (LayoutParams) text3.getLayoutParams();
                p3.setMargins(iconWidth / 2, 0, 0, 0);
                text3.setLayoutParams(p3);
                text3.measure(w, h);
                params.width = text3.getMeasuredWidth() + iconWidth / 2;
                params.height = text3.getMeasuredHeight();
                iconparams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                iconparams.addRule(RelativeLayout.CENTER_VERTICAL);
                params.setMargins(mLeft - iconWidth / 2, mTop - text3.getMeasuredHeight() / 2, 0, 0);
                break;
            case 4:
                LayoutParams p4 = (LayoutParams) text4.getLayoutParams();
                p4.setMargins(iconWidth / 2, iconHeight / 2, 0, 0);
                text4.setLayoutParams(p4);
                text4.measure(w, h);
                params.width = text4.getMeasuredWidth() + iconWidth / 2;
                params.height = text4.getMeasuredHeight() + iconHeight / 2;
                iconparams.removeRule(RelativeLayout.CENTER_VERTICAL);
                iconparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.setMargins(mLeft - iconWidth / 2, mTop - iconHeight / 2, 0, 0);
                break;
            case 5:
                LayoutParams p5 = (LayoutParams) text5.getLayoutParams();
                p5.setMargins(0, iconHeight / 2, iconWidth / 2, 0);
                text5.setLayoutParams(p5);
                text5.measure(w, h);
                params.width = text5.getMeasuredWidth() + iconWidth / 2;
                params.height = text5.getMeasuredHeight() + iconHeight / 2;
                iconparams.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
                iconparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.setMargins(mLeft - text5.getMeasuredWidth(), mTop - iconHeight / 2, 0, 0);
                break;
            case 6:
                LayoutParams p6 = (LayoutParams) text6.getLayoutParams();
                p6.setMargins(0, 0, iconWidth / 2, 0);
                text6.setLayoutParams(p6);
                text6.measure(w, h);
                params.width = text6.getMeasuredWidth() + iconWidth / 2;
                params.height = text6.getMeasuredHeight();
                iconparams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                iconparams.addRule(RelativeLayout.CENTER_VERTICAL);
                params.setMargins(mLeft - text6.getMeasuredWidth(), mTop - text6.getMeasuredHeight() / 2, 0, 0);
                break;
        }
        setLayoutParams(params);
        wave();
    }

    //小圆点动画，可以不加哦
    public void wave() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, ScaleAnimation.RELATIVE_TO_SELF,
                0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIMATIONEACHOFFSET * 3);
        sa.setRepeatCount(10);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(10);// 设置循环
        as.setDuration(ANIMATIONEACHOFFSET * 3);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        labelIcon.startAnimation(as);
    }

    //更新Visibility
    private void updateVisibility() {
        for (int i = 0; i < aList.size(); i++) {
            if (i + 1 == type) {
                aList.get(i).setVisibility(View.VISIBLE);
            } else {
                aList.get(i).setVisibility(View.GONE);
            }
        }
    }

    //移动的时候更新小小圆点中心哦
    public void updateMarginValue(int left, int top) {
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        switch (type) {
            case 1:
                text1.measure(w, h);
                mLeft=left+text1.getMeasuredWidth();
                mTop=top+text1.getMeasuredHeight();
                break;
            case 2:
                text2.measure(w, h);
                mLeft=left+iconWidth / 2;
                mTop=top+text2.getMeasuredHeight();
                break;
            case 3:
                text3.measure(w, h);
                mLeft=left+iconWidth / 2;
                mTop=top+text3.getMeasuredHeight() / 2;
                break;
            case 4:
                text4.measure(w, h);
                mLeft=left+iconWidth / 2;
                mTop=top+iconHeight / 2;
                break;
            case 5:
                text5.measure(w, h);
                mLeft=left+text5.getMeasuredWidth();
                mTop=top+iconHeight / 2;
                break;
            case 6:
                text6.measure(w, h);
                mLeft=left+text6.getMeasuredWidth();
                mTop=top+text6.getMeasuredHeight()/2;
                break;

        }

    }
    long times=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                times=System.currentTimeMillis();
                LayoutParams lParams = (LayoutParams) this
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                if (System.currentTimeMillis() -times >2*1000){
                    if (null!=lableOnLongClockListener){
                        lableOnLongClockListener.longClick(ILabelView.this);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                LayoutParams layoutParams = (LayoutParams) this
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                setLayoutParams(layoutParams);
                updateMarginValue(X - _xDelta,Y - _yDelta);
                if (Math.abs(X - _xDelta)>20&&Math.abs(Y - _yDelta)>20){
                    times=System.currentTimeMillis();
                }
                break;
        }
//        _root.invalidate();
        return true;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
        text1.setText(logName);
        text2.setText(logName);
        text3.setText(logName);
        text4.setText(logName);
        text5.setText(logName);
        text6.setText(logName);
    }

    public String getLogDetal() {
        return logDetal;
    }

    public void setLogDetal(String logDetal) {
        this.logDetal = logDetal;
    }
    private LableOnLongClockListener lableOnLongClockListener;

    public void setLableOnLongClockListener(LableOnLongClockListener lableOnLongClockListener) {
        this.lableOnLongClockListener = lableOnLongClockListener;
    }

    public interface LableOnLongClockListener{
        void longClick(View v);
    }
}