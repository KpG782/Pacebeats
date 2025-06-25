// app/auth/Sign-up.tsx
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function SignUp() {
    const router = useRouter();

    const goToHealth = () => {
        // from app/auth/Sign-up.tsx, "../health" resolves to app/health.tsx
        router.push('../health');
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Sign Up Here</Text>

            {/* …your existing form fields would go here… */}

            <Button
                title="Go to Health Screen"
                onPress={goToHealth}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
        justifyContent: 'center',
        alignItems: 'center',
    },
    title: {
        fontSize: 24,
        marginBottom: 24,
    },
});
