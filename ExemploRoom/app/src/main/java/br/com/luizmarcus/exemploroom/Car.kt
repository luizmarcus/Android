package br.com.luizmarcus.exemploroom

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
data class Car (
        @NonNull
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        var name: String
)