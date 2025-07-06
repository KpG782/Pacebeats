// app/_layout.tsx
import { Stack } from 'expo-router'
import './global.css'
import { StatusBar } from 'react-native'

export default function RootLayout() {
    return (
        <>
            <StatusBar hidden={true} />
            <Stack initialRouteName="loading">
                {/* splash screen */}
                <Stack.Screen
                    name="loading"
                    options={{ headerShown: false }}
                />

                {/* your landing page with sign-in / sign-up buttons */}
                <Stack.Screen
                    name="index"           // this maps to app/index.tsx
                    options={{ headerShown: false }}
                />

                {/* other tabs or authenticated routes */}
                <Stack.Screen
                    name="(tabs)"
                    options={{ headerShown: false }}
                />


                {/* other tabs or authenticated routes */}
                <Stack.Screen
                    name="auth"
                    options={{ headerShown: false }}
                />

            </Stack>
        </>
    )
}
