import React from 'react'
import { View, Image } from 'react-native'
import Runner from '../assets/runner.png'

interface Props {
    index: number
    count: number
    containerWidth: number
    containerHeight: number
}

const StripeCard: React.FC<Props> = ({ index, count, containerWidth, containerHeight }) => {
    const stripeWidth = containerWidth / (count + 1)
    const longHeight = containerHeight
    const shortHeight = stripeWidth * 2.5
    const height = index % 2 === 1 ? longHeight : shortHeight

    return (
        <View
            style={{
                width: stripeWidth,
                height,
                borderRadius: stripeWidth / 2,
                overflow: 'hidden',
                transform: [{ rotate: '10deg' }],
            }}
        >
            <Image
                source={Runner}
                resizeMode="cover"
                style={{
                    position: 'absolute',
                    top: 0,
                    left: -index * (stripeWidth * 0.8),
                    width: stripeWidth * count,
                    height,
                    transform: [{ rotate: '-10deg' }],
                }}
            />
        </View>
    )
}

export default StripeCard
