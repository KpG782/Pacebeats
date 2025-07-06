import React from 'react'
import { TextInput, TextInputProps, StyleProp, TextStyle } from 'react-native'
import {white} from "colorette";

interface TextBoxProps extends TextInputProps {
    /** Tailwind-style classes for styling */
    className?: string
    /** Placeholder text shown when empty */
    placeholder?: string
    /** Color of the placeholder text (and text) */
    placeholderTextColor?: string
    /** Optional style override (will be merged) */
    style?: StyleProp<TextStyle>
}

const TextBox: React.FC<TextBoxProps> = ({
                                             className = 'text-md w-full h-[56px] rounded-[20px] mb-7 font-primary justify-center items-center bg-[#2A2A2A] p-5',
                                             placeholder = '',
                                             placeholderTextColor = '#888888',
                                             style,
                                             ...props
                                         }) => {
    return (
        <TextInput
            className={className}
            placeholder={placeholder}
            placeholderTextColor={placeholderTextColor}
            // merge any passed-in style and force text color to match placeholder
            style={[style, { color: "#FFF"}]}
            {...props}
        />
    )
}

export default TextBox
