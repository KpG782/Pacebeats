import {View, Text} from 'react-native'
import "../global.css";
import React from 'react'
import {LinearGradient} from 'expo-linear-gradient';

const Activity = () => {
    return (
        <LinearGradient colors={['#1E1E1E', '#0D0D0D']} className="flex-1 justify-center items-center">
            <Text className="text-5xl text-white">Activity</Text>
        </LinearGradient>
    )
}
export default Activity
