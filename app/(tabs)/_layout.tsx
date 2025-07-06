import {View, Text, Image, Pressable} from 'react-native'
import React from 'react'
import {Tabs} from "expo-router";
import {ImageBackground} from "react-native";
import {images} from "@/constants/images";
import {Ionicons} from "@expo/vector-icons";
import {BottomTabBarButtonProps} from "@react-navigation/bottom-tabs";


const CustomHeader = () => (
    <View>
        <View className="flex-row mb-2">
            <Text className="text-[16px] font-normal text-white">Hello,</Text>
            <Text className="text-[16px] font-semibold text-white"> [User]</Text>
        </View>
        <View>
            <Text className="text-[11px] font-normal text-white">Beginner</Text>
        </View>
    </View>
);

const CustomTabBarButton: React.FC<BottomTabBarButtonProps> = ({ children, onPress }) => (
    <Pressable
        onPress={onPress}
        style={{
            top: -30,
            justifyContent: 'center',
            alignItems: 'center',
        }}
    >
        <View className="w-[70px] h-[70px] rounded-full border-4 border-neutral-400 bg-[#2A2A2A] shadow-md items-center justify-center">
            {children}
        </View>
    </Pressable>
);


const TabIcon = ({focused, icon, title} : any) => {

    if(focused){
        return (
            <ImageBackground source={images.highlight} className="flex flex-row w-full flex-1 min-w-[112px] min-h-16 mt-4 justify-center items-center rounded-full overflow-hidden"
            >
                <Image source={icon} tintColor="#151312" className="size-5" />
                <Text className="text-secondary text-base font-semibold ml-2">{title}</Text>
            </ImageBackground>
        )
    }
    return(
        <View className={"size-full justify-center items-center mt-4 rounded-full"}>
            <Image source={icon} tintColor="#A8B5DB" className="size-5" />
        </View>
    )

}

const _Layout = () => {
    return (
        <Tabs
            screenOptions={{
                headerStyle: {
                    backgroundColor: '#2A2A2A',
                    height: 120,
                },
                headerTintColor: '#fff',
                tabBarItemStyle: {
                    width: '100%',
                    height: '100%',
                    justifyContent: 'center',
                    alignItems: 'center',
                },
                tabBarStyle: {
                    backgroundColor: '#2a2a2a',
                    height: 90,
                    paddingTop: 10,
                    borderTopWidth: 0,
                    borderTopLeftRadius: 20,
                    borderTopRightRadius: 20,
                    zIndex: 1,
                    overflow: 'visible',
                    position: 'absolute',
                }
            }}
        >

            <Tabs.Screen
                name="index"
                options={{
                    tabBarShowLabel: false,
                    headerTitle: () => (
                        <CustomHeader/>
                    ),
                    headerRight: () => (
                        <Pressable
                            onPress={() => {
                                console.log('Header button pressed');
                            }}
                            className="mr-4"
                        >
                            <Ionicons name="settings-outline" size={24} color="white" />
                        </Pressable>
                    ),
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="menu-outline" size={35} color={color} />
                    )
                }}
            />

            <Tabs.Screen
                name="activity"
                options={{
                    tabBarShowLabel: false,
                    headerTitle: () => (
                        <CustomHeader/>
                    ),
                    headerRight: () => (
                        <Pressable
                            onPress={() => {
                                console.log('Header button pressed');
                            }}
                            className="mr-4"
                        >
                            <Ionicons name="settings-outline" size={24} color="white" />
                        </Pressable>
                    ),
                    tabBarIcon: ({ color }) => (
                        <Ionicons name="add" size={32} color="white" />
                    ),
                    tabBarButton: (props) => <CustomTabBarButton {...props} />,
                }}
            />

            <Tabs.Screen
                name="profile"
                options={{
                    tabBarShowLabel: false,
                    headerTitle: () => (
                        <CustomHeader/>
                    ),
                    headerRight: () => (
                        <Pressable
                            onPress={() => {
                                console.log('Header button pressed');
                            }}
                            className="mr-4"
                        >
                            <Ionicons name="settings-outline" size={24} color="white" />
                        </Pressable>
                    ),
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="person-outline" size={35} color={color} />
                    )
                }}
            />
        </Tabs>
    )
}
export default _Layout
