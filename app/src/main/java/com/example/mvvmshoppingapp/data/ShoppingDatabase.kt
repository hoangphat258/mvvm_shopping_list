package com.example.mvvmshoppingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmshoppingapp.data.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile   //make sure this instance can't be initialized in other threads
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        //This function is a Singleton. Whenever initialize ShoppingDatabase(), this method will invoke
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db").build()
    }

}