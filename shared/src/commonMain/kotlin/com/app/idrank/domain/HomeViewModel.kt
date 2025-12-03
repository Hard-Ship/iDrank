package com.app.idrank.domain

import androidx.collection.emptyObjectList
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.ui.util.fastSumBy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.idrank.data.WaterEntity
import com.app.idrank.data.WaterRepo
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val totalMl: Int = 0,
    val goalMl: Int = 2000,
    val history: List<WaterEntity> = emptyList(),

    val amountTextField : TextFieldState = TextFieldState("0"),
    val isShowAddWaterBs : Boolean = false,
)

class HomeViewModel(
    private val waterRepo: WaterRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            waterRepo.getTodayWater().collect { historyList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        totalMl = historyList.fastSumBy { it.amountMl},
                        history = historyList)
                }
            }
        }
    }


    fun updateAddWaterBottomSheet(show : Boolean ){
        _uiState.update { currentState ->
            currentState.copy(isShowAddWaterBs = show)
        }
    }

    fun addWater(amountMl: Int) {
        viewModelScope.launch {
            waterRepo.addWater(amountMl)
        }
    }


    fun onAddWater() {
        val amount = uiState.value.amountTextField.text
            .toString()
            .toIntOrNull()
            ?.takeIf { it > 0 } ?: return

        viewModelScope.launch {
            try {
                waterRepo.addWater(amount)
                uiState.value.amountTextField
                    .setTextAndPlaceCursorAtEnd("")
                updateAddWaterBottomSheet(false)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {

            }
        }
    }


    fun deleteWater(id: Long) {
        viewModelScope.launch {
            try {
                waterRepo.deleteWater(id)
            }catch (e : Exception){

            }

        }
    }
}