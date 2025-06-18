import React from 'react';
import {
    SafeAreaView,
    View,
    Image,
    StatusBar,
    StyleSheet,
} from 'react-native';

import Logo from '../../assets/logo1.png';

const Saved: React.FC = () => (
    <SafeAreaView style={styles.container}>
        <StatusBar barStyle="light-content" backgroundColor="#4B7FFF" />

        <View style={styles.logoWrapper}>
            <Image
                source={Logo}
                style={styles.logo}
                resizeMode="contain"
            />
        </View>
    </SafeAreaView>
);

export default Saved;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#4B7FFF',
    },
    logoWrapper: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    logo: {
        width: 120,
        height: 120,
    },
});
