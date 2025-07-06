// app/_layout.tsx
import { Stack } from 'expo-router'
import '../global.css'
import { StatusBar } from 'react-native'

export default function Layout() {
    return (
        <>
            <StatusBar hidden={true} />
            <Stack screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Sign-in" />
                <Stack.Screen name="Sign-up" />
            </Stack>
        </>
    )
}
