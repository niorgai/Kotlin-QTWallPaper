package us.wili.qtwallpaper;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import us.wili.qtwallpaper.data.local.QTDatabase;
import us.wili.qtwallpaper.data.local.dao.CategoryItemDao;
import us.wili.qtwallpaper.data.local.dao.WallpaperItemDao;
import us.wili.qtwallpaper.data.model.CategoryItem;
import us.wili.qtwallpaper.data.model.WallpaperItem;

/**
 * Test room sql
 * Created by jianqiu on 11/22/17.
 */
@RunWith(AndroidJUnit4.class)
public class RoomTest {

    private WallpaperItemDao mWallpaperItemDao;
    private CategoryItemDao mCategoryItemDao;
    private QTDatabase mDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, QTDatabase.class).build();
        mCategoryItemDao = mDatabase.getCategoryDao();
        mWallpaperItemDao = mDatabase.getWallpaperDao();
    }

    @Test
    public void testCategories() throws Exception {
        ArrayList<CategoryItem> insertItems = new ArrayList<>();
        //Insert hot item
        CategoryItem hotItem = new CategoryItem();
        hotItem.setObjectId("1234");
        hotItem.setHot(true);
        hotItem.setName("Hot Category");
        insertItems.add(hotItem);
        //Insert normal item
        CategoryItem normalItem = new CategoryItem();
        normalItem.setObjectId("12345");
        insertItems.add(normalItem);
        mCategoryItemDao.insertAll(insertItems);

        List<CategoryItem> hotLists = mCategoryItemDao.getHotCategories();
        Assert.assertTrue(hotLists.get(0).getName().equals(hotItem.getName()));

        List<CategoryItem> allLists = mCategoryItemDao.getAllCategories();
        Assert.assertTrue(allLists.size() == 2);
    }

    public void testWallPapers() throws Exception {
        ArrayList<WallpaperItem> wallpaperItems = new ArrayList<>();
        WallpaperItem wallpaperItem = new WallpaperItem();
        wallpaperItem.setObjectId("123");
        wallpaperItems.add(wallpaperItem);

        WallpaperItem newItem = new WallpaperItem();
        newItem.setObjectId("1234");
        newItem.setCategoryId("1234");
        wallpaperItems.add(newItem);
        mWallpaperItemDao.insertAll(wallpaperItems);


        ArrayList<CategoryItem> insertItems = new ArrayList<>();
        //Insert hot item
        CategoryItem hotItem = new CategoryItem();
        hotItem.setObjectId("1234");
        hotItem.setHot(true);
        hotItem.setName("Hot Category");
        insertItems.add(hotItem);

        List<WallpaperItem> allItems = mWallpaperItemDao.getWallpapers();
        Assert.assertTrue(allItems.size() == 2);

        List<WallpaperItem> categoriedItem = mWallpaperItemDao.getWallpapersFromCategoryId("1234");
        Assert.assertTrue(allItems.size() == 1);
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

}
