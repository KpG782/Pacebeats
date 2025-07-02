import {Button, Text, View} from "react-native";
import "../global.css";
import {useRouter} from 'expo-router';

export default function Index() {
    const router = useRouter();

    const goToHealth = () => {
        // from app/auth/Sign-up.tsx, "../health" resolves to app/health.tsx
        router.push('../health');
    };

    return (
    <View className={"justify-center items-center"}>
      <Text className={"text-blue-500"}>Welcome</Text>
        <View className={"flex flex-row w-full"}>
            <Button
                title={"Go to Health Tab"}
                onPress={goToHealth}
            />
        </View>
    </View>
  );
}
