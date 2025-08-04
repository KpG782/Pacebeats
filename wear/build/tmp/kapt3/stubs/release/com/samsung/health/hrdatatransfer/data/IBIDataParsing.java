package com.samsung.health.hrdatatransfer.data;

import com.samsung.android.service.health.tracking.data.DataPoint;
import com.samsung.android.service.health.tracking.data.ValueKey;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/IBIDataParsing;", "", "()V", "Companion", "wear_release"})
public final class IBIDataParsing {
    @org.jetbrains.annotations.NotNull
    public static final com.samsung.health.hrdatatransfer.data.IBIDataParsing.Companion Companion = null;
    
    public IBIDataParsing() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002\u00a8\u0006\r"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/IBIDataParsing$Companion;", "", "()V", "getValidIbiList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "dataPoint", "Lcom/samsung/android/service/health/tracking/data/DataPoint;", "isIBIValid", "", "ibiStatus", "ibiValue", "wear_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final boolean isIBIValid(int ibiStatus, int ibiValue) {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<java.lang.Integer> getValidIbiList(@org.jetbrains.annotations.NotNull
        com.samsung.android.service.health.tracking.data.DataPoint dataPoint) {
            return null;
        }
    }
}