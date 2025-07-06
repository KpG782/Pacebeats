// app/auth/Sign-up.tsx
import React from 'react'
import { View, Text, Button, StyleSheet, Alert } from 'react-native'
import { useRouter } from 'expo-router'
import { createFileRecord } from '@/services/appwrite'  // only import the document creation helper

export default function SignUp() {
    const router = useRouter()

    const goToIndex = () => {
        router.push('..//(tabs)')
    }

    const goToSetProfile = () => {
        router.push('/auth/SetProfile')
    }

    // Create a new file record in Appwrite
    const handleCreateFile = async () => {
        try {
            const doc = await createFileRecord(`file-${Date.now()}.txt`)
            Alert.alert('✅ File Record Created', JSON.stringify(doc, null, 2))
        } catch (err: any) {
            Alert.alert('❌ Create failed', err.message || 'Unknown error')
        }
    }

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Sign Up Here</Text>
            {/* …your form fields… */}

            <Button title="Go to Home Tabs" onPress={goToIndex} />
            <Button title="Go to Set Profile" onPress={goToSetProfile} />

            {/* Create File Record */}
            <View style={{ marginTop: 24, width: '100%' }}>
                <Button title="Create File Record" onPress={handleCreateFile} />
            </View>
        </View>
    )
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
})
