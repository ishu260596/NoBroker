package com.ishwar_arcore.nobroker.data.local

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ishwar_arcore.nobroker.utils.TABLE_NAME
import java.io.Serializable

@Entity(tableName = TABLE_NAME)
data class ItemEntity(
    @PrimaryKey()
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "subtitle") var subtitle: String,
) : Serializable

