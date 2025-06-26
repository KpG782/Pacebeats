// app/auth/Sign-up.tsx
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function SignUp() {
    const router = useRouter();

    const goToIndex = () => {
        // Option A: absolute
        // router.push('/');

        // Option B: relative
        router.push('..//(tabs)/saved');
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Sign Up Here</Text>
            {/* …your form fields… */}
            <Button title="Go to Home Tabs" onPress={goToIndex} />
        </View>
    );
}

const styles = StyleSheet.create({
    container: { flex: 1, padding: 16, justifyContent: 'center', alignItems: 'center' },
    title:     { fontSize: 24, marginBottom: 24 },
});
