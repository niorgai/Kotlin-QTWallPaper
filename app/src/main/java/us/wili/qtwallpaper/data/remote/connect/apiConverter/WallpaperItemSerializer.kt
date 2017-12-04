package us.wili.qtwallpaper.data.remote.connect.apiConverter

import com.google.gson.*
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem
import java.lang.reflect.Type

/**
 * Change Wallpaper item serialize
 * Created by jianqiu on 11/6/17.
 */
class WallpaperItemSerializer : JsonSerializer<WallpaperItem>, JsonDeserializer<WallpaperItem> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): WallpaperItem? {
        if (json !is JsonObject) {
            return null
        }
        val item = WallpaperItem()
        item.objectId = json.getAsJsonPrimitive(WallpaperItem.COLUMN_OBJECT_ID).asString
        item.order = json.getAsJsonPrimitive(WallpaperItem.COLUMN_ORDER).asInt
        item.downloads = json.getAsJsonPrimitive(WallpaperItem.COLUMN_DOWNLOADS).asInt
        val category = json.getAsJsonObject(WallpaperItem.COLUMN_CATEGORY_ID)
        item.categoryId = category.getAsJsonPrimitive(CategoryItem.COLUMN_OBJECT_ID).asString
        item.imageUrl = json.getAsJsonPrimitive(WallpaperItem.COLUMN_IMAGE_URL).asString
        item.name = json.getAsJsonPrimitive(WallpaperItem.COLUMN_NAME).asString
        return item
    }

    override fun serialize(src: WallpaperItem, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
