package com.example.carapp.domain.repository

import com.example.carapp.domain.entity.Car
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface CarFieldRepository {
    fun watchCar() : BehaviorSubject<Car>

    fun updateCar(car: Car)
}
