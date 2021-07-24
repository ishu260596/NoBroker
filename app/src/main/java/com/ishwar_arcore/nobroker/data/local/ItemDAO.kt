package com.ishwar_arcore.nobroker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(itemEntity: ItemEntity)

    @Query("SELECT * FROM item_table")
    fun getItemFromLocal(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM item_table WHERE title LIKE '%' || :search || '%' OR subtitle LIKE '%' || :search || '%'")
    fun getItemBySearch(search: String): LiveData<List<ItemEntity>>
}