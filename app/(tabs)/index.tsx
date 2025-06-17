import { Text, View } from "react-native";
import "../global.css";
import {Link} from 'expo-router';

export default function Index() {
  return (
    <View className={"justify-center items-center"}>
      <Text className={"text-blue-500"}>Welcome</Text>
    </View>
  );
}
