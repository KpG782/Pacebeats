package com.samsung.health.mobile.data;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.samsung.health.mobile.presentation.MainActivity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/samsung/health/mobile/data/DataListenerService;", "Lcom/google/android/gms/wearable/WearableListenerService;", "()V", "onMessageReceived", "", "messageEvent", "Lcom/google/android/gms/wearable/MessageEvent;", "mobile_release"})
public final class DataListenerService extends com.google.android.gms.wearable.WearableListenerService {
    
    public DataListenerService() {
        super();
    }
    
    @java.lang.Override
    public void onMessageReceived(@org.jetbrains.annotations.NotNull
    com.google.android.gms.wearable.MessageEvent messageEvent) {
    }
}