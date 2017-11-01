package qiu.niorgai.tools.image;

import android.graphics.drawable.Animatable;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.*;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * use Fresco to load image
 * Created by jianqiu on 10/24/17.
 */
public class FrescoImageLoader implements BaseImageLoader {

    private static final String TAG = FrescoImageLoader.class.getSimpleName();

    private ScalingUtils.ScaleType changeScaleType(ScaleType type) {
        if (type == null) {
            return ScalingUtils.ScaleType.CENTER_CROP;
        }
        switch (type) {
            case FIT_XY:
                return ScalingUtils.ScaleType.FIT_XY;
            case FIT_START:
                return ScalingUtils.ScaleType.FIT_START;
            case FIT_CENTER:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case FIT_END:
                return ScalingUtils.ScaleType.FIT_END;
            case CENTER:
                return ScalingUtils.ScaleType.CENTER;
            case CENTER_CROP:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case CENTER_INSIDE:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            default:
                Log.w(TAG, "Fresco Image Loader not support this scale type");
        }
        return ScalingUtils.ScaleType.CENTER_CROP;
    }

    private com.facebook.drawee.generic.RoundingParams changeRoundingParams(RoundingParams params) {
        if (params == null) {
            return null;
        }
        com.facebook.drawee.generic.RoundingParams roundingParams = new com.facebook.drawee.generic.RoundingParams();
        roundingParams.setBorder(params.getBorderColor(), params.getBorderWidth());
        roundingParams.setCornersRadii(params.getCornersRadii());
        roundingParams.setOverlayColor(params.getOverlayColor());
        roundingParams.setPadding(params.getPadding());
        roundingParams.setRoundAsCircle(params.getRoundAsCircle());
        roundingParams.setRoundingMethod(changeRoundingMethod(params.getRoundingMethod()));
        return roundingParams;
    }

    private com.facebook.drawee.generic.RoundingParams.RoundingMethod changeRoundingMethod(RoundingParams.RoundingMethod method) {
        if (method == null || method == RoundingParams.RoundingMethod.BITMAP_ONLY) {
            return com.facebook.drawee.generic.RoundingParams.RoundingMethod.BITMAP_ONLY;
        }
        return com.facebook.drawee.generic.RoundingParams.RoundingMethod.OVERLAY_COLOR;
    }

    @Override
    public void loadImage(final ImageLoader loader) {
        DraweeView view = (DraweeView) loader.getTarget();
        GenericDraweeHierarchy hierarchy;
        if (view.getHierarchy() != null) {
            hierarchy = (GenericDraweeHierarchy) view.getHierarchy();
        } else {
            hierarchy = new GenericDraweeHierarchyBuilder(view.getContext().getResources())
                    .build();
        }
        if (!hierarchy.hasPlaceholderImage()) {
            hierarchy.setPlaceholderImage(loader.getHolderId(), changeScaleType(loader.getHolderScaleType()));
        }
        if (loader.isNoHolder()) {
            hierarchy.setPlaceholderImage(null);
        }
        ScalingUtils.ScaleType actualScaleType = changeScaleType(loader.getActualScaleType());
        if (hierarchy.getActualImageScaleType() == null || hierarchy.getActualImageScaleType() != actualScaleType) {
            hierarchy.setActualImageScaleType(actualScaleType);
        }
        if (hierarchy.getRoundingParams() == null) {
            hierarchy.setRoundingParams(changeRoundingParams(loader.getRoundingParams()));
        }
        if (hierarchy.getFadeDuration() != loader.getFadeDuration()) {
            hierarchy.setFadeDuration(loader.getFadeDuration());
        }
        if (loader.getAspectRatio() != 0) {
            view.setAspectRatio(loader.getAspectRatio());
        }
        view.setHierarchy(hierarchy);

        if (loader.getUri() == null) {
            Log.w(TAG, "Please set uri to load");
            if (loader.getCompleteListener() != null) {
                loader.getCompleteListener().onFail();
            }
            return;
        }

        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(loader.getUri())
                .setAutoRotateEnabled(loader.isAutoRotate());

        if (loader.getTargetWidth() != 0 && loader.getTargetHeight() != 0) {
            builder.setResizeOptions(new ResizeOptions(loader.getTargetWidth(), loader.getTargetHeight()));
        }

        PipelineDraweeControllerBuilder build = Fresco.newDraweeControllerBuilder()
                .setOldController(view.getController())
                .setAutoPlayAnimations(true)
                .setImageRequest(builder.build());

        if (loader.getCompleteListener() != null) {
            build.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        if (loader.getCompleteListener() != null) {
                            loader.getCompleteListener().onFail();
                        }
                        return;
                    }
                    if (loader.getCompleteListener() != null) {
                        loader.getCompleteListener().onSuccess(imageInfo.getWidth(), imageInfo.getHeight());
                    }
                }

                @Override
                public void onFailure(String id, Throwable throwable) {
                    super.onFailure(id, throwable);
                    if (loader.getCompleteListener() != null) {
                        loader.getCompleteListener().onFail();
                    }
                }
            });
        }
        view.setController(build.build());
    }
}
