import React from 'react'
import {Text} from "react-native";

export interface TitleProps {
//     text to display
    title: string;
//     for classname
    className?: string;
}

const Title: React.FC<TitleProps> = ({title, className = ''}) => {
    return (
        <Text className={"text-white font-poppins-semibold text-3xl mb-16 text-center"}>
            {title}
        </Text>
    )
}
export default Title
