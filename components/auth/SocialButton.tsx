import React from 'react'
import { TouchableOpacity, GestureResponderEvent } from 'react-native'
import { FontAwesome } from '@expo/vector-icons'
import { FontAwesome5 } from '@expo/vector-icons'

interface SocialButtonProps {
    /** Name of the icon to render */
    iconName: string
    /** Which icon library to use: 'fa' for FontAwesome or 'fa5' for FontAwesome5 */
    iconLib?: 'fa' | 'fa5'
    /** Icon size in pixels */
    size?: number
    /** Icon color */
    color?: string
    /** Callback when the button is pressed */
    onPress?: (event: GestureResponderEvent) => void
    /** Tailwind-style classes to override default styling */
    className?: string
}

/**
 * A circular social login button that renders a FontAwesome or FontAwesome5 icon.
 * Default size is 60×60px with centered content.
 * Usage:
 * <SocialButton iconName="google" iconLib="fa" onPress={...} />
 * <SocialButton iconName="spotify" iconLib="fa5" size={32} color="#1DB954" />
 */
const SocialButton: React.FC<SocialButtonProps> = ({
                                                       iconName,
                                                       iconLib = 'fa',
                                                       size = 32,
                                                       color = '#fff',
                                                       onPress,
                                                       className = 'w-[60px] h-[60px] bg-[#333] rounded-[12px] items-center justify-center mx-2',
                                                   }) => {
    const IconComponent = iconLib === 'fa' ? FontAwesome : FontAwesome5

    return (
        <TouchableOpacity
            className={className}
            onPress={onPress}
            accessibilityRole="button"
        >
            <IconComponent name={iconName} size={size} color={color} />
        </TouchableOpacity>
    )
}

export default SocialButton
