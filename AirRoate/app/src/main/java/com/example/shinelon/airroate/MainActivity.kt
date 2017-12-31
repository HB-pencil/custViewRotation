package com.example.shinelon.airroate

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private var value = ValueAnimator.ofFloat(0F,1F)
    private var ob:ObjectAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        val cusView: CustomView = find(R.id.custom_view)
        val bt: Button = find(R.id.bt)
        val bt_: Button = find(R.id.bt_)

        ob = ObjectAnimator.ofObject(cusView,"color",ColorEvaluator(),"#0000FF","#000000")
        ob!!.duration = 2000
        ob!!.repeatMode = ValueAnimator.REVERSE
        ob!!.repeatCount = -1
        ob!!.interpolator = AccelerateInterpolator()
        ob!!.addUpdateListener{
            Log.d("Object动画",it.getAnimatedValue() as String)
        }
        value.duration = 2000
        value.repeatMode = ValueAnimator.REVERSE
        value.repeatCount = -1
        value.interpolator = AccelerateInterpolator()
        value.addUpdateListener {
            val v = it.getAnimatedValue() as Float
            cusView.setDegree(v*360F)
            cusView.setNum(v)
            cusView.invalidate()
            Log.d("动画", v.toString())
        }

        bt.setOnClickListener{
            value.start()
            ob!!.start()
        }

        bt_.setOnClickListener{
            //end()结束也就是值变为终值，pause()暂停
            //value.pause()
            value.end()
            ob!!.end()
            //重置
            cusView.setColor("#0000ff")
            cusView.setDegree(0F)
            cusView.setNum(0F)
            cusView.invalidate()
        }
    }

}
