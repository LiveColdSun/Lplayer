package com.zz.lplayer.util;

import java.util.ArrayList;
import java.util.List;

import com.zz.lplayer.util.LrcProcess.LrcContent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义绘画歌词，产生滚动效果
 */
public class LrcView extends TextView {

	private float width; //歌词试图宽度
	private float high; 	//歌词视图高度
	private Paint CurrentPaint; //当前画笔对象
	private Paint NotCurrentPaint;//非当前画笔对象
	private float TextHigh = 25; //文本高度
	private float TextSize = 18;//文本大小
	private int Index = 0; //list集合下标

	private List<LrcContent> mSentenceEntities = new ArrayList<LrcContent>();

	public void setSentenceEntities(List<LrcContent> mSentenceEntities) {
		this.mSentenceEntities = mSentenceEntities;
	}

	public LrcView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public LrcView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public LrcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		setFocusable(true); //设置可对焦

		// 高亮部分
		CurrentPaint = new Paint();
		CurrentPaint.setAntiAlias(true); //设置抗锯齿，让文字美观饱满
		CurrentPaint.setTextAlign(Paint.Align.CENTER);//设置文本对齐方式  

		// 非高亮部分
		NotCurrentPaint = new Paint();
		NotCurrentPaint.setAntiAlias(true);
		NotCurrentPaint.setTextAlign(Paint.Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (canvas == null) {
			return;
		}

		CurrentPaint.setColor(Color.argb(210, 251, 248, 29));
		NotCurrentPaint.setColor(Color.argb(140, 255, 255, 255));

		CurrentPaint.setTextSize(24);
		CurrentPaint.setTypeface(Typeface.SERIF);

		NotCurrentPaint.setTextSize(TextSize);
		NotCurrentPaint.setTypeface(Typeface.DEFAULT);

		try {
			setText("");
			canvas.drawText(mSentenceEntities.get(Index).getLrc(), width / 2,
					high / 2, CurrentPaint);

			float tempY = high / 2;
			// 画出本句之前的句子
			for (int i = Index - 1; i >= 0; i--) {
				// 向上推移
				tempY = tempY - TextHigh;

				canvas.drawText(mSentenceEntities.get(i).getLrc(), width / 2,
						tempY, NotCurrentPaint);
			}
			tempY = high / 2;
			// 画出本句之后的句子
			for (int i = Index + 1; i < mSentenceEntities.size(); i++) {
				// 往下推移
				tempY = tempY + TextHigh;
				canvas.drawText(mSentenceEntities.get(i).getLrc(), width / 2,
						tempY, NotCurrentPaint);
			}
		} catch (Exception e) {
			setText("...无歌词文件，赶紧去下载...");
		}
	}
	/*
	 * 当view大小改变的时候调用的方法
	 *
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		this.width = w;
		this.high = h;
	}

	public void SetIndex(int index) {
		this.Index = index;
	}
}
