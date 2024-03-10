package com.example.ry_myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View {

    private LinkedList<LinkedList<HashMap<String, Float>>> lines;
    private Paint paint;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GREEN);

        lines = new LinkedList<>();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

//        setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 處理點擊事件
//                Log.v("brad", "Click");
//            }
//        });
    }

    /**
     * 繪製背景
     * 這個方法會在 Canvas 上繪製出由 lines 變數表示的所有線條。
     * 每一條線由一個 LinkedList<HashMap<String, Float>> 表示，
     * 其中每個 HashMap 包含一個點的 x 和 y 座標。在每一條線中，
     * 這個方法會遍歷每一個點，並在連續的兩點之間繪製一條線段。
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // 遍歷 lines 鏈表中的每一條線
        for (LinkedList<HashMap<String, Float>> line : lines) {
            // 遍歷每一條線中的每一個點，並在連續的兩點之間繪製一條線段
            for (int i = 0; i < line.size() - 1; i++) {
                // 獲取第 i 個點
                HashMap<String, Float> p0 = line.get(i);
                // 獲取第 i + 1 個點
                HashMap<String, Float> p1 = line.get(i + 1);
                // 在 p0 和 p1 之間繪製一條線段
                canvas.drawLine(p0.get("x"), p0.get("y"),
                        p1.get("x"), p1.get("y"),
                        paint);
            }
        }
    }

    public void clear() {
        lines.clear();
        invalidate();
    }

    public void undo() {
        if (lines.size() > 0) {
            lines.removeLast();
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.v("brad", "Down");
            setFirstPoint(event);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.v("brad", "Up");

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.v("brad", "Move");
            setMovePoint(event);

        }
        // super.onTouchEvent(event); // 調用 View 類別的 onTouchEvent 方法處理觸摸事件的分發(setOnClickListener)

        return true; // 如果返回 true，該 View 將繼續接收到這個觸摸事件序列（包括 MOVE、UP 等後續事件）
        // 如果返回 false，該 View 將不再接收到這個觸摸事件序列（包括 MOVE、UP 等後續事件）
    }

    private void setFirstPoint(MotionEvent event) {
        LinkedList<HashMap<String, Float>> line = new LinkedList<>();
        float ex = event.getX(), ey = event.getY();
        HashMap<String, Float> point = new HashMap<>();
        point.put("x", ex);
        point.put("y", ey);
        line.add(point);

        lines.add(line);
    }

    private void setMovePoint(MotionEvent event) {
        float ex = event.getX(), ey = event.getY();
        HashMap<String, Float> point = new HashMap<>();
        point.put("x", ex);
        point.put("y", ey);
        lines.getLast().add(point);

        invalidate();
    }
}
