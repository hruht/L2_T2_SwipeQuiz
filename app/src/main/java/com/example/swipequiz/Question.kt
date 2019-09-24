package com.example.swipequiz

import androidx.annotation.DrawableRes

data class Question (
    var question: String,
    var corrAns: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "\u2022 To rename files use the 'mv' command.",
            "\u2022 Use the 'clear' command to clear system cache.",
            "\u2022 'rm -i' prompt before removing a file.",
            "\u2022 Use 'pwd' to change your password.",
            "\u2022 'ln -s file link' Creates a symbolic link from file.",
            "\u2022 'chown user1: file1' changes the owner of file1 to user1 and group to user1",
            "\u2022 Command 'file' creates a file.",
            "\u2022 'time' shows what time it is."
        )

        val CORRECT_ANSWERS = arrayOf(
            true,
            false,
            true,
            false,
            true,
            true,
            false,
            false
        )
    }
}