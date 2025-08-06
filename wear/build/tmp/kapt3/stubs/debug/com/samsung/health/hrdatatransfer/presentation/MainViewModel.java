package com.samsung.health.hrdatatransfer.presentation;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import com.samsung.android.service.health.tracking.HealthTrackerException;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.ConnectionMessage;
import com.samsung.health.hrdatatransfer.data.TrackerMessage;
import com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase;
import com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase;
import com.samsung.health.hrdatatransfer.domain.SendMessageUseCase;
import com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase;
import com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0002J\u0006\u0010B\u001a\u00020?J\u0006\u0010C\u001a\u00020?J\b\u0010D\u001a\u00020?H\u0002J\u0006\u0010E\u001a\u00020?J\b\u0010F\u001a\u00020?H\u0002J\u0006\u0010G\u001a\u00020?J\u0006\u0010H\u001a\u00020?R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00120%\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u000e\u0010*\u001a\u00020+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u00100\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u00104\u001a\u0002058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\u001b\u00a8\u0006I"}, d2 = {"Lcom/samsung/health/hrdatatransfer/presentation/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "makeConnectionToHealthTrackingServiceUseCase", "Lcom/samsung/health/hrdatatransfer/domain/MakeConnectionToHealthTrackingServiceUseCase;", "sendMessageUseCase", "Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;", "stopTrackingUseCase", "Lcom/samsung/health/hrdatatransfer/domain/StopTrackingUseCase;", "areTrackingCapabilitiesAvailableUseCase", "Lcom/samsung/health/hrdatatransfer/domain/AreTrackingCapabilitiesAvailableUseCase;", "application", "Landroid/app/Application;", "(Lcom/samsung/health/hrdatatransfer/domain/MakeConnectionToHealthTrackingServiceUseCase;Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;Lcom/samsung/health/hrdatatransfer/domain/StopTrackingUseCase;Lcom/samsung/health/hrdatatransfer/domain/AreTrackingCapabilitiesAvailableUseCase;Landroid/app/Application;)V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/samsung/health/hrdatatransfer/presentation/ConnectionState;", "_messageSentToast", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "_stepCount", "", "_stepTracking", "_trackingState", "Lcom/samsung/health/hrdatatransfer/presentation/TrackingState;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "currentHR", "", "currentIBI", "Ljava/util/ArrayList;", "initialStepCount", "", "Ljava/lang/Float;", "manualStepCount", "messageSentToast", "Lkotlinx/coroutines/flow/SharedFlow;", "getMessageSentToast", "()Lkotlinx/coroutines/flow/SharedFlow;", "stepCount", "getStepCount", "stepListener", "Landroid/hardware/SensorEventListener;", "stepSensor", "Landroid/hardware/Sensor;", "stepSensorManager", "Landroid/hardware/SensorManager;", "stepSensorType", "Ljava/lang/Integer;", "stepTracking", "getStepTracking", "trackHeartRateUseCase", "Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;", "getTrackHeartRateUseCase", "()Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;", "setTrackHeartRateUseCase", "(Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;)V", "trackingJob", "Lkotlinx/coroutines/Job;", "trackingState", "getTrackingState", "processExerciseUpdate", "", "trackedData", "Lcom/samsung/health/data/TrackedData;", "sendMessage", "setUpTracking", "startStepTracking", "startTracking", "stopStepTracking", "stopTracking", "toggleStepTracking", "wear_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase makeConnectionToHealthTrackingServiceUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.domain.SendMessageUseCase sendMessageUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase stopTrackingUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase areTrackingCapabilitiesAvailableUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Boolean> _messageSentToast = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.Boolean> messageSentToast = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> _trackingState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> trackingState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> connectionState = null;
    @javax.inject.Inject
    public com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase trackHeartRateUseCase;
    @org.jetbrains.annotations.NotNull
    private java.lang.String currentHR = "-";
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.Integer> currentIBI;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _stepCount = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> stepCount = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _stepTracking = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> stepTracking = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.Float initialStepCount;
    private int manualStepCount = 0;
    @org.jetbrains.annotations.Nullable
    private android.hardware.SensorManager stepSensorManager;
    @org.jetbrains.annotations.Nullable
    private android.hardware.Sensor stepSensor;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer stepSensorType;
    @org.jetbrains.annotations.NotNull
    private final android.hardware.SensorEventListener stepListener = null;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job trackingJob;
    
    @javax.inject.Inject
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase makeConnectionToHealthTrackingServiceUseCase, @org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.SendMessageUseCase sendMessageUseCase, @org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase stopTrackingUseCase, @org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase areTrackingCapabilitiesAvailableUseCase, @org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.Boolean> getMessageSentToast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> getTrackingState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase getTrackHeartRateUseCase() {
        return null;
    }
    
    public final void setTrackHeartRateUseCase(@org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getStepCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getStepTracking() {
        return null;
    }
    
    public final void toggleStepTracking() {
    }
    
    private final void startStepTracking() {
    }
    
    private final void stopStepTracking() {
    }
    
    public final void stopTracking() {
    }
    
    public final void setUpTracking() {
    }
    
    public final void sendMessage() {
    }
    
    private final void processExerciseUpdate(com.samsung.health.data.TrackedData trackedData) {
    }
    
    public final void startTracking() {
    }
}