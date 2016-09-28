package com.ress.customercoordinatelayout.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.ress.customercoordinatelayout.R;

/**
 * Created by ress on 2016/9/28.
 */
public class CustomerCoordinateLayout extends LinearLayout implements NestedScrollingParent{

    private View mTopView;
    private OverScroller mScroller;
    private int mTopHeight;
    private View mIndicator;
    private ViewPager mVp;

    public CustomerCoordinateLayout(Context context) {
        this(context,null,0);
    }

    public CustomerCoordinateLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomerCoordinateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.iv_top);
        mIndicator = findViewById(R.id.spi);
        View view = findViewById(R.id.vp);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException(
                    "id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mVp = (ViewPager) view;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mVp.getLayoutParams();
        params.height = getMeasuredHeight() - mIndicator.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mTopView.getMeasuredHeight() +mIndicator.getMeasuredHeight() + mVp.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopHeight = mTopView.getMeasuredHeight();
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean hideTop = dy > 0 && getScrollY() < mTopHeight;
        boolean shoTop = dy < 0 && getScrollY() >=0 && !ViewCompat.canScrollVertically(target, -1);
        if (hideTop || shoTop) {
            scrollBy(0,dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY() >= mTopHeight) {
            return false;
        } else {
            fling((int) velocityY);
            return true;
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopHeight) {
            y = mTopHeight;
        }
        if (y != getScrollY()) {

            super.scrollTo(x, y);
        }

    }

    private void fling(int velocityY) {
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,mTopHeight);
        invalidate();
    }
}
