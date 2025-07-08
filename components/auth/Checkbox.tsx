// components/auth/Checkbox.tsx
import React from 'react'
import { TouchableOpacity, View, Text } from 'react-native'
import { router } from 'expo-router'

interface CheckboxProps {
    checked: boolean
    onToggle: () => void
}

export default function Checkbox({ checked, onToggle }: CheckboxProps) {
    return (
        <TouchableOpacity
            onPress={onToggle}
            activeOpacity={0.8}
            className="flex-row items-center p-2 mb-7"
        >
            <View
                className={`
          w-5 h-5 border-2 border-[#377DFF] rounded-md mr-2
          items-center justify-center ${checked ? 'bg-[#377DFF]' : ''}
        `}
            >
                {checked && (
                    <Text className="text-white text-[14px] leading-4 font-semibold">
                        ✓
                    </Text>
                )}
            </View>
            <Text className="text-white text-[14px] font-poppins-regular">
                I understood the{' '}
                <Text
                    className="underline text-[#377DFF]"
                    onPress={() => router.push('../../auth/Terms')}
                >
                    terms & policy
                </Text>
                .
            </Text>
        </TouchableOpacity>
    )
}
