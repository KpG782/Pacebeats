// components/auth/SocialButton.tsx
import React from 'react'
import { TouchableOpacity, GestureResponderEvent } from 'react-native'
import { FontAwesome } from '@expo/vector-icons'
import { FontAwesome5 } from '@expo/vector-icons'
import { Client, Account, OAuthProvider } from 'react-native-appwrite'
import * as WebBrowser from 'expo-web-browser'

// Ensure EXPO_PUBLIC_APPWRITE_ENDPOINT is root URL without "/v1"
const rawEndpoint = process.env.EXPO_PUBLIC_APPWRITE_ENDPOINT!
const rootEndpoint = rawEndpoint.replace(/\/v1\/?$/, '')
const projectId  = process.env.EXPO_PUBLIC_APPWRITE_PROJECT_ID!
const successUrl = process.env.EXPO_PUBLIC_APPWRITE_OAUTH_SUCCESS!  // e.g. pacebeats://callback/spotify
const failureUrl = process.env.EXPO_PUBLIC_APPWRITE_OAUTH_FAILURE!  // e.g. pacebeats://callback/spotify?error

// initialize Appwrite SDK
const client = new Client()
    .setEndpoint(`${rootEndpoint}/v1`)
    .setProject(projectId)
const account = new Account(client)

export interface SocialButtonProps {
    iconName: string
    iconLib?: 'fa' | 'fa5'
    size?: number
    color?: string
    onPress?: (event: GestureResponderEvent) => void
    provider?: OAuthProvider | string
    className?: string
}

/**
 * Social login button launching OAuth flow with an auth session
 */
const SocialButton: React.FC<SocialButtonProps> = ({
                                                       iconName,
                                                       iconLib = 'fa',
                                                       size = 32,
                                                       color = '#fff',
                                                       onPress,
                                                       provider,
                                                       className = 'w-[60px] h-[60px] bg-[#333] rounded-[12px] items-center justify-center mx-2',
                                                   }) => {
    const Icon = iconLib === 'fa' ? FontAwesome : FontAwesome5

    const handlePress = async (event: GestureResponderEvent) => {
        if (onPress) {
            onPress(event)
            return
        }
        if (provider) {
            const oauthUrl =
                `${rootEndpoint}/v1/account/sessions/oauth2/${provider as OAuthProvider}` +
                `?project=${projectId}` +
                `&success=${encodeURIComponent(successUrl)}` +
                `&failure=${encodeURIComponent(failureUrl)}`

            console.log('Starting OAuth session:', oauthUrl)
            const result = await WebBrowser.openAuthSessionAsync(oauthUrl, successUrl)
            console.log('OAuth session result:', result)
            // The callback route or loading screen will finalize the session
        }
    }

    return (
        <TouchableOpacity
            className={className}
            onPress={handlePress}
            accessibilityRole="button"
        >
            <Icon name={iconName} size={size} color={color} />
        </TouchableOpacity>
    )
}

export default SocialButton
