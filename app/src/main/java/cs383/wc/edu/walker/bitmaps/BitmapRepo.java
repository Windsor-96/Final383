package cs383.wc.edu.walker.bitmaps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
@SuppressWarnings("All")
/**
 * Created by shaffer on 4/29/16.
 */
public class BitmapRepo {
    private static BitmapRepo defaultInstance;
    private Context context;
    private LruCache<String, Bitmap> cache;

    public BitmapRepo() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        cache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static BitmapRepo getInstance() {
        if (defaultInstance == null)
            defaultInstance = new BitmapRepo();
        return defaultInstance;
    }

    public void setContext(Context c) {
        context = c;
    }

    public Bitmap getImage(int resId) {
        final String imageKey = String.valueOf(resId);

        Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            addBitmapToMemoryCache(imageKey, bitmap);
        }
        return bitmap;
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            cache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return cache.get(key);
    }

}
