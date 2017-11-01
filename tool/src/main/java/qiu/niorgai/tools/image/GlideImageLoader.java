package qiu.niorgai.tools.image;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

/**
 * use glide to load image
 * Created by jianqiu on 10/24/17.
 */
public class GlideImageLoader implements BaseImageLoader {

    private static final String TAG = GlideImageLoader.class.getSimpleName();

    @Override
    public void loadImage(ImageLoader loader) {
        ImageView view = (ImageView) loader.getTarget();
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isDestroyed() || ((Activity) context).isFinishing()) {
                return;
            }
        }
        if (loader.getUri() == null) {
            Log.w(TAG, "Please set uri to load");
            if (loader.getCompleteListener() != null) {
                loader.getCompleteListener().onFail();
            }
            return;
        }
        String url = loader.getUri().toString();
//        if (url.endsWith(".gif")) {
//            Glide.with(view.getContext())
//                    .asGif()
//                    .load(url)
//                    .error(R.drawable.avatar_default_circle)
//                    .placeholder(R.drawable.avatar_default_circle)
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .dontAnimate()
//                    .transformFrame(new CropCircleTransformation(view.getContext()))
//                    .into(view);
//        } else {
//            Glide.with(view.getContext())
//                    .load(url)
//                    .asBitmap()
//                    .centerCrop()
//                    .error(R.drawable.avatar_default_circle)
//                    .placeholder(R.drawable.avatar_default_circle)
//                    .into(new BitmapImageViewTarget(view) {
//                        @Override
//                        protected void setResource(Bitmap resource) {
//                            super.setResource(resource);
//                            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(view.getResources(), resource);
//                            drawable.setCircular(true);
//                            view.setImageDrawable(drawable);
//                        }
//                    });
//        }
    }
}
