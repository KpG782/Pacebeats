package com.samsung.health.mobile.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.compose.runtime.State;
import com.samsung.health.data.TrackedData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/samsung/health/mobile/presentation/viewmodel/HeartbeatViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_heartbeatData", "Landroidx/compose/runtime/MutableState;", "", "Lcom/samsung/health/data/TrackedData;", "heartbeatData", "Landroidx/compose/runtime/State;", "getHeartbeatData", "()Landroidx/compose/runtime/State;", "getLatestHeartRate", "", "updateHeartbeatData", "", "data", "mobile_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class HeartbeatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.samsung.health.data.TrackedData>> _heartbeatData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.State<java.util.List<com.samsung.health.data.TrackedData>> heartbeatData = null;
    
    @javax.inject.Inject
    public HeartbeatViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.State<java.util.List<com.samsung.health.data.TrackedData>> getHeartbeatData() {
        return null;
    }
    
    public final void updateHeartbeatData(@org.jetbrains.annotations.NotNull
    java.util.List<com.samsung.health.data.TrackedData> data) {
    }
    
    public final int getLatestHeartRate() {
        return 0;
    }
}