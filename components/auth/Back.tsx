import React from 'react'
import { TouchableOpacity, Image, ImageStyle, StyleProp } from 'react-native'
import { useRouter } from 'expo-router'
import BackIcon from '../../assets/back.png'

interface BackProps {
    /** Optional Tailwind classes for positioning */
    className?: string
    /** Optional style override for the image */
    imageStyle?: StyleProp<ImageStyle>
}

/**
 * A back button that navigates to the previous screen.
 * Uses a 44×44px back arrow image.
 */
const Back: React.FC<BackProps> = ({
                                       className = 'absolute top-6 left-4',
                                       imageStyle,
                                   }) => {
    const router = useRouter()

    return (
        <TouchableOpacity
            className={className}
            onPress={() => router.back()}
            accessibilityRole="button"
            accessibilityLabel="Go back"
        >
            <Image
                source={BackIcon}
                resizeMode="contain"
                style={[{ width: 44, height: 44 }, imageStyle]}
            />
        </TouchableOpacity>
    )
}

export default Back
