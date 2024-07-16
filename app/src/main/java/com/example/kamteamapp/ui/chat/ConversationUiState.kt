package com.example.kamteamapp.ui.chat

import android.view.View
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.kamteamapp.ui.chat.EMOJIS.EMOJI_CLOUDS
import com.example.kamteamapp.ui.chat.EMOJIS.EMOJI_FLAMINGO
import com.example.kamteamapp.ui.chat.EMOJIS.EMOJI_MELTING
import com.example.kamteamapp.ui.chat.EMOJIS.EMOJI_PINK_HEART
import com.example.kamteamapp.ui.chat.EMOJIS.EMOJI_POINTS
import com.example.kamteamapp.R
import com.example.kamteamapp.data.Temp_Main_Items
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConversationUiState(
    val channelName: String,
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg)
    }
}


@Immutable
data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val cardorimage: CardorImage? = null,
    val authorImage: Int = if (author == "me") R.drawable.mypicture else R.drawable.vivo
)


sealed class CardorImage{
    data class CardItem(val carditem: Temp_Main_Items) :CardorImage()
    data class ImageItem( val image: Int ):CardorImage()
}


object EMOJIS {
    // EMOJI 15
    const val EMOJI_PINK_HEART = "\uD83E\uDE77"

    // EMOJI 14 🫠
    const val EMOJI_MELTING = "\uD83E\uDEE0"

    // ANDROID 13.1 😶‍🌫️
    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"

    // ANDROID 12.0 🦩
    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"

    // ANDROID 12.0  👉
    const val EMOJI_POINTS = " \uD83D\uDC49"
}