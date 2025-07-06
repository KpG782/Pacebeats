import React from 'react'
import Logo1 from '../../assets/logo1.png'
import {Image} from "react-native";

const Logo = () => {
    return (
        <Image source={Logo1} className={"w-[50px] h-[70px] justify-center items-center mb-14"} resizeMode="contain"/>
    )
}
export default Logo
