package com.example.reservation.data;  // Your package


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.reservation.Store;

@Dao
public interface StoreDao {
    @Insert
    void insert(Store store);

    @Update
    void update(Store store);

    @Delete
    void delete(Store store);

    @Query("SELECT * FROM store WHERE storeId = :storeId")
    Store findStorebyID(int storeId);

}
