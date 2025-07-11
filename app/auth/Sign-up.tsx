// SignUp.tsx
import React, { useState } from 'react'
import { View, Text, TouchableOpacity, Alert } from 'react-native'
import { LinearGradient } from 'expo-linear-gradient'
import { Client, Account, ID } from 'react-native-appwrite'
import { router } from 'expo-router'
import Back from '@/components/auth/Back'
import Title from '@/components/auth/Title'
import TextBox from '@/components/auth/TextBox'
import Checkbox from '@/components/auth/Checkbox'
import SocialButton from '@/components/auth/SocialButton'

const client = new Client()
    .setEndpoint(process.env.EXPO_PUBLIC_APPWRITE_ENDPOINT!)
    .setProject(process.env.EXPO_PUBLIC_APPWRITE_PROJECT_ID!)

const account = new Account(client)

const SignUp: React.FC = () => {
    const [username, setUsername]               = useState('')
    const [email, setEmail]                     = useState('')
    const [password, setPassword]               = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [agreed, setAgreed]                   = useState(false)
    const [loading, setLoading]                 = useState(false)
    const [snackVisible, setSnackVisible]       = useState(false)

    const handleRegister = async () => {
        if (!username || !email || !password || !confirmPassword) {
            return Alert.alert('All fields are required')
        }
        if (password !== confirmPassword) {
            return Alert.alert('Passwords do not match')
        }
        if (!agreed) {
            return Alert.alert('You must accept the terms & policy')
        }
        setLoading(true)
        try {
            await account.create(ID.unique(), email, password, username)
            await account.createEmailPasswordSession(email, password)
            setSnackVisible(true)
            setTimeout(() => {
                setSnackVisible(false)
                router.replace('/(tabs)')
            }, 1500)
        } catch (err: any) {
            Alert.alert('Registration failed', err.message || 'Unknown error')
            setLoading(false)
        }
    }

    return (
        <View className="flex-1">
            <LinearGradient
                colors={['#1E1E1F', '#0D0D0D']}
                className="flex-1 justify-center items-center p-6"
            >
                <Back />
                <Title title="Create your account" />

                <TextBox placeholder="Username" value={username} onChangeText={setUsername} />
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
                <TextBox
                    placeholder="Confirm Password"
                    secureTextEntry
                    value={confirmPassword}
                    onChangeText={setConfirmPassword}
                />

                <Checkbox checked={agreed} onToggle={() => setAgreed(p => !p)} />

                <TouchableOpacity
                    onPress={handleRegister}
                    disabled={loading}
                    className={`w-full h-[56px] rounded-3xl justify-center items-center bg-secondary mb-7 ${
                        loading ? 'opacity-50' : ''
                    }`}
                >
                    <Text className="font-bold text-white">
                        {loading ? 'Signing Up…' : 'Sign-Up'}
                    </Text>
                </TouchableOpacity>

                <Text className="text-[14px] mb-4 text-[#888]">or sign up with</Text>
                <View className="flex-row justify-center items-center space-x-4">
                    <SocialButton iconName="google" iconLib="fa" provider="google" />
                    <SocialButton iconName="spotify" iconLib="fa5" provider="spotify" />
                </View>
            </LinearGradient>

            {snackVisible && (
                <View className="absolute bottom-10 left-5 right-5 bg-[#333] px-4 py-3 rounded-lg">
                    <Text className="text-white text-center">Account created</Text>
                </View>
            )}
        </View>
    )
}

export default SignUp
