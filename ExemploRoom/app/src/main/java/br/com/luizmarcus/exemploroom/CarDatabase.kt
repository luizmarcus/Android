package br.com.luizmarcus.exemploroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Car::class], version = 1)
abstract class CarDatabase : RoomDatabase(){

    abstract fun carDao(): CarDao

    companion object {
        private var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase? {
            if (INSTANCE == null) {
                synchronized(CarDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CarDatabase::class.java, "car.db")
                            .build()
                }
            }
            return INSTANCE
        }
    }

}