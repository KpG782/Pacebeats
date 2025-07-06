import {View, Text, Pressable, Image} from 'react-native'
import React from 'react'
import {Tabs} from "expo-router";
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

const _Layout = () => {
    return (
        <Tabs
            screenOptions={{
                headerStyle: {
                    backgroundColor: '#2A2A2A',
                    height: 120,
                },
                headerTintColor: '#fff',
                tabBarStyle: {
                    backgroundColor: '#2A2A2A',
                    height: 90,
                    paddingTop: 10,
                    borderTopWidth: 0,
                    borderTopLeftRadius: 20,
                    borderTopRightRadius: 20,
                    zIndex: 1,
                    overflow: 'visible',
                    position: 'absolute',
                },
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
                        <Ionicons name="menu-outline" size={40} color={color} />
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
