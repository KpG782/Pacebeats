import React, { useState } from 'react';
import { SafeAreaView, View, Text, TextInput, TouchableOpacity, StyleSheet, Platform } from 'react-native';
import { useRouter } from 'expo-router';

export default function SetProfile() {
    const router = useRouter();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [dob, setDob] = useState('');

    const onNext = () => {
        // navigate to next onboarding screen
        router.push('./onboarding/onboarding1');
    };

    return (
        <SafeAreaView style={styles.container}>
            <Text style={styles.title}>Set your profile</Text>
            <View style={styles.form}>
                <TextInput
                    style={styles.input}
                    placeholder="First Name"
                    placeholderTextColor="#777"
                    value={firstName}
                    onChangeText={setFirstName}
                />
                <TextInput
                    style={styles.input}
                    placeholder="Last Name"
                    placeholderTextColor="#777"
                    value={lastName}
                    onChangeText={setLastName}
                />
                <TextInput
                    style={styles.input}
                    placeholder="Date of Birth"
                    placeholderTextColor="#777"
                    value={dob}
                    onChangeText={setDob}
                />
            </View>

            <View style={styles.pagination}>
                <View style={styles.dotActive} />
                <View style={styles.dot} />
            </View>

            <TouchableOpacity style={styles.button} onPress={onNext} activeOpacity={0.8}>
                <Text style={styles.buttonText}>Next</Text>
            </TouchableOpacity>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#0F0D23',
        paddingHorizontal: 24,
        paddingTop: Platform.OS === 'android' ? 24 : 0,
        justifyContent: 'space-between',
    },
    title: {
        fontSize: 24,
        fontWeight: '600',
        color: '#FFF',
        textAlign: 'center',
        marginTop: 40,
    },
    form: {
        marginTop: 40,
    },
    input: {
        height: 48,
        backgroundColor: '#1B1637',
        borderRadius: 12,
        paddingHorizontal: 16,
        color: '#FFF',
        fontSize: 16,
        marginBottom: 16,
    },
    pagination: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 24,
    },
    dot: {
        width: 8,
        height: 8,
        borderRadius: 4,
        backgroundColor: '#555',
        marginHorizontal: 4,
    },
    dotActive: {
        width: 16,
        height: 8,
        borderRadius: 4,
        backgroundColor: '#6E7EFF',
        marginHorizontal: 4,
    },
    button: {
        backgroundColor: '#6E7EFF',
        height: 48,
        borderRadius: 24,
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 32,
    },
    buttonText: {
        color: '#FFF',
        fontSize: 16,
        fontWeight: '600',
    },
});
