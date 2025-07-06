import React from 'react'
import {Text, TouchableOpacity} from "react-native";
import {router, useRouter} from "expo-router";
// Pull in router.push’s exact allowed targets
type Router = ReturnType<typeof useRouter>
type LinkTarget = Parameters<Router['push']>[0]

interface ButtonProps{
    label: string;
    to: LinkTarget;
    className?: string;
    textClassName?:string;
}

const Button : React.FC<ButtonProps> = ({label,
                                        to,
                                        className='w-full h-14 rounded-3xl items-center bg-[#2A2A2A]',
                                        textClassName = 'font-primary font-bold text-white text-[16px]'}) => {
    const router = useRouter();
    return (
        <TouchableOpacity
            className={className}
            onPress={() => router.push(to)}>

            <Text className={textClassName}>{label}</Text>
        </TouchableOpacity>
    )
}
export default Button
