package com.samsung.health.hrdatatransfer.presentation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import kotlinx.coroutines.flow.StateFlow;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u001a\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\n2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u001eH\u0014J\u0012\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u0006\u0010(\u001a\u00020\u001eJ\b\u0010)\u001a\u00020\u001eH\u0002J\u0006\u0010*\u001a\u00020\u001eJ\u0006\u0010+\u001a\u00020\u001eJ\b\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020-H\u0002J\b\u0010/\u001a\u00020-H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u00060"}, d2 = {"Lcom/samsung/health/hrdatatransfer/presentation/viewmodel/StepsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Landroid/hardware/SensorEventListener;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_stepsState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/samsung/health/hrdatatransfer/presentation/viewmodel/StepsState;", "accelerometer", "Landroid/hardware/Sensor;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "currentAcceleration", "", "initialStepCount", "", "lastAcceleration", "sensorManager", "Landroid/hardware/SensorManager;", "sessionSteps", "stepCounterSensor", "stepDetectorSensor", "stepThreshold", "stepsState", "Lkotlinx/coroutines/flow/StateFlow;", "getStepsState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkPermissions", "", "initializeConnection", "onAccuracyChanged", "sensor", "accuracy", "", "onCleared", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "sendStepsData", "startSimulatedTracking", "startStepsTracking", "stopStepsTracking", "tryAccelerometerTracking", "", "tryStepCounterSensor", "tryStepDetectorSensor", "wear_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class StepsViewModel extends androidx.lifecycle.AndroidViewModel implements android.hardware.SensorEventListener {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.hrdatatransfer.presentation.viewmodel.StepsState> _stepsState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.viewmodel.StepsState> stepsState = null;
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final android.hardware.SensorManager sensorManager = null;
    @org.jetbrains.annotations.Nullable
    private final android.hardware.Sensor stepCounterSensor = null;
    @org.jetbrains.annotations.Nullable
    private final android.hardware.Sensor stepDetectorSensor = null;
    @org.jetbrains.annotations.Nullable
    private final android.hardware.Sensor accelerometer = null;
    private long initialStepCount = 0L;
    private long sessionSteps = 0L;
    private float lastAcceleration = 9.8F;
    private float currentAcceleration = 9.8F;
    private final float stepThreshold = 12.0F;
    
    @javax.inject.Inject
    public StepsViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.viewmodel.StepsState> getStepsState() {
        return null;
    }
    
    private final void checkPermissions() {
    }
    
    private final void initializeConnection() {
    }
    
    public final void startStepsTracking() {
    }
    
    private final boolean tryStepCounterSensor() {
        return false;
    }
    
    private final boolean tryStepDetectorSensor() {
        return false;
    }
    
    private final boolean tryAccelerometerTracking() {
        return false;
    }
    
    private final void startSimulatedTracking() {
    }
    
    public final void stopStepsTracking() {
    }
    
    public final void sendStepsData() {
    }
    
    @java.lang.Override
    public void onSensorChanged(@org.jetbrains.annotations.Nullable
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
}