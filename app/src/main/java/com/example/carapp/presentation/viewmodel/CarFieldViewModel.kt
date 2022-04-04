package com.example.carapp.presentation.viewmodel

import android.util.Size
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carapp.domain.entity.Car
import com.example.carapp.domain.repository.CarFieldRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class CarFieldViewModel(
    private val carFieldRepository: CarFieldRepository,
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _liveData = MutableLiveData<Car>()
    val liveData: LiveData<Car> = _liveData

    fun watchCar() {
        disposables += carFieldRepository.watchCar()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _liveData.postValue(it)
            }
    }

    fun updatePositionCar(sizeScreen: Size) {
        val positionX = Random().nextInt(sizeScreen.width) - (sizeScreen.width / 2)
        val positionY = Random().nextInt(sizeScreen.height - 200) - ((sizeScreen.height - 200) / 2)

        val updatedCar = _liveData.value?.copy(positionX = positionX, positionY = positionY)
        updatedCar?.let { carFieldRepository.updateCar(it) }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
