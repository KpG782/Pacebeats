import { Text, View } from "react-native";
import "../global.css";
import {Link} from 'expo-router';
import {LinearGradient} from 'expo-linear-gradient';

export default function Index() {
  return (
    <LinearGradient colors={['#1E1E1E', '#0D0D0D']} className="flex-1 justify-center items-center">
        <Text className="text-5xl text-white">Menu</Text>
    </LinearGradient>
  )
}
