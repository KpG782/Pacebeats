import React from 'react'
import {TextInput} from "react-native";

const TextBox = () => {
    return (
        <TextInput className={"text-md w-full height-[56px] rounded-[20px] mb-7 justify-center items-center text-md bg-[#2A2A2A] p-5"} placeholder={"Password"} secureTextEntry/>

    )
}
export default TextBox
