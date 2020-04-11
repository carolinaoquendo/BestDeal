package com.example.bestdeal.ui.database

import androidx.room.*

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE code = :code")
    fun findByCode(code: Int): Product

    @Insert
    fun insertAll(vararg products: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("DELETE FROM product")
    fun deleteAll()
}
