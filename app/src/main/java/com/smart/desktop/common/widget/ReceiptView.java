package com.smart.desktop.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smart.desktop.R;

/**
 * 自定义收款界面
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月21日
 */
public class ReceiptView extends View {
    private static final String TAG = ReceiptView.class.getSimpleName();

    private Paint mPaint;    //绘制圆
    private Paint mArcPaint; //绘制圆环
    private Paint mTextPaint;//绘制文字
    private int mCenterX; //中心坐标X
    private int mCenterY; //中心坐标Y

    private float mRadius;  //圆的半径
    private int mRecColor;  //图形颜色
    private float mRecPadding;
    private float mRecAdjustment;
    private float mArcWidth;   //圆环宽度
    private float mGapWidth;  //间隙宽度
    private int mGapColor;    //间隙颜色
    private int mPressedColor;  //按下颜色

    private float mCenterTextSize;//圆中的字体大小
    private float mRectTextSize;  //矩形中的字体大小
    private String mCenterText;   //圆中的内容
    private String mTLText;
    private String mTRText;
    private String mBLText;
    private String mBRText;
    private int mTextColor;       //字体颜色

    private boolean mIsCPressed = false;
    private boolean mIsTLPressed = false;
    private boolean mIsTRPressed = false;
    private boolean mIsBLPressed = false;
    private boolean mIsBRPressed = false;

    private RectF mRectF_TL;
    private RectF mRectF_TR;
    private RectF mRectF_BL;
    private RectF mRectF_BR;
    private OnRectClickListener mClickListener;
    private int mArcAlpha = 100;

    private float mLastX;
    private float mLastY;

    public ReceiptView(Context context) {
        this(context, null);
    }

