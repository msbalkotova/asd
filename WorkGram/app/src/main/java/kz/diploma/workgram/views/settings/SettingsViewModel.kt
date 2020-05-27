package kz.diploma.workgram.views.settings

import androidx.lifecycle.ViewModel
import kz.diploma.workgram.models.settings.SettingDto

class SettingsViewModel: ViewModel() {
    var infoList: ArrayList<SettingDto> = arrayListOf()

    fun getInfo(): ArrayList<SettingDto>? {
        for (i in 0 until 4) {
            val settingDto = SettingDto()
            settingDto.id = i

            when(i) {
                0 -> {
                    settingDto.title = "About us"
                    settingDto.description = "Get a job quickly and easily. The best app for quick mini jobs search nearby. Only current vacancies from direct employers. Restaurants, shops, clubs, health and beauty centers, fitness clubs, hotels, travel agencies, construction companies and many others. This application is a perfect opportunity students and people who are looking for a temporary job. WorkGram is a constantly updated base of vacancies. \n" +
                            "Do you need a job? Install the application and follow the new vacancies. By the WorkGram,  you instantly receive an answer to your response."
                }
                1 -> {
                    settingDto.title = "License Agreements"
                    settingDto.description = "License agreement License agreement for the use of the program \"Workgram\" for mobile devices\n" +
                            "\n" +
                            "Before using the program, please read the terms of the following license agreement.\n" +
                            "\n" +
                            "Any use of the program by You constitutes your full and unconditional acceptance of the terms of this license agreement.\n" +
                            "\n" +
                            "If You do not accept the terms of the license agreement in full, You may not use the program for any purpose.\n" +
                            "\n" +
                            "1. General provisions\n" +
                            "\n" +
                            "1.1. This License agreement (hereinafter referred to as the license) sets out the terms of use of the  \"Workgram\" for mobile devices (hereinafter-the Program) and is concluded between any person using the Program (hereinafter-the User) and \"Workgram\", which is the copyright holder of the exclusive rights to the Program (hereinafter-the copyright Holder).\n" +
                            "\n" +
                            "1.2. by Copying the Program, installing it on your mobile device, or using the Program in any way, the User expresses their full and unconditional consent to all the terms of the License.\n" +
                            "\n" +
                            "1.3. use of the Program is permitted only under the terms of this License. If the User does not accept the terms of the License in full, the User does not have the right to use the Program for any purpose. Use of the Program in violation of any of the License terms is prohibited.\n" +
                            "\n" +
                            "1.4. use of the Program by the User under the terms of this License for personal non-commercial purposes is free of charge. Use of the Program on terms and in ways not provided for by this License is possible only on the basis of a separate agreement with the copyright Holder."
                }

                2 -> {
                    settingDto.title = "Support service"
                    settingDto.description = "We are have all rights, use this application. Today we gonna show you Workgram"
                }

                3 -> {
                    settingDto.title = "Advisor"
                    settingDto.description = "Pay around 100000tg and be on the top of our application."
                }
            }

            infoList.add(settingDto)
        }
        return infoList
    }
}