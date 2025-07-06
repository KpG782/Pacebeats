import React from 'react'
import {
    SafeAreaView,
    View,
    Text,
    Image,
    TextInput,
    TouchableOpacity,
    StyleSheet,
} from 'react-native'
import { FontAwesome, FontAwesome5 } from '@expo/vector-icons'

import Logo from '../../assets/logo1.png'
import Button from "@/components/auth/Button";
import TextBox from "@/components/auth/TextBox";
import Title from "@/components/auth/Title";

const SignIn: React.FC = () => {
    return (
        <SafeAreaView className={"flex-1 bg-primary-dark justify-center items-center p-6"}>
            {/* Logo */}
            <Image source={Logo} style={styles.logo} resizeMode="contain" />

            {/* Title */}
            <Title/>
            {/* Inputs */}
            <View style={styles.inputContainer}>
                <TextInput
                    style={styles.input}
                    placeholder="Email"
                    keyboardType="email-address"
                    autoCapitalize="none"
                />
                <TextBox/>
            </View>

            {/* Sign-in Button */}
            <Button
                label={'Sign-In'}
                to={"/(tabs)"}
                className={"w-full h-[56px] rounded-3xl justify-center items-center bg-secondary mb-7"}
                textClassName={"font-bold text-white border-r-[20px]]"}
            />

            {/* Social Login */}
            <Text style={styles.orText}>or sign in with</Text>
            <View style={styles.socialContainer}>
                <TouchableOpacity style={styles.socialButton}>
                    <FontAwesome name="google" size={32} color="#fff" />
                </TouchableOpacity>
                <TouchableOpacity style={styles.socialButton}>
                    <FontAwesome5 name="spotify" size={32} color="#fff" />
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    )
}

export default SignIn

const styles = StyleSheet.create({
    logo: {
        width: 80,
        height: 80,
        marginBottom: 24,
    },
    inputContainer: {
        width: '100%',
        marginBottom: 24,
    },
    input: {
        width: '100%',
        height: 50,
        backgroundColor: '#fff',
        borderRadius: 25,
        paddingHorizontal: 16,
        marginBottom: 16,
        fontSize: 16,
    },
    signInButton: {
        width: '100%',
        height: 50,
        backgroundColor: '#4B7FFF',
        borderRadius: 25,
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 24,
    },
    signInText: {
        color: '#fff',
        fontSize: 16,
        fontWeight: '500',
    },
    orText: {
        fontSize: 14,
        color: '#888',
        marginBottom: 16,
    },
    socialContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
    },
    socialButton: {
        width: 60,
        height: 60,
        backgroundColor: '#333',
        borderRadius: 12,
        alignItems: 'center',
        justifyContent: 'center',
        marginHorizontal: 8,
    },
})
