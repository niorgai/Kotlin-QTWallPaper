package us.wili.qtwallpaper.tool

import qiu.niorgai.tools.ResourceUtils
import us.wili.qtwallpaper.R
import java.util.*

/**
 * Created by jianqiu on 11/3/17.
 */
object ColorUtils {

    val randomColor: Int
        get() {
            val randomColor = Random().nextInt() % 8
            when (randomColor) {
                0 -> return ResourceUtils.getColor(R.color.holder1)
                1 -> return ResourceUtils.getColor(R.color.holder2)
                2 -> return ResourceUtils.getColor(R.color.holder3)
                3 -> return ResourceUtils.getColor(R.color.holder4)
                4 -> return ResourceUtils.getColor(R.color.holder5)
                5 -> return ResourceUtils.getColor(R.color.holder6)
                6 -> return ResourceUtils.getColor(R.color.holder7)
                else -> return ResourceUtils.getColor(R.color.holder8)
            }
        }
}
