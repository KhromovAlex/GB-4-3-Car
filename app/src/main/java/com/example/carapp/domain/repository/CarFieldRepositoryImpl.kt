package com.example.carapp.domain.repository

import com.example.carapp.domain.entity.Car
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CarFieldRepositoryImpl : CarFieldRepository {
    private val carSubject: BehaviorSubject<Car> = BehaviorSubject.createDefault(Car(positionX = 0, positionY = 0))

    override fun watchCar(): BehaviorSubject<Car> = carSubject

    override fun updateCar(car: Car) {
        carSubject.onNext(car)
    }
}
