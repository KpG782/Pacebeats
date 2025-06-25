// app/Health.tsx
import React, { useState, useEffect } from 'react';
import {
    View,
    Button,
    Text,
    Alert,
    ScrollView,
    Linking,
} from 'react-native';
import {
    initialize,
    requestPermission,
    readRecords,
    type Permission,
} from 'react-native-health-connect';

// Configure the record types you want to fetch
const recordConfigs: { recordType: string; label: string }[] = [
    { recordType: 'Steps', label: 'Steps' },
    { recordType: 'HeartRate', label: 'Heart Rate' },
    { recordType: 'Distance', label: 'Distance' },
    { recordType: 'ActiveCaloriesBurned', label: 'Active Calories' },
    { recordType: 'SleepSession', label: 'Sleep Sessions' },
    { recordType: 'Nutrition', label: 'Nutrition' },
];

export default function HealthScreen() {
    const [log, setLog] = useState<string[]>([]);
    // Append messages to log
    const append = (msg: string) => setLog(prev => [...prev, msg]);

    // Initialize Health Connect once on mount
    useEffect(() => {
        (async () => {
            append('⚙️ Initializing Health Connect…');
            const available = await initialize();
            if (!available) append('❌ Health Connect unavailable');
            else append('✅ Health Connect initialized');
        })();
    }, []);

    // Generic fetch for any record type
    const fetchData = async (recordType: string, label: string) => {
        append(`🔑 Requesting ${label} permission…`);
        const perms: Permission[] = [{ accessType: 'read', recordType }];
        const granted = await requestPermission(perms);
        if (!granted.some(p => p.recordType === recordType)) {
            append(`❌ ${label} permission denied`);
            return Alert.alert(
                'Permission needed',
                `Please enable ${label} access in app settings.`,
                [{ text: 'Open Settings', onPress: () => Linking.openSettings() }]
            );
        }

        append(`⏱ Reading ${label}…`);
        const now = new Date();
        const { records } = await readRecords(recordType, {
            timeRangeFilter: {
                operator: 'between',
                startTime: new Date(now.getTime() - 24 * 60 * 60 * 1000).toISOString(),
                endTime: now.toISOString(),
            },
        });
        append(`✅ Fetched ${records.length} record(s) of ${label}`);
        console.log(`${label} records:`, records);
    };

    return (
        <View style={{ flex: 1, padding: 16 }}>
            {/* Buttons for each data type */}
            <ScrollView horizontal style={{ marginBottom: 16 }}>
                {recordConfigs.map(rc => (
                    <View key={rc.recordType} style={{ marginRight: 8 }}>
                        <Button
                            title={`Fetch ${rc.label}`}
                            onPress={() => fetchData(rc.recordType, rc.label)}
                        />
                    </View>
                ))}
            </ScrollView>

            {/* Log output */}
            <ScrollView>
                {log.map((l, i) => (
                    <Text key={i} style={{ marginBottom: 4 }}>{l}</Text>
                ))}
            </ScrollView>
        </View>
    );
}
