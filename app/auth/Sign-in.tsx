import React from 'react'
import {
    SafeAreaView,
    View,
    Text,
    Image,
    TextInput,
    TouchableOpacity,
    StyleSheet,
    Dimensions,
} from 'react-native'
import { FontAwesome, FontAwesome5 } from '@expo/vector-icons'

import Logo from '../../assets/logo1.png'
import {router} from "expo-router";

const { width, height } = Dimensions.get('window')

const SignIn: React.FC = () => {
    return (
        <SafeAreaView style={styles.container}>
            {/* Logo */}
            <Image source={Logo} style={styles.logo} resizeMode="contain" />

            {/* Title */}
            <Text style={styles.title}>Sign in your account</Text>

            {/* Inputs */}
            <View style={styles.inputContainer}>
                <TextInput
                    style={styles.input}
                    placeholder="Email"
                    keyboardType="email-address"
                    autoCapitalize="none"
                />
                <TextInput
                    style={styles.input}
                    placeholder="Password"
                    secureTextEntry
                />
            </View>

            {/* Sign-in Button */}
            <TouchableOpacity style={styles.signInButton}>
                <Text  onPress={() => router.push('//(tabs)/index')} style={styles.signInText}>Sign-in</Text>
            </TouchableOpacity>

            {/* Social Login */}
            <Text style={styles.orText}>or sign in with</Text>
            <View style={styles.socialContainer}>
                <TouchableOpacity style={styles.socialButton}>
                    <FontAwesome name="google" size={32} color="#fff" />
                </TouchableOpacity>
                <TouchableOpacity style={styles.socialButton}>
                    <FontAwesome5 name="spotify" size={32} color="#fff" />
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    )
}

export default SignIn

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#F0F0F0',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 24,
    },
    logo: {
        width: 80,
        height: 80,
        marginBottom: 24,
    },
    title: {
        fontSize: 20,
        fontWeight: '600',
        color: '#000',
        marginBottom: 24,
    },
    inputContainer: {
        width: '100%',
        marginBottom: 24,
    },
    input: {
        width: '100%',
        height: 50,
        backgroundColor: '#fff',
        borderRadius: 25,
        paddingHorizontal: 16,
        marginBottom: 16,
        fontSize: 16,
    },
    signInButton: {
        width: '100%',
        height: 50,
        backgroundColor: '#4B7FFF',
        borderRadius: 25,
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 24,
    },
    signInText: {
        color: '#fff',
        fontSize: 16,
        fontWeight: '500',
    },
    orText: {
        fontSize: 14,
        color: '#888',
        marginBottom: 16,
    },
    socialContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
    },
    socialButton: {
        width: 60,
        height: 60,
        backgroundColor: '#333',
        borderRadius: 12,
        alignItems: 'center',
        justifyContent: 'center',
        marginHorizontal: 8,
    },
})
