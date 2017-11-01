package qiu.niorgai.tools.image;

/**
 * Custom ScaleType
 * Created by jianqiu on 10/24/17.
 */
public enum ScaleType {

    MATRIX      (0),

    FIT_XY      (1),

    FIT_START   (2),

    FIT_CENTER  (3),

    FIT_END     (4),

    CENTER      (5),

    CENTER_CROP (6),

    CENTER_INSIDE (7);

    ScaleType(int ni) {
        nativeInt = ni;
    }

    final int nativeInt;
}
