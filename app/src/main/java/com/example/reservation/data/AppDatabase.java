package com.example.reservation.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.reservation.Store;
import com.example.reservation.data.StoreDao;

@Database(entities = {Store.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StoreDao storeDao();  // Abstract method to get the DAO

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "reservation_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
