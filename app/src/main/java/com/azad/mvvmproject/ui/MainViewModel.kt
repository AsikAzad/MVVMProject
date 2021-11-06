package com.azad.mvvmproject.ui
import androidx.lifecycle.*
import com.azad.mvvmproject.model.Blog
import com.azad.mvvmproject.repository.MainRepository
import com.azad.mvvmproject.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    //Can be generic type of return data
    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
    get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.AddBlogEvents ->{
                    mainRepository.addBlog(blog = Blog(1, "ABC", "XYZ", "Url", "Cat"))
                }
                is MainStateEvent.None -> {

                }
            }
        }
    }
}

//For different type of state handle
sealed class MainStateEvent{
    object GetBlogEvents: MainStateEvent()
    object AddBlogEvents: MainStateEvent()
    object None: MainStateEvent()
}