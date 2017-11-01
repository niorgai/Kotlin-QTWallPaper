package qiu.niorgai.tools.image;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;


/**
 * Custom ImageLoader
 * Created by jianqiu on 10/24/17.
 */
public class ImageLoader {

    private int holderId = 0;

    private ScaleType mHolderScaleType = ScaleType.CENTER_CROP;

    private ScaleType mActualScaleType = ScaleType.CENTER_CROP;

    private View mTarget;

    private Uri uri;

    private boolean autoRotate;

    private int targetWidth;

    private int targetHeight;

    private int fadeDuration = 300;

    private float aspectRatio = 0;

    public interface OnLoadCompleteListener {

        void onSuccess(int width, int height);

        void onFail();
    }

    private OnLoadCompleteListener mCompleteListener;

    private RoundingParams mRoundingParams;

    private boolean noHolder = false;

    public ImageLoader() {

    }

    public int getHolderId() {
        return holderId;
    }

    public ScaleType getHolderScaleType() {
        return mHolderScaleType;
    }

    public ScaleType getActualScaleType() {
        return mActualScaleType;
    }

    public View getTarget() {
        return mTarget;
    }

    public Uri getUri() {
        return uri;
    }

    public boolean isAutoRotate() {
        return autoRotate;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public OnLoadCompleteListener getCompleteListener() {
        return mCompleteListener;
    }

    public RoundingParams getRoundingParams() {
        return mRoundingParams;
    }

    public boolean isNoHolder() {
        return noHolder;
    }

    public int getFadeDuration() {
        return fadeDuration;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public ImageLoader setHolderId(int holderId) {
        this.holderId = holderId;
        return this;
    }

    public ImageLoader setHolderScaleType(ScaleType holderScaleType) {
        mHolderScaleType = holderScaleType;
        return this;
    }

    public ImageLoader setActualScaleType(ScaleType actualScaleType) {
        mActualScaleType = actualScaleType;
        return this;
    }

    public ImageLoader setTarget(View target) {
        mTarget = target;
        return this;
    }

    public ImageLoader setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public ImageLoader setUri(String uri) {
        if (!TextUtils.isEmpty(uri)) {
            this.uri = Uri.parse(uri);
        }
        return this;
    }

    public ImageLoader setAutoRotate(boolean autoRotate) {
        this.autoRotate = autoRotate;
        return this;
    }

    public ImageLoader setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
        return this;
    }

    public ImageLoader setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
        return this;
    }

    public ImageLoader setCompleteListener(OnLoadCompleteListener completeListener) {
        mCompleteListener = completeListener;
        return this;
    }

    public ImageLoader setRoundingParams(RoundingParams roundingParams) {
        mRoundingParams = roundingParams;
        return this;
    }

    public ImageLoader setNoHolder(boolean noHolder) {
        this.noHolder = noHolder;
        return this;
    }

    public ImageLoader setFadeDuration(int fadeDuration) {
        this.fadeDuration = fadeDuration;
        return this;
    }

    public ImageLoader setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }
}
