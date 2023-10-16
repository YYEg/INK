import android.app.Fragment
import android.widget.ImageButton
import android.core.graphics.drawable.DrawableCompat

class Extensions {

    fun Fragment(button: ImageButton, color: Int){
        val drawable = button.drawable
        DrawableCompat.setTint(drawable,color)
        button.setImageDrawable(drawable)
    }
}