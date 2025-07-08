import React from 'react';
import { ScrollView, View, Text, StyleSheet } from 'react-native';

export default function Terms() {
    return (
        <ScrollView contentContainerStyle={styles.container}>
            <Text style={styles.title}>PaceBeats Terms & Conditions</Text>

            <Text style={styles.sectionTitle}>1. Acceptance of Terms</Text>
            <Text style={styles.sectionText}>
                By downloading or using PaceBeats (“App”), you agree to be bound by these Terms & Conditions. If you don’t agree, uninstall now.
            </Text>

            <Text style={styles.sectionTitle}>2. Use of the App</Text>
            <Text style={styles.sectionText}>
                PaceBeats grants you a non-exclusive, non-transferable license to use the App on your device. Don’t reverse-engineer, resell, redistribute, or misuse it.
            </Text>

            <Text style={styles.sectionTitle}>3. User Conduct</Text>
            <Text style={styles.sectionText}>
                You’re responsible for your own data, actions, and uploads. Don’t upload illegal, abusive, or infringing content. We will terminate accounts that break this shit.
            </Text>

            <Text style={styles.sectionTitle}>4. Privacy</Text>
            <Text style={styles.sectionText}>
                We collect basic running metrics, heart rate, and playlist data to power recommendations. We don’t sell your personal info. See our Privacy Policy for details.
            </Text>

            <Text style={styles.sectionTitle}>5. Disclaimers</Text>
            <Text style={styles.sectionText}>
                PaceBeats is provided “as-is.” We aren’t doctors or therapists. Use at your own risk. We’re not liable for injuries, data loss, or shitty song matches.
            </Text>

            <Text style={styles.sectionTitle}>6. Limitation of Liability</Text>
            <Text style={styles.sectionText}>
                To the fullest extent allowed by law, PaceBeats’ total liability won’t exceed the amount you paid (which is zero unless you bought a premium feature).
            </Text>

            <Text style={styles.sectionTitle}>7. Changes to Terms</Text>
            <Text style={styles.sectionText}>
                We can change these terms anytime. Continued use after updates means you accept the new terms.
            </Text>

            <Text style={styles.sectionTitle}>8. Contact</Text>
            <Text style={styles.sectionText}>
                Questions or complaints? Email support@pacebeats.app.
            </Text>

            <Text style={styles.effectiveDate}>Effective Date: July 9, 2025</Text>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        padding: 20,
        backgroundColor: '#fff',
    },
    title: {
        fontSize: 24,
        fontWeight: '700',
        marginBottom: 16,
    },
    sectionTitle: {
        fontSize: 18,
        fontWeight: '600',
        marginTop: 12,
    },
    sectionText: {
        fontSize: 14,
        lineHeight: 20,
        marginTop: 4,
    },
    effectiveDate: {
        marginTop: 24,
        fontSize: 12,
        color: '#666',
        textAlign: 'center',
    },
});
