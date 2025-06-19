import React from 'react'
import {
    SafeAreaView,
    View,
    Text,
    TouchableOpacity,
    StyleSheet,
    Dimensions,
} from 'react-native'
import { useRouter } from 'expo-router'
import StripeCard from '@/components/StripeCard'

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
            {/* Stripes Section */}
            <View style={[styles.stripesContainer, { height: CONTAINER_HEIGHT }]}>
                {Array.from({ length: STRIPE_COUNT }).map((_, i) => (
                    <StripeCard
                        key={i}
                        index={i}
                        count={STRIPE_COUNT}
                        containerWidth={CONTAINER_WIDTH}
                        containerHeight={CONTAINER_HEIGHT}
                    />
                ))}
            </View>

            {/* Headline */}
            <Text style={styles.headline}>
                Join PaceBeats and turn every step into progress.
            </Text>

            {/* Buttons */}
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
        alignItems: 'flex-end',
        marginBottom: 32,
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
