import React from 'react'
import {
    SafeAreaView,
    View,
    Text,
    ImageBackground,
} from 'react-native'
import Button from '@/components/auth/Button'
import Runner from '@/assets/runner.png'
import TextBox from "@/components/auth/TextBox";

const Index: React.FC = () => {


    return (
        <>
            <SafeAreaView className={"flex-1 bg-primary-dark" }>

                {/* Stripes Section */}
                <View className={"w-full h-[60%]"}>
                    <ImageBackground source={Runner} className={"flex flex-row w-full h-full"}/>
                </View>


                <View className={"w-full h-[40%] p-6"}>
                    {/* Headline */}
                    <Text className={"font-primary font-bold text-white text-lg text-center mb-7"}>
                        Join PaceBeats and turn every step into progress.
                    </Text>


                    {/* Sign In Button */}
                    <Button
                        label={'Sign-In'}
                        to={"/auth/Sign-in"}
                        className={"w-full h-[56px] rounded-3xl justify-center items-center bg-[#2A2A2A] mb-7"}
                        textClassName={"font-bold text-white border-r-[20px]]"}
                    />

                    {/* Sign up Button */}
                    <Button
                        label={'Sign-Up for free'}
                        to={"/auth/Sign-up"}
                        className={"w-full h-[56px] rounded-3xl justify-center items-center bg-[#3F4ED3] mb-7"}
                        textClassName={"font-primary font-bold text-white border-r-[20px]]"}
                    />
                </View>

        </SafeAreaView>
        </>
    )
}

export default Index
