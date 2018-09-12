package br.com.luizmarcus.exemploroom

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarDao {

    @Insert
    fun insert(car: Car)

    @Query("SELECT MAX(id) FROM Car")
    fun findLastCarId (): Long

    @Query("SELECT * from Car ORDER BY name ASC")
    fun getAllCar(): List<Car>

    @Query("DELETE from Car")
    fun deleteAll()
}