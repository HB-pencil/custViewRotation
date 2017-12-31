package com.example.shinelon.airroate

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by Shinelon on 2017/11/22.
 */

class CustomView : View{

    private val mPaint = Paint()
    private val dColor: Int= Color.WHITE
    private var Radius: Float = 80F
    private var radius: Float = 50F
    private var currentAngle: Float = -15F
    private val gapAngle: Float = 15F
    private val spanAngle: Float = 30F
    private var circleWith: Float = 100F
    private val recf = RectF()
    private var mWidth: Float = 500F
    private var degree: Float = 0F
    private var num: Float = 0F
    private var color: String = "#0000ff"

    constructor(context: Context?):this(context,null)
    constructor(context: Context?,attrs: AttributeSet?):this(context,attrs,0)
    constructor(context: Context?,attrs: AttributeSet?,def: Int):super(context,attrs,def)

    fun getColor()= color

    fun setColor(color: String){
        this.color = color
        Log.d("setColor",color)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)

        if(wMode==MeasureSpec.AT_MOST || hMode==MeasureSpec.AT_MOST) {
            width = 100
            height = 100
        }
        mWidth = width/2F

        Radius = mWidth - 80F

        recf.left = 10F + circleWith/2 + 80F
        recf.right = width - recf.left
        recf.top = recf.left
        recf.bottom = height - recf.left
        radius = (recf.right - recf.left)/2

        Log.d("R和r","外圆半径 $Radius 小圆半径$radius")
        setMeasuredDimension(width,height)

    }

    override fun onDraw(canvas: Canvas){
        canvas.drawColor(Color.parseColor(getColor()))
        drawCircle(canvas)
        canvas.save()
        canvas.rotate(degree,mWidth,mWidth)
        drawCircleOfGr(canvas)
        canvas.restore()
    }

    fun drawCircle(canvas: Canvas){
        mPaint.color = dColor
        mPaint.strokeWidth = 20F
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
        canvas.drawCircle(mWidth,mWidth,Radius,mPaint)
        Log.w("外圆","调用")
    }

    fun drawCircleOfGr(canvas: Canvas){

        mPaint.strokeWidth = circleWith
        while (currentAngle < 330){
            val x0 = (Radius + radius*(Math.cos((currentAngle)*Math.PI/180))).toFloat() + 80
            val y0 = (Radius + radius*(Math.sin((currentAngle)*Math.PI/180))).toFloat() + 80
            val x1 = (Radius + Radius*(Math.cos((currentAngle+spanAngle)*Math.PI/180))).toFloat() + 80
            val y1 = (Radius + Radius*(Math.sin((currentAngle+spanAngle)*Math.PI/180))).toFloat() + 80
            val shader = LinearGradient(x0,y0,x1,y1, intArrayOf(resources.getColor(R.color.white),Color.WHITE),null, Shader.TileMode.CLAMP)
            mPaint.shader = shader
            canvas.drawArc(recf,currentAngle,spanAngle,false,mPaint)
            Log.w("环形","调用")
            //置空Shader
            mPaint.shader = null
            Log.d("坐标值","($x0,$y0)和($x1,$y1)")
            Log.d("当前角度",currentAngle.toString())
            currentAngle = currentAngle + spanAngle + gapAngle
        }
        //最内圆遮挡
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.parseColor(getColor())
        canvas.drawCircle(mWidth,mWidth,radius - circleWith/2 + num*circleWith,mPaint)

        //很迷。发现onDraw()有时候被执行一次有时候两次。故而把角度复原
        currentAngle = -15F
        Log.w("环形外部","调用")
    }


    fun setDegree(d: Float){
        degree = d
    }

    fun setNum(n: Float){
        num = n
    }


}
