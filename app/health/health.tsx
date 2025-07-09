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
    insertRecords,
    type Permission,
    type RecordType,
} from 'react-native-health-connect';

// Define which Health Connect data types your app will use
const recordConfigs: Array<{ recordType: RecordType; label: string }> = [
    { recordType: 'Steps', label: 'Steps' },
    { recordType: 'HeartRate', label: 'Heart Rate' },
    { recordType: 'Distance', label: 'Distance' },
    { recordType: 'ActiveCaloriesBurned', label: 'Active Calories' },
    { recordType: 'SleepSession', label: 'Sleep Sessions' },
    { recordType: 'Nutrition', label: 'Nutrition' },
];

export default function HealthScreen() {
    const [log, setLog] = useState<string[]>([]);
    const append = (msg: string) => setLog((prev) => [...prev, msg]);

    // Initialize Health Connect once when component mounts
    useEffect(() => {
        (async () => {
            append('⚙️ Initializing Health Connect…');
            const available = await initialize();
            if (available) append('✅ Health Connect initialized');
            else append('❌ Health Connect unavailable');
        })();
    }, []);

    // Read data for a given type
    const fetchData = async (recordType: RecordType, label: string) => {
        append(`🔑 Requesting ${label} READ permission…`);
        const perms: Permission[] = [{ accessType: 'read', recordType }];
        const granted = await requestPermission(perms);
        if (!granted.some((p) => p.recordType === recordType && p.accessType === 'read')) {
            append(`❌ ${label} read permission denied`);
            Alert.alert(
                'Permission needed',
                `Please enable ${label} read access in Health Connect settings.`,
                [{ text: 'Open Settings', onPress: () => Linking.openSettings() }]
            );
            return;
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

    // Insert a test record for a given type
    const writeData = async (recordType: RecordType, label: string) => {
        append(`🔑 Requesting ${label} WRITE permission…`);
        const perms: Permission[] = [{ accessType: 'write', recordType }];
        const granted = await requestPermission(perms);
        if (!granted.some((p) => p.recordType === recordType && p.accessType === 'write')) {
            append(`❌ ${label} write permission denied`);
            Alert.alert(
                'Permission needed',
                `Please enable ${label} write access in Health Connect settings.`,
                [{ text: 'Open Settings', onPress: () => Linking.openSettings() }]
            );
            return;
        }

        append(`⏳ Writing ${label} record…`);
        const now = new Date();
        const oneHourAgo = new Date(now.getTime() - 60 * 60 * 1000);
        let record: any;

        switch (recordType) {
            case 'Steps':
                record = {
                    recordType,
                    count: 1000,
                    startTime: oneHourAgo.toISOString(),
                    endTime: now.toISOString(),
                };
                break;
            case 'HeartRate':
                record = {
                    recordType,
                    heartBeatsPerMinute: 70,
                    startTime: oneHourAgo.toISOString(),
                    endTime: now.toISOString(),
                };
                break;
            // TODO: add cases for Distance, ActiveCaloriesBurned, etc.
            default:
                append(`❌ Writing for ${label} not implemented`);
                return;
        }

        try {
            const ids = await insertRecords([record]);
            append(`✅ Inserted ${ids.length} ${label} record(s)`);
            console.log('Inserted record IDs:', ids);
        } catch (err) {
            append(`❌ Failed to write ${label}: ${err}`);
            console.error(err);
        }
    };

    return (
        <View style={{ flex: 1, padding: 16 }}>
            <ScrollView horizontal showsHorizontalScrollIndicator={false} style={{ marginBottom: 16 }}>
                {recordConfigs.map((rc) => (
                    <View key={rc.recordType} style={{ marginRight: 12 }}>
                        <Button title={`Fetch ${rc.label}`} onPress={() => fetchData(rc.recordType, rc.label)} />
                        <View style={{ height: 8 }} />
                        <Button title={`Add ${rc.label}`} onPress={() => writeData(rc.recordType, rc.label)} />
                    </View>
                ))}
            </ScrollView>

            <ScrollView>
                {log.map((l, i) => (
                    <Text key={i} style={{ marginBottom: 4 }}>{l}</Text>
                ))}
            </ScrollView>
        </View>
    );
}
