package com.example.carapp.presentation.view

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.example.carapp.R
import com.example.carapp.databinding.FragmentCarFieldBinding
import com.example.carapp.domain.entity.Car
import com.example.carapp.presentation.utils.ScreenMetricsCompat
import com.example.carapp.presentation.viewmodel.CarFieldViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CarFieldFragment : Fragment(R.layout.fragment_car_field) {
    private var _binding: FragmentCarFieldBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CarFieldViewModel by viewModel()
    private lateinit var valueAnimatorX: ValueAnimator
    private lateinit var valueAnimatorY: ValueAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarFieldBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.watchCar()
        viewModel.liveData.observe(viewLifecycleOwner, ::renderData)
        binding.car.setOnClickListener {
            val sizeScreen = ScreenMetricsCompat.getScreenSize(requireContext())
            viewModel.updatePositionCar(sizeScreen = sizeScreen)
        }
        initValueAnimatorX()
        initValueAnimatorY()
    }

    private fun initValueAnimatorX() {
        valueAnimatorX = ValueAnimator.ofFloat(INIT_POSITION_X, INIT_POSITION_Y)
        valueAnimatorX.addUpdateListener {
            binding.car.translationX = it.animatedValue as Float
        }
        valueAnimatorX.interpolator = LinearInterpolator()
        valueAnimatorX.duration = DURATION_ANIMATION
    }

    private fun initValueAnimatorY() {
        valueAnimatorY = ValueAnimator.ofFloat(INIT_POSITION_X, INIT_POSITION_Y)
        valueAnimatorY.addUpdateListener {
            binding.car.translationY = it.animatedValue as Float
        }
        valueAnimatorY.interpolator = LinearInterpolator()
        valueAnimatorY.duration = DURATION_ANIMATION
    }

    private fun renderData(car: Car) {
        valueAnimatorX.setFloatValues(binding.car.translationX, car.positionX.toFloat())
        valueAnimatorY.setFloatValues(binding.car.translationY, car.positionY.toFloat())
        valueAnimatorX.start()
        valueAnimatorY.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DURATION_ANIMATION = 2000L
        const val INIT_POSITION_X = 0f
        const val INIT_POSITION_Y = 0f
    }
}
