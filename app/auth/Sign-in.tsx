// SignIn.tsx
import React, { useState, useEffect } from 'react'
import { View, Text, TouchableOpacity, Alert } from 'react-native'
import { LinearGradient } from 'expo-linear-gradient'
import { Client, Account } from 'react-native-appwrite'
import { router } from 'expo-router'
import Back from '@/components/auth/Back'
import Logo from '@/components/auth/Logo'
import Title from '@/components/auth/Title'
import TextBox from '@/components/auth/TextBox'
import SocialButton from '@/components/auth/SocialButton'

const client = new Client()
    .setEndpoint(process.env.EXPO_PUBLIC_APPWRITE_ENDPOINT!)
    .setProject(process.env.EXPO_PUBLIC_APPWRITE_PROJECT_ID!)

const account = new Account(client)

export default function SignIn() {
    const [email, setEmail]       = useState('')
    const [password, setPassword] = useState('')
    const [loading, setLoading]   = useState(false)
    const [snackVisible, setSnackVisible] = useState(false)

    // if there's an active session, skip straight to app
    useEffect(() => {
        account.get()
            .then(() => router.replace('/(tabs)'))
            .catch(() => {}) // no session yet
    }, [])

    const handleLogin = async () => {
        if (!email || !password) {
            return Alert.alert('Both email and password are required')
        }
        setLoading(true)
        try {
            await account.createEmailPasswordSession(email, password)
            setSnackVisible(true)
            setTimeout(() => {
                setSnackVisible(false)
                router.replace('/(tabs)')
            }, 1500)
        } catch (err: any) {
            Alert.alert('Login failed', err.message || 'Invalid credentials')
            setLoading(false)
        }
    }

    return (
        <View className="flex-1">
            <LinearGradient
                colors={['#1E1E1E', '#0D0D0D']}
                className="flex-1 justify-center items-center p-6"
            >
                <Back />
                <Logo />
                <Title title="Sign in to your account" className="mb-12" />

                <TextBox
                    placeholder="Email"
                    keyboardType="email-address"
                    autoCapitalize="none"
                    value={email}
                    onChangeText={setEmail}
                />
                <TextBox
                    placeholder="Password"
                    secureTextEntry
                    value={password}
                    onChangeText={setPassword}
                />

                <TouchableOpacity
                    onPress={handleLogin}
                    disabled={loading}
                    className={`w-full h-[56px] rounded-3xl justify-center items-center bg-secondary mb-7 ${
                        loading ? 'opacity-50' : ''
                    }`}
                >
                    <Text className="font-bold text-white">
                        {loading ? 'Signing In…' : 'Sign In'}
                    </Text>
                </TouchableOpacity>

                <Text className="text-[14px] mb-4 text-[#888]">or sign in with</Text>
                <View className="flex-row justify-center items-center space-x-4">
                    <SocialButton iconName="google" />
                    <SocialButton iconName="spotify" />
                </View>
            </LinearGradient>

            {snackVisible && (
                <View className="absolute bottom-10 left-5 right-5 bg-[#333] px-4 py-3 rounded-lg">
                    <Text className="text-white text-center">Logged in successfully</Text>
                </View>
            )}
        </View>
    )
}
