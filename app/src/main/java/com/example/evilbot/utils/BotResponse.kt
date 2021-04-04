package com.example.evilbot.utils

object BotResponse {

    fun preSetResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when {
            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there"
                    1 -> "Yo"
                    2 -> "What's up!"
                    else -> "error"
                }
            }
            //How are you
            message.contains("how are you") -> {
                when (random) {
                    0 -> "All good, thanks for asking."
                    1 -> "Dont talk to me human !"
                    2 -> "I'm Ok...."
                    else -> "error"

                }

            }
            else -> {
                //when user ask anything else than the above.
                when (random) {
                    0 -> "I dont understand...."
                    1 -> "I dont know......"
                    2 -> "Try ask me something else."
                    else -> "error"
                }
            }
        }
    }
}