    public ReceiptView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReceiptView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(context, attrs, defStyleAttr);
        initViews();
    }

    /**
     * 初始化相应属性
     */
    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ReceiptView, defStyleAttr, 0);
        if (ta != null){
            mRadius = ta.getDimension(R.styleable.ReceiptView_rec_radius, 50);
            mRecPadding = ta.getDimension(R.styleable.ReceiptView_rec_padding, 0);
            mRecAdjustment = ta.getDimension(R.styleable.ReceiptView_rec_adjustment, 0);
            mArcWidth = ta.getDimension(R.styleable.ReceiptView_rec_arc_width, 0);
            mRecColor = ta.getColor(R.styleable.ReceiptView_rec_color, 0);
            mCenterText = ta.getString(R.styleable.ReceiptView_rec_center_text);
            mTLText = ta.getString(R.styleable.ReceiptView_rec_tl_text);
            mTRText = ta.getString(R.styleable.ReceiptView_rec_tr_text);
            mBLText = ta.getString(R.styleable.ReceiptView_rec_bl_text);
            mBRText = ta.getString(R.styleable.ReceiptView_rec_br_text);
            mCenterTextSize = ta.getDimension(R.styleable.ReceiptView_rec_center_text_size, 12);
            mRectTextSize = ta.getDimension(R.styleable.ReceiptView_rec_rect_text_size, 12);
            mTextColor = ta.getColor(R.styleable.ReceiptView_rec_text_color, 0);
            mGapWidth = ta.getDimension(R.styleable.ReceiptView_rec_gap_width, 5);
            mGapColor = ta.getColor(R.styleable.ReceiptView_rec_gap_color, 0);
            mPressedColor = ta.getColor(R.styleable.ReceiptView_rec_press_color, 0);
            ta.recycle();
        }
    }

    private void initViews() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(mRecColor);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCenterX = getWidth() / 2;  //中心点X
        mCenterY = getHeight() / 2;  //中心点Y
        //绘制矩形 X4
        for (int i = 1; i <= 4; i++){
            switch (i){
                case 1:
                    mPaint.setColor(mIsTLPressed ? mPressedColor : mRecColor);
                    mRectF_TL = new RectF(mRecPadding, mRecPadding, mCenterX - (mGapWidth / 2), mCenterY - (mGapWidth / 2));
                    canvas.drawRect(mRectF_TL, mPaint);
                    //绘制左上矩形内容
                    if (!TextUtils.isEmpty(mTLText)){
                        mTextPaint.setTextSize(mRectTextSize);
                        mTextPaint.setTypeface(Typeface.DEFAULT);
                        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
                        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
                        int baseLineY = (int) (mCenterY - top/2 - bottom/2);//基线中间点的y轴计算公式
                        canvas.drawText(mTLText, 0, mTLText.length(), mCenterX - mRadius - mRecAdjustment, baseLineY - mRadius - mRecAdjustment, mTextPaint);
                    }
                    break;
                case 2:
                    mPaint.setColor(mIsTRPressed ? mPressedColor : mRecColor);
                    mRectF_TR = new RectF(mCenterX + (mGapWidth / 2), mRecPadding, mCenterX * 2 - mRecPadding, mCenterY - (mGapWidth / 2));
                    canvas.drawRect(mRectF_TR, mPaint);
                    //绘制右上矩形内容
                    if (!TextUtils.isEmpty(mTRText)){
                        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
                        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
                        int baseLineY = (int) (mCenterY - top/2 - bottom/2);//基线中间点的y轴计算公式
                        canvas.drawText(mTRText, 0, mTRText.length(), mCenterX + mRadius + mRecAdjustment, baseLineY - mRadius - mRecAdjustment, mTextPaint);
                    }
                    break;
                case 3:
                    mPaint.setColor(mIsBLPressed ? mPressedColor : mRecColor);
                    mRectF_BL = new RectF(mRecPadding, mCenterY + (mGapWidth / 2), mCenterX - (mGapWidth / 2), mCenterY * 2 - mRecPadding);
                    canvas.drawRect(mRectF_BL, mPaint);
                    //绘制左下矩形内容
                    if (!TextUtils.isEmpty(mBLText)){
                        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
                        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
                        int baseLineY = (int) (mCenterY - top/2 - bottom/2);//基线中间点的y轴计算公式
                        canvas.drawText(mBLText, 0, mBLText.length(), mCenterX - mRadius - mRecAdjustment, baseLineY + mRadius + mRecAdjustment, mTextPaint);
                    }
                    break;
                case 4:
                    mPaint.setColor(mIsBRPressed ? mPressedColor : mRecColor);
                    mRectF_BR = new RectF(mCenterX + (mGapWidth / 2), mCenterY + (mGapWidth / 2), mCenterX * 2 - mRecPadding, mCenterY * 2 - mRecPadding);
                    canvas.drawRect(mRectF_BR, mPaint);
                    //绘制右下矩形内容
                    if (!TextUtils.isEmpty(mBRText)){
                        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
                        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
                        int baseLineY = (int) (mCenterY - top/2 - bottom/2);//基线中间点的y轴计算公式
                        canvas.drawText(mBRText, 0, mBRText.length(), mCenterX + mRadius + mRecAdjustment, baseLineY + mRadius + mRecAdjustment, mTextPaint);
                    }
                    break;
                default:
                    break;
            }
        }
        //绘制外圆
        mPaint.setColor(mGapColor);
        canvas.drawCircle(mCenterX, mCenterY, mRadius + mGapWidth, mPaint);
        //绘制动态圆环
        if (!mIsCPressed){
            //绘制圆环
            RectF rectF = new RectF(mCenterX - mRadius + 5, mCenterY - mRadius + 5, mCenterX + mRadius - 5, mCenterY + mRadius - 5);
            mArcPaint.setAlpha(mArcAlpha);
            canvas.drawArc(rectF, 0, 360, false, mArcPaint);
        }
        //绘制内圆
        mPaint.setColor(mIsCPressed ? mPressedColor : mRecColor);
        canvas.drawCircle(mCenterX, mCenterY, mRadius - mArcWidth - 2, mPaint);
        //绘制文字
        mTextPaint.setTextSize(mCenterTextSize);
        mTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/Helvetica Condensed Bold.ttf"));
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (mCenterY - top/2 - bottom/2);//基线中间点的y轴计算公式
        canvas.drawText(mCenterText, 0, mCenterText.length(), mCenterX, baseLineY, mTextPaint);
        //让圆环动起来
        if (!mIsCPressed){
            mArcAlpha += 10;
            if (mArcAlpha >= 200) mArcAlpha = 70;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理圆圈的点击事件
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                //判断所点击的区域
                if (isPointInCircle(mLastX, mLastY, mRadius)){
                    mIsCPressed = true;
                    invalidate();
                    return true;
                }
                //判断所点击的区域不在外圆内
                if (!isPointInCircle(mLastX, mLastY, mRadius + mGapWidth)){
                    //再分别判断落在哪个矩形内
                    if (mRectF_TL.contains(mLastX, mLastY)){
                        mIsTLPressed = true;
                        invalidate();
                        return true;
                    }
                    if (mRectF_TR.contains(mLastX, mLastY)){
                        mIsTRPressed = true;
                        invalidate();
                        return true;
                    }
                    if (mRectF_BL.contains(mLastX, mLastY)){
                        mIsBLPressed = true;
                        invalidate();
                        return true;
                    }
                    if (mRectF_BR.contains(mLastX, mLastY)){
                        mIsBRPressed = true;
                        invalidate();
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastX = event.getX();
                mLastY = event.getY();
                //继续判断是否在所点击的区域内
                if (mIsCPressed && isPointInCircle(mLastX, mLastY, mRadius)){
                    mIsCPressed = false;
                    if (mClickListener != null){
                        mClickListener.onClick(RecClick.CENTER);
                    }
                    invalidate();
                    return true;
                }
                //继续判断所点击的区域不在外圆内
                if (!isPointInCircle(mLastX, mLastY, mRadius + mGapWidth)){
                    //再分别判断落在哪个矩形内
                    if (mIsTLPressed && mRectF_TL.contains(mLastX, mLastY)){
                        mIsTLPressed = false;
                        if (mClickListener != null){
                            mClickListener.onClick(RecClick.TOP_LEFT);
                        }
                        invalidate();
                        return true;
                    }
                    if (mIsTRPressed && mRectF_TR.contains(mLastX, mLastY)){
                        mIsTRPressed = false;
                        if (mClickListener != null){
                            mClickListener.onClick(RecClick.TOP_RIGHT);
                        }
                        invalidate();
                        return true;
                    }
                    if (mIsBLPressed && mRectF_BL.contains(mLastX, mLastY)){
                        mIsBLPressed = false;
                        if (mClickListener != null){
                            mClickListener.onClick(RecClick.BOTTOM_LEFT);
                        }
                        invalidate();
                        return true;
                    }
                    if (mIsBRPressed && mRectF_BR.contains(mLastX, mLastY)){
                        mIsBRPressed = false;
                        if (mClickListener != null){
                            mClickListener.onClick(RecClick.BOTTOM_RIGHT);
                        }
                        invalidate();
                        return true;
                    }
                }
                resetPressedFlag();
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 点击是否落在圆内
     *
     * @param lastX
     * @param lastY
     * @return
     */
    private boolean isPointInCircle(float lastX, float lastY, float radius){
        //点击位置x坐标与圆心的x坐标的距离
        float distanceX = Math.abs(mCenterX - lastX);
        //点击位置y坐标与圆心的y坐标的距离
        float distanceY = Math.abs(mCenterY - lastY);
        //点击位置与圆心的直线距离
        float distanceZ = (float) Math.sqrt(Math.pow(distanceX,2) + Math.pow(distanceY,2));
        return distanceZ <= radius;
    }

    /**
     * 重置点击标识
     */
    private void resetPressedFlag(){
        mIsCPressed = false;
        mIsTLPressed = false;
        mIsTRPressed = false;
        mIsBLPressed = false;
        mIsBRPressed = false;
    }

    /**
     * 设置监听事件
     *
     * @param clickListener
     */
    public void setClickListener(OnRectClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    /**
     * 点击事件枚举
     */
    public enum RecClick{
        CENTER,TOP_LEFT,TOP_RIGHT,BOTTOM_LEFT,BOTTOM_RIGHT
    }

    /**
     * 点击事件回调接口
     */
    public interface OnRectClickListener{
        void onClick(RecClick click);
    }
}
