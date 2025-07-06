// SignIn.tsx
import React from 'react'
import {
    View,
    Text,
} from 'react-native'
import Button from '@/components/auth/Button'
import TextBox from '@/components/auth/TextBox'
import Title from '@/components/auth/Title'
import Logo from '@/components/auth/Logo'
import { LinearGradient } from 'expo-linear-gradient'
import Back from "@/components/auth/Back";
import SocialButton from "@/components/auth/SocialButton";

const SignIn: React.FC = () => {     // ← hook

    return (
        <LinearGradient
            colors={['#1E1E1E', '#0D0D0D']}
            className="flex-1 bg-primary-dark justify-center items-center p-6"
        >
            <Back/>
            <Logo />
            <Title />

            <TextBox placeholder="Email" />
            <TextBox placeholder="Password" secureTextEntry />

            <Button
                label="Sign-In"
                to="/(tabs)"
                className="w-full h-[56px] rounded-3xl justify-center items-center bg-secondary mb-7"
                textClassName="font-bold text-white"
            />

            {/* Social Login */}
            <Text className={"font-[14px] mb-4 color-[#888]"}>or sign in with</Text>
            <View className={"flex flex-row justify-center items-center"}>
                <SocialButton iconName={"google"}/>
                <SocialButton iconName={"spotify"}/>

            </View>

        </LinearGradient>
    )
}

export default SignIn

