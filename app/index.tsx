import React from 'react'
import {
    SafeAreaView,
    View,
    Text,
    Image,
    TouchableOpacity,
    StyleSheet,
    Dimensions,
} from 'react-native'
import { useRouter } from 'expo-router'
import Runner from '../assets/runner.png'

const { width } = Dimensions.get('window')
const H_PAD = 24
const CONTAINER_WIDTH = width - H_PAD * 2
const STRIPE_COUNT = 4
const STRIPE_WIDTH = CONTAINER_WIDTH / (STRIPE_COUNT + 1)
const SHORT_HEIGHT = STRIPE_WIDTH * 2.5
const LONG_HEIGHT = STRIPE_WIDTH * 3.5
const CONTAINER_HEIGHT = LONG_HEIGHT

const Index: React.FC = () => {
    const router = useRouter()

    return (
        <SafeAreaView style={styles.container}>
            <View style={[styles.stripesContainer, { height: CONTAINER_HEIGHT }]}>
                {Array.from({ length: STRIPE_COUNT }).map((_, i) => {
                    const isLong = i % 2 === 1
                    const H = isLong ? LONG_HEIGHT : SHORT_HEIGHT
                    return (
                        <View key={i} style={[styles.stripeWrapper, { height: H }]}>
                            <Image
                                source={Runner}
                                resizeMode="cover"
                                style={{
                                    position: 'absolute',
                                    top: 0,
                                    left: -i * (STRIPE_WIDTH * 0.8),
                                    width: STRIPE_WIDTH * STRIPE_COUNT,
                                    height: H,
                                    transform: [{ rotate: '-10deg' }],
                                }}
                            />
                        </View>
                    )
                })}
            </View>

            <Text style={styles.headline}>
                Join PaceBeats and turn every step into progress.
            </Text>

            <TouchableOpacity
                style={styles.signInButton}
                onPress={() => router.push('/auth/Sign-in')}
            >
                <Text style={styles.signInText}>Sign-In</Text>
            </TouchableOpacity>

            <TouchableOpacity
                style={styles.signUpButton}
                onPress={() => router.push('/auth/Sign-up')}
            >
                <Text style={styles.signUpText}>Sign-up for free</Text>
            </TouchableOpacity>
        </SafeAreaView>
    )
}

export default Index

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        paddingHorizontal: H_PAD,
        alignItems: 'center',
        justifyContent: 'center',
    },
    stripesContainer: {
        width: CONTAINER_WIDTH,
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: 32,
    },
    stripeWrapper: {
        width: STRIPE_WIDTH,
        borderRadius: STRIPE_WIDTH / 2,
        overflow: 'hidden',
        transform: [{ rotate: '10deg' }],
    },
    headline: {
        fontSize: 16,
        textAlign: 'center',
        color: '#333',
        marginBottom: 24,
        lineHeight: 22,
    },
    signInButton: {
        width: '100%',
        paddingVertical: 14,
        backgroundColor: '#333',
        borderRadius: 30,
        alignItems: 'center',
        marginBottom: 12,
    },
    signInText: {
        color: '#fff',
        fontSize: 16,
    },
    signUpButton: {
        width: '100%',
        paddingVertical: 14,
        backgroundColor: '#4B7FFF',
        borderRadius: 30,
        alignItems: 'center',
    },
    signUpText: {
        color: '#fff',
        fontSize: 16,
    },
})