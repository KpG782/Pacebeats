import {Button, Text, View} from "react-native";
import "../global.css";
import {useRouter} from 'expo-router';
import {LinearGradient} from 'expo-linear-gradient';

export default function Index() {
    const router = useRouter();

    const goToHealth = () => {
        // from app/auth/Sign-up.tsx, "../health" resolves to app/health.tsx
        router.push('../health');
    };

    return (
    <LinearGradient colors={['#1E1E1E', '#0D0D0D']} className={"justify-center items-center flex-1"}>
      <Text className={"text-blue-500"}>Welcome</Text>
        <View className={"flex flex-row w-full"}>
            <Button
                title={"Go to Health Tab"}
                onPress={goToHealth}
            />
        </View>
    </LinearGradient>
  );
}
