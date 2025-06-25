// app/health.tsx
import { useState } from 'react';
import { Text, View, Button, ScrollView } from 'react-native';
import {
    initialize,
    requestPermission,
    readRecords,
    Permission,
} from 'react-native-health-connect';

export default function HealthScreen() {
    const [logs, setLogs] = useState<string[]>([]);

    const append = (msg: string) => setLogs((prev) => [...prev, msg]);

    const fetchCalories = async () => {
        append('⚙️ Initializing…');
        const ready = await initialize();
        if (!ready) {
            append('❌ Health Connect not available');
            return;
        }

        append('🔑 Requesting permission…');
        const granted: Permission[] = await requestPermission([
            { accessType: 'read', recordType: 'ActiveCaloriesBurned' },
        ]);
        // check by mapping to recordType strings
        const grantedTypes = granted.map((p) => p.recordType);
        if (!grantedTypes.includes('ActiveCaloriesBurned')) {
            append('❌ Permission denied');
            return;
        }

        append('⏱ Reading records…');
        // readRecords returns { records: YourRecordType[] }
        const { records } = await readRecords('ActiveCaloriesBurned', {
            timeRangeFilter: {
                operator: 'between',
                startTime: new Date(Date.now() - 1000 * 60 * 60 * 24).toISOString(), // last 24h
                endTime: new Date().toISOString(),
            },
        });

        append(`✅ Got ${records.length} record(s)`);
        console.log('Calories data:', records);
    };

    return (
        <View style={{ flex: 1, padding: 16 }}>
            <Button title="Fetch Calories" onPress={fetchCalories} />
            <ScrollView style={{ marginTop: 16 }}>
                {logs.map((l, i) => (
                    <Text key={i} style={{ marginBottom: 4 }}>
                        {l}
                    </Text>
                ))}
            </ScrollView>
        </View>
    );
}
