package com.samsung.health.mobile.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.ComponentActivity;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\bH\u0014J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/samsung/health/mobile/presentation/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "trackedDataList", "Landroidx/compose/runtime/MutableState;", "", "Lcom/samsung/health/data/TrackedData;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onResume", "updateTrackedDataFromIntent", "mobile_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @org.jetbrains.annotations.NotNull
    private androidx.compose.runtime.MutableState<java.util.List<com.samsung.health.data.TrackedData>> trackedDataList;
    
    public MainActivity() {
        super(0);
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onNewIntent(@org.jetbrains.annotations.NotNull
    android.content.Intent intent) {
    }
    
    private final void updateTrackedDataFromIntent(android.content.Intent intent) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
}