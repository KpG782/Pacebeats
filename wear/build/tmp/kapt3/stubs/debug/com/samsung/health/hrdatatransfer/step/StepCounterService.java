package com.samsung.health.hrdatatransfer.step;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0006\u0010\u001c\u001a\u00020\u0016J\u0006\u0010\u001d\u001a\u00020\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/samsung/health/hrdatatransfer/step/StepCounterService;", "Landroid/hardware/SensorEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_stepCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "initialStepCount", "", "Ljava/lang/Float;", "isTracking", "", "sensorManager", "Landroid/hardware/SensorManager;", "stepCount", "Lkotlinx/coroutines/flow/StateFlow;", "getStepCount", "()Lkotlinx/coroutines/flow/StateFlow;", "stepSensor", "Landroid/hardware/Sensor;", "onAccuracyChanged", "", "sensor", "accuracy", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "startTracking", "stopTracking", "wear_debug"})
public final class StepCounterService implements android.hardware.SensorEventListener {
    @org.jetbrains.annotations.NotNull
    private final android.hardware.SensorManager sensorManager = null;
    @org.jetbrains.annotations.Nullable
    private final android.hardware.Sensor stepSensor = null;
    private boolean isTracking = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.Float initialStepCount;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _stepCount = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> stepCount = null;
    
    public StepCounterService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getStepCount() {
        return null;
    }
    
    public final void startTracking() {
    }
    
    public final void stopTracking() {
    }
    
    @java.lang.Override
    public void onSensorChanged(@org.jetbrains.annotations.Nullable
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable
    android.hardware.Sensor sensor, int accuracy) {
    }
}