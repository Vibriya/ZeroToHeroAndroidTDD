package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class AbstractFragment<T : ViewModel, VB : ViewBinding>(
    private val clazz: Class<T>
) : Fragment() {
    protected lateinit var binding: VB

    protected val viewModel: T by lazy {
        (requireActivity() as ProvideViewModel).viewModel(clazz)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingClass =
            (javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[1] as Class<*>
        val inflate =
            bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java,
            )
        binding = inflate.invoke(null, inflater, container, false) as VB
        return binding.root
    }

}