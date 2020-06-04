package rs.raf.projekat2.darko_dimitrijevic_rn9418.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import rs.raf.projekat2.darko_dimitrijevic_rn9418.R
import timber.log.Timber

class VerticalBars : View {

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    private var rect: Rect = Rect()
    private var paint: Paint = Paint()

    var offsetsList: MutableList<Double> = mutableListOf()

    private val blueFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.specBlue)
        it.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        Timber.e("77777777 " + offsetsList.toString())
        if(offsetsList.size > 0) {
            var move = width / 5

            var left = 0
            var right = move

            for (i in 0..4) {

                if(offsetsList[i] == 1.0){
                    /** 0.0 signs that we didn't created any notes that day, so we don't have
                     * anything to display. */
                    left += move + 7
                    right += move + 7
                    continue
                }

                var bottom = height
                var top: Int = (height * offsetsList.get(i)).toInt()

                rect.set(left, (top * offsetsList.get(i)).toInt(), right, bottom)
                canvas?.drawRect(rect, blueFillPaint)

                left += move + 7 /** 7 is space between bars */
                right += move + 7
            }
        }
    }
}