package com.example.semenov_lesson

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class WatchView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private var time = Time(0, 0, 0)
    private lateinit var watchPaint: Paint
    private lateinit var hourPaint: Paint
    private lateinit var minutePaint: Paint
    private lateinit var secondPaint: Paint
    private val externalNotches: Array<PointF> = Array(12) { PointF(0f, 0f) }
    private val internalNotches: Array<PointF> = Array(12) { PointF(0f, 0f) }
    private val center: PointF = PointF(0f, 0f)
    private var radius = 0f
    private val hourColor = Color.RED
    private val minuteColor = Color.BLUE
    private val secondColor = Color.BLACK

    init {
        initWatchPaint()
        initArrowsPaint()
    }

    private fun initArrowsPaint() {

        hourPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hourPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics
        )
        hourPaint.color = hourColor

        minutePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        minutePaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 6f, resources.displayMetrics
        )
        minutePaint.color = minuteColor

        secondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        secondPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics
        )
        secondPaint.color = secondColor
    }

    private fun initWatchPaint() {
        watchPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        watchPaint.style = Paint.Style.STROKE
        watchPaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
        )
        watchPaint.color = Color.BLACK
    }

    fun setNewTime(time: Time) {
        this.time = time
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateCenterAndRadius()
        updateExternalNotchesArray()
        updateInternalNotchesArray()
    }

    private fun updateInternalNotchesArray() {
        for (i in 0 until 12) {
            val angle = i * (360 / 12)
            val x = center.x + radius * 0.90 * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + radius * 0.90 * sin(Math.toRadians(angle.toDouble()))
            internalNotches[i].x = x.toFloat()
            internalNotches[i].y = y.toFloat()
        }
    }

    private fun drawArrow(
        angle: Double, canvas: Canvas, paint: Paint, ratioBig: Double, ratioSmall: Double
    ) {
        val pairBig = calcXY(angle, ratioBig)
        canvas.drawLine(center.x, center.y, pairBig.first, pairBig.second, paint)

        val pairSmall = calcXY(angle + 180, ratioSmall)
        canvas.drawLine(center.x, center.y, pairSmall.first, pairSmall.second, paint)
    }

    private fun calcXY(angle: Double, radiusC: Double): Pair<Float, Float> {
        val trueAngle = angle - 90
        val x = (center.x + radius * radiusC * cos(Math.toRadians(trueAngle))).toFloat()
        val y = (center.y + radius * radiusC * sin(Math.toRadians(trueAngle))).toFloat()
        return Pair(x, y)
    }

    private fun updateExternalNotchesArray() {
        for (i in 0 until 12) {
            val angle = i * (360 / 12)
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble()))
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble()))
            externalNotches[i].x = x.toFloat()
            externalNotches[i].y = y.toFloat()
        }
    }

    private fun updateCenterAndRadius() {
        center.x = width.toFloat() / 2
        center.y = height.toFloat() / 2
        radius = width.toFloat() / 3
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)
        drawArrows(canvas)
    }

    private fun drawArrows(canvas: Canvas) {
        val hourAngle = 0.5 * (time.hour * 60 + time.minute)
        drawArrow(hourAngle, canvas, hourPaint, 0.4, 0.15)

        val minuteAngle = 0.1 * (time.minute * 60 + time.second)
        drawArrow(minuteAngle, canvas, minutePaint, 0.6, 0.2)

        val secondAngle = 6.0 * time.second
        drawArrow(secondAngle, canvas, secondPaint, 0.8, 0.4)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            center.x, center.y, radius, watchPaint
        )
        for (i in 0 until 12) {
            canvas.drawLine(
                externalNotches[i].x,
                externalNotches[i].y,
                internalNotches[i].x,
                internalNotches[i].y,
                watchPaint
            )
        }
    }

}