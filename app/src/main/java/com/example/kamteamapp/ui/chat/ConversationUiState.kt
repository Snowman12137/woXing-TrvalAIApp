package com.example.kamteamapp.ui.chat

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.R
import com.example.kamteamapp.data.Temp_Main_Items
import com.example.kamteamapp.data.tempMainItem2
import com.example.kamteamapp.data.tempMainItem3
import com.example.kamteamapp.data.tempMainItem4
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//class ConversationUiState(
//    val channelName: String,
//    initialMessages: List<Message>
//) {
//    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
//    val messages: List<Message> = _messages
//
//    fun addMessage(msg: Message) {
//        _messages.add(0, msg)
//    }
//}


class ConversationUiState(
    val channelName: String,
    initialMessages: List<Message>
) {
    private val _messages: MutableLiveData<List<Message>> = MutableLiveData(initialMessages)
    val messages: LiveData<List<Message>> = _messages

    fun addMessage(msg: Message) {
        val newMessages = _messages.value?.toMutableList() ?: mutableListOf()
        newMessages.add(0, msg)
        _messages.value = newMessages
    }
}




@RequiresApi(Build.VERSION_CODES.O)
class ConversationViewModel : ViewModel() {
    val conversation = ConversationUiState("General", listOf())

    init {
        viewModelScope.launch {
            delay(3000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "请说出你本次出行的计划要求",
                "8:05 PM"))
            delay(15000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "请问你们从哪里出发",
                "8:05 PM"))

            delay(8000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "好的，请问你们希望选择什么交通工具往返西安",
                "8:05 PM"))
            delay(10000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "好的请问你们的预算是多少",
                "8:06 PM"))
            delay(10000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "已为您生成出行方案",
                "8:06 PM",
                CardorImage.CardItem(
                    tempMainItem3
                )
            ))
            delay(30000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "您可以对出行方案随时进行评价",
                "8:06 PM",
            ))


            delay(15000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "请问是否需要重新修改第二日方案，推迟参观兵马俑时间",
                "8:06 PM",
            ))

            delay(8000)
            conversation.addMessage(Message(
                "蓝心大模型",
                "已为您重新规划方案",
                "8:06 PM",
                CardorImage.CardItem(
                    tempMainItem4
                )
            ))



        }
    }

    fun getMessages(): LiveData<List<Message>> {
        return conversation.messages
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

//    // EMOJI 14 🫠
//    const val EMOJI_MELTING = "\uD83E\uDEE0"
//
//    // ANDROID 13.1 😶‍🌫️
//    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"
//
//    // ANDROID 12.0 🦩
//    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"
//
//    // ANDROID 12.0  👉
//    const val EMOJI_POINTS = " \uD83D\uDC49"
}