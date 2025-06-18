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

const { width } = Dimensions.get('window')
const STRIPE_COUNT = 5
const STRIPE_WIDTH = width / (STRIPE_COUNT + 1)  // adjust to taste

export default function LandingPage() {
    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.stripesContainer}>
                {Array.from({ length: STRIPE_COUNT }).map((_, i) => (
                    <View key={i} style={styles.stripeWrapper}>
                        <Image
                            source={require('../assets/runner.jpg')}
                            style={[
                                styles.image,
                                // shift the image left so each stripe shows a different slice
                                { left: -i * (STRIPE_WIDTH * 0.8) },
                            ]}
                            resizeMode="cover"
                        />
                    </View>
                ))}
            </View>

            <Text style={styles.headline}>
                Join PaceBeats and turn every step into progress.
            </Text>

            <TouchableOpacity style={styles.signInButton}>
                <Text style={styles.signInText}>Sign-In</Text>
            </TouchableOpacity>

            <TouchableOpacity style={styles.signUpButton}>
                <Text style={styles.signUpText}>Sign-up for free</Text>
            </TouchableOpacity>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        padding: 24,
        alignItems: 'center',
        justifyContent: 'center',
    },

    stripesContainer: {
        flexDirection: 'row',
        marginBottom: 32,
    },

    stripeWrapper: {
        width: STRIPE_WIDTH,
        height: STRIPE_WIDTH * 2.5,
        borderRadius: STRIPE_WIDTH / 2,
        overflow: 'hidden',
        marginHorizontal: 4,
    },

    image: {
        position: 'absolute',
        top: 0,
        width: STRIPE_WIDTH * STRIPE_COUNT,  // wide enough to span all stripes
        height: STRIPE_WIDTH * 2.5,
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
