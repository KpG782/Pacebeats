import React from 'react'
import { View, Image } from 'react-native'
import Runner from '../assets/runner.png'

interface Props {
    index: number
    count: number
    containerWidth: number
}

// 1) The exact Figma artboard size you measured
const DESIGN_WIDTH = 740.22
const DESIGN_HEIGHT = 533.47

// 2) Aspect ratio of artboard
const DESIGN_RATIO = DESIGN_HEIGHT / DESIGN_WIDTH

// 3) Measured “short” stripe height in Figma (e.g. you saw one of the small bars was 400px tall)
const SHORT_DESIGN_HEIGHT = 400
const SHORT_RATIO = SHORT_DESIGN_HEIGHT / DESIGN_HEIGHT

const StripeCard: React.FC<Props> = ({ index, count, containerWidth }) => {
    // derive containerHeight from containerWidth
    const containerHeight = containerWidth * DESIGN_RATIO

    // evenly for n stripes with gaps
    const stripeWidth = containerWidth / (count + 1)

    // full-height and short-height in px
    const longHeight  = containerHeight
    const shortHeight = containerHeight * SHORT_RATIO

    // alternate tall/short
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
                    // pan the image under each stripe so it slides smoothly
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
