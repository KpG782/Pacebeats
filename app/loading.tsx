// app/loading.tsx
import React, { useEffect } from 'react'
import { SafeAreaView, View, Image, StyleSheet } from 'react-native'
import { useRouter } from 'expo-router'

// ← correct relative path (no leading slash)
import Logo from '../assets/logo1.png'
import {LinearGradient} from "expo-linear-gradient";

export default function LoadingScreen() {
    const router = useRouter()

    useEffect(() => {
        const timer = setTimeout(() => {
            router.replace('/')    // navigate to your landing page
        }, 2000)                // show splash for 2 seconds

        return () => clearTimeout(timer)
    }, [router])

    return (
        <SafeAreaView style={styles.container}>
            <LinearGradient colors={['#1E1E1E', '#0D0D0D']} style={styles.logoContainer} className={"justify-center items-center flex-1"}>
                <Image
                    source={Logo}
                    style={styles.logo}
                    resizeMode="contain"
                />
            </LinearGradient>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#0D0D0D',
    },
    logoContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    logo: {
        width: 140,
        height: 140,
    },
})
