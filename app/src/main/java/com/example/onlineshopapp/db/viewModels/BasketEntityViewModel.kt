package com.example.onlineshopapp.db.viewModels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = BasketEntityRepository(application)
    var dataList = mutableStateOf<List<BasketEntity>>(listOf())
    private suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    private suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun addTooBasket(basketEntity: BasketEntity) {

        if (dataList.value.any { x ->
                x.productId == basketEntity.productId &&
                        x.colorId == basketEntity.colorId
                        && x.sizeId == basketEntity.sizeId
            }) {
            val oldBasket =
                dataList.value.first { x ->
                    x.productId == basketEntity.productId &&
                            x.colorId == basketEntity.colorId
                            && x.sizeId == basketEntity.sizeId
                }

            oldBasket.quantity++
            update(oldBasket)
        } else {
            insert(basketEntity)
        }
    }

    suspend fun delete(basketEntity: BasketEntity) {
        repository.delete(basketEntity)
    }

    suspend fun deleteAll() {
        return repository.deleteAll()
    }

    suspend fun getAllBasketList(): List<BasketEntity> {
        return repository.getAllBasket()
    }

    fun getAllBasketLive(): LiveData<List<BasketEntity>> {
        return repository.getAllLive()
    }
}