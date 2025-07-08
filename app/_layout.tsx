// app/_layout.tsx
import React, { useCallback, useEffect, useState } from 'react'
import { Stack } from 'expo-router'
import './global.css'
import { StatusBar, View } from 'react-native'
import * as SplashScreen from 'expo-splash-screen'
import {
    useFonts,
    Poppins_400Regular,
    Poppins_500Medium,
    Poppins_600SemiBold,
    Poppins_700Bold,
} from '@expo-google-fonts/poppins'

// Keep splash screen visible until fonts load and layout is ready
SplashScreen.preventAutoHideAsync()

export default function RootLayout() {
    const [fontsLoaded] = useFonts({
        Poppins_400Regular,
        Poppins_500Medium,
        Poppins_600SemiBold,
        Poppins_700Bold,
    })
    const [appIsReady, setAppIsReady] = useState(false)

    useEffect(() => {
        if (fontsLoaded) {
            setAppIsReady(true)
        }
    }, [fontsLoaded])

    const onLayoutRootView = useCallback(async () => {
        if (appIsReady) {
            await SplashScreen.hideAsync()
        }
    }, [appIsReady])

    if (!appIsReady) {
        return null
    }

    return (
        <View style={{ flex: 1 }} onLayout={onLayoutRootView}>
            <StatusBar hidden />

            <Stack initialRouteName="loading">
                {/* Splash Screen */}
                <Stack.Screen
                    name="loading"
                    options={{ headerShown: false }}
                />

                {/* Landing Page */}
                <Stack.Screen
                    name="index"
                    options={{ headerShown: false }}
                />

                {/* Main Tabs */}
                <Stack.Screen
                    name="(tabs)"
                    options={{ headerShown: false }}
                />

                {/* Auth Routes */}
                <Stack.Screen
                    name="auth"
                    options={{ headerShown: false }}
                />
            </Stack>
        </View>
    )
}
