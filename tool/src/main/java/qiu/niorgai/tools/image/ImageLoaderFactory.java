package qiu.niorgai.tools.image;

/**
 * get Instance of ImageLoader
 * Created by jianqiu on 10/24/17.
 */
public class ImageLoaderFactory {

    private static ImageLoaderFactory mInstance;

    private BaseImageLoader mLoader;

    private ImageLoaderFactory() {
        mLoader = new FrescoImageLoader();
    }

    public static ImageLoaderFactory getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderFactory.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderFactory();
                }
            }
        }
        return mInstance;
    }

    public void loadImage(ImageLoader loader) {
        mLoader.loadImage(loader);
    }

    public void setImageLoader(BaseImageLoader loader) {
        this.mLoader = loader;
    }
}
