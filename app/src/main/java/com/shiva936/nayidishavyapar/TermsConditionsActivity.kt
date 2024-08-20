package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityTermsConditionsBinding

class TermsConditionsActivity : ComponentActivity() {
    private lateinit var binding: ActivityTermsConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val termsConditionsText = """
            At Nayi Dish Vayapar, safeguarding your privacy is our top priority. As a leading business-to-business (B2B) platform connecting sellers of waste material with buyers, we are dedicated to ensuring the confidentiality and security of your personal and business information. This Privacy Policy outlines how we collect, use, and protect your data when you use our app.

            1. Information Collection:
            We collect information necessary for the effective operation of our platform, including but not limited to business details, contact information, and transaction history. This data helps us facilitate interactions between sellers and buyers, manage transactions, and provide customer support. We may also collect technical information related to your use of the app, such as device information and usage patterns.

            2.Data Usage:
            Your data is used primarily to enable and improve our services. This includes processing transactions, managing your account, and enhancing your experience on the platform. We may use your information to communicate important updates, respond to inquiries, and offer tailored recommendations. In addition, aggregated data may be analyzed to improve app functionality and user experience.

            3. Data Protection:
            We implement stringent security measures to protect your data from unauthorized access, disclosure, or alteration. Our security protocols include encryption, access controls, and regular security audits. Despite these measures, no method of electronic transmission or storage is completely secure, and we cannot guarantee absolute protection.

            4. Data Sharing:
            We respect your privacy and do not share your personal or business data with third parties without your consent, except where necessary to facilitate transactions between sellers and buyers, comply with legal obligations, or enforce our terms and conditions. We may share aggregated or anonymized data for analytical purposes without identifying individuals.

            5. User Rights:
            You have the right to access, update, or delete your personal information. You can manage your preferences and account settings directly within the app. If you have any concerns about the data we hold or wish to exercise your rights, please contact us through the provided channels.

            6. Policy Updates:
            We may update this Privacy Policy from time to time to reflect changes in our practices or legal requirements. Any modifications will be posted within the app or communicated through other means. Your continued use of Nayi Dish Vayapar following such changes constitutes acceptance of the updated policy.

            7. Contact Us:
            For questions or concerns regarding our Privacy Policy or data practices, please reach out to us at [contact information]. We are committed to addressing any issues and ensuring a positive experience with our platform.

            By using Nayi Dish Vayapar, you acknowledge and agree to our Privacy Policy and the collection, use, and protection of your data as described.
        """.trimIndent()

        // Set the privacy policy text to the TextView
        binding.termCondition.text = termsConditionsText
    }
}
