package com.samsung.health.hrdatatransfer.domain;

import android.util.Log;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.MessageRepository;
import com.samsung.health.hrdatatransfer.data.TrackingRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eJ\u0011\u0010\u000f\u001a\u00020\u0010H\u0086B\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;", "", "messageRepository", "Lcom/samsung/health/hrdatatransfer/data/MessageRepository;", "trackingRepository", "Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;", "getCapableNodes", "Lcom/samsung/health/hrdatatransfer/domain/GetCapableNodes;", "(Lcom/samsung/health/hrdatatransfer/data/MessageRepository;Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;Lcom/samsung/health/hrdatatransfer/domain/GetCapableNodes;)V", "encodeMessage", "", "trackedData", "Ljava/util/ArrayList;", "Lcom/samsung/health/data/TrackedData;", "Lkotlin/collections/ArrayList;", "invoke", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public final class SendMessageUseCase {
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.data.MessageRepository messageRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.data.TrackingRepository trackingRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.samsung.health.hrdatatransfer.domain.GetCapableNodes getCapableNodes = null;
    
    @javax.inject.Inject
    public SendMessageUseCase(@org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.data.MessageRepository messageRepository, @org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.data.TrackingRepository trackingRepository, @org.jetbrains.annotations.NotNull
    com.samsung.health.hrdatatransfer.domain.GetCapableNodes getCapableNodes) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String encodeMessage(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.samsung.health.data.TrackedData> trackedData) {
        return null;
    }
}