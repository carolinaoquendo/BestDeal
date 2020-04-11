package com.example.bestdeal.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val code: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var description: String,
    @ColumnInfo var quantity: Int,
    @ColumnInfo var price: Double
)
