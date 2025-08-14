package com.samsung.health.mobile.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\u0006\u0010\u0013\u001a\u00020\u000bJ\u0006\u0010\u0014\u001a\u00020\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lcom/samsung/health/mobile/presentation/viewmodel/StepCounterViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/samsung/health/mobile/presentation/viewmodel/StepCounterUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onSensorConnected", "", "onSensorDataReceived", "stepCountValue", "", "onSensorError", "error", "", "startTimeCounter", "startTracking", "stopTracking", "mobile_debug"})
public final class StepCounterViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.mobile.presentation.viewmodel.StepCounterUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.mobile.presentation.viewmodel.StepCounterUiState> uiState = null;
    
    public StepCounterViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.mobile.presentation.viewmodel.StepCounterUiState> getUiState() {
        return null;
    }
    
    public final void startTracking() {
    }
    
    public final void stopTracking() {
    }
    
    public final void onSensorDataReceived(float stepCountValue) {
    }
    
    public final void onSensorConnected() {
    }
    
    public final void onSensorError(@org.jetbrains.annotations.NotNull
    java.lang.String error) {
    }
    
    private final void startTimeCounter() {
    }
}