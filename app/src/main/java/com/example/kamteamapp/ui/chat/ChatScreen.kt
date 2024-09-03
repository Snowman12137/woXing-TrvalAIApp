package com.example.kamteamapp.ui.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.R
import com.example.kamteamapp.componets.MyAppBar
import com.example.kamteamapp.ui.HistoryProgram.MyItem
import kotlinx.coroutines.launch


data class TopMassage(
    var authorMe :String = "客户端",
    var timeNow : String = "时间"  //客户端顶部消息
)


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)



@Composable
fun ConversationScreen(
    viewModel: MainViewModel = viewModel(), // 该 ViewModel 管理对话界面的业务逻辑
    modifier: Modifier = Modifier,
) {
    val topMassage = TopMassage()  // 用于显示在消息列表顶部的消息
    val scrollState = rememberLazyListState()  // 创建一个用于控制消息列表滚动位置的状态对象。
    val topBarState = rememberTopAppBarState()  // 创建一个用于控制顶部应用栏滚动状态的对象。
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState) // 使用 TopAppBarDefaults.pinnedScrollBehavior(topBarState) 定义顶部应用栏的滚动行为，使其在滚动时固定。
    val scope = rememberCoroutineScope()  // 获取一个协程作用域，用于在 Compose 中启动协程。

    // 从 viewModel.conversationHistory 中收集对话历史数据，并将其转化为一个 Compose 可观察的状态 (State) 对象。
    // 每当 conversationHistory 中的数据发生变化时，messages 会自动更新，触发与之关联的 UI 组件重新绘制，确保用户界面总是展示最新的消息记录。
    val messages by viewModel.conversationHistory.collectAsState()

    // 每次消息列表发生变化时自动滚动到最后一项
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            scrollState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            ChannelNameBar(
                channelName = "新对话", // 显示一个顶部应用栏，标题为“新对话”
                scrollBehavior = scrollBehavior,
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Messages( // 客户端顶部显示内容
                data = topMassage, // data 参数传入 topMassage
                messages = messages, // messages 参数传入当前的消息列表
                navigateToItemUpdate = {},
                modifier = Modifier.weight(1f),
                scrollState = scrollState
            )

            // 用于发送新消息的输入框组件
            UserInput(
                onMessageSent = { content ->
                    viewModel.fetchPost(content)
                },
                resetScroll = {
                    scope.launch {
                        if (messages.isNotEmpty()) {
                            scrollState.animateScrollToItem(messages.size - 1)  // 自动滚动到最后一条消息
                        }
                    }
                },
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding()
            )

        }
    }
}





//用于显示一个顶部应用栏（App Bar），其内容包括频道名称、搜索图标和信息图标。
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
    //navigateToItemUpdate: () -> Unit,
    channelName: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    var functionalityNotAvailablePopupShown by remember { mutableStateOf(false) }
    if (functionalityNotAvailablePopupShown) {
        FunctionalityNotAvailablePopup { functionalityNotAvailablePopupShown = false }
    }
    MyAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Channel name
                Text(
                    text = channelName,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        actions = {
            // Search icon
            Icon(
                imageVector = Icons.Outlined.Search,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable(onClick = { functionalityNotAvailablePopupShown = true })
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = stringResource(id = R.string.search)
            )
            // Info icon
            Icon(
                imageVector = Icons.Rounded.Apps,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable(onClick = {} )
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = stringResource(id = R.string.info)
            )
        }
    )

}


//用于显示一个消息列表（类似聊天界面），并支持滚动显示。
@Composable
fun Messages(
    data: TopMassage,
    messages: List<Message>, //消息的列表，每条消息用 Message 对象表示
    navigateToItemUpdate: () -> Unit,  //一个回调函数，用于处理消息更新或导航操作
    scrollState: LazyListState,
    modifier: Modifier = Modifier
){
    val scope = rememberCoroutineScope()
    Box (modifier = modifier){
        //LazyColumn 是一个高效的垂直列表，适合展示大量可滚动的项目。这里 reverseLayout = true 意味着消息将从底部向上排列（最新消息在列表底部）。
        LazyColumn(
            reverseLayout = false,
            state = scrollState,
            modifier = Modifier
                .testTag("TestTag")
                .fillMaxSize()
        ) {
            for (index in messages.indices){  //通过 messages.indices 遍历消息列表的每一个元素，并获取其索引 index。
                val prevAuthor = messages.getOrNull(index - 1)?.author
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val content = messages[index] //当前索引位置的消息内容
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author

                //日期分割线
                if (index == messages.size - 1) {
                    item {
                        DayHeader("7月6日") //当消息索引为列表的最后一个时，显示日期“7月6日”。
                    }
                } else if (index == 2) {
                    item {
                        DayHeader("Today")  //当消息索引为第2条消息时，显示日期“Today”。
                    }
                }

                item {
                    //Message 组件
                    Message(
                        navigateToItemUpdate = navigateToItemUpdate,
                        msg = content,
                        isUserMe = content.author == data.authorMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor
                    )
                }

            }
        }
        // 返回顶部 TODO{返回顶部}
    }

}


////用于在聊天界面中显示一条消息。它根据消息作者的身份（是否为当前用户）以及该消息在对话中的位置来调整显示效果。
@Composable
fun Message(
    navigateToItemUpdate: () -> Unit,
    msg: Message,
    isUserMe: Boolean, //表示消息的作者是否为当前用户。如果是当前用户，值为 true，否则为 false。
    isFirstMessageByAuthor: Boolean, //示当前消息是否是该作者的第一条消息。如果是该作者的第一条消息，值为 true。
    isLastMessageByAuthor: Boolean  //表示当前消息是否是该作者的最后一条消息。如果是该作者的最后一条消息，值为 true。
){
    //borderColor 用于定义消息头像的边框颜色。根据 isUserMe 参数判断，如果消息是由当前用户发送的，边框颜色为 primary，否则为 tertiary。
    val borderColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    //用于决定消息间的间距。如果当前消息是该作者的最后一条消息，则为消息添加一个顶部内边距（8.dp），否则不添加任何内边距。
    val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier


    Row(modifier = spaceBetweenAuthors) {
        if (isLastMessageByAuthor) {
            // Avatar
            Image(
                modifier = Modifier
                    //.clickable(onClick = { onAuthorClick(msg.author) })
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(id = msg.authorImage),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            // Space under avatar
            Spacer(modifier = Modifier.width(74.dp))
        }
        AuthorAndTextMessage(
            navigateToItemUpdate=navigateToItemUpdate,
            msg = msg,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }

}


//用于在聊天界面中显示消息的内容，包括作者名、时间戳、消息气泡等。
// 具体来说，这个函数处理了消息作者和文本内容的展示，并根据消息在对话中的位置来调整布局。
@Composable
fun AuthorAndTextMessage(
    navigateToItemUpdate: () -> Unit,
    msg: Message,
    isUserMe: Boolean, //一个布尔值，表示消息的作者是否为当前用户
    isFirstMessageByAuthor: Boolean,  //表示当前消息是否是该作者的第一条消息
    isLastMessageByAuthor: Boolean, //表示当前消息是否是该作者的最后一条消息
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (isLastMessageByAuthor) {
            AuthorNameTimestamp(msg)
        }
        ChatItemBubble(msg, isUserMe,navigateToItemUpdate )
        if (isFirstMessageByAuthor) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}



private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)


//用于在聊天界面中显示消息气泡以及附加的卡片或图片内容。
@Composable
fun ChatItemBubble(
    message: Message,
    isUserMe: Boolean,
    onItemOptionClick: () -> Unit
) {

    val backgroundBubbleColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            ClickableMessage(
                message = message,
                isUserMe = isUserMe,
                //authorClicked = authorClicked
            )
        }
        message.cardorimage?.let {
            when(message.cardorimage){
                is CardorImage.CardItem->{
                    MyItem(
                        message.cardorimage.carditem,
                        modifier = Modifier
                            .clickable (onClick = onItemOptionClick)
                    )

                }
                is CardorImage.ImageItem->{
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        color = backgroundBubbleColor,
                        shape = ChatBubbleShape
                    ) {
                        Image(
                            painter = painterResource(message.cardorimage.image),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(160.dp),
                            contentDescription = stringResource(id = R.string.attached_image)
                        )
                    }
                }
            }
        }
    }
}



//用于显示可点击的消息内容。
@Composable
fun ClickableMessage(
    message: Message,
    isUserMe: Boolean,
    //authorClicked: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    val styledMessage = messageFormatter(
        text = message.content,
        primary = isUserMe
    )

    ClickableText(
        text = styledMessage,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        modifier = Modifier.padding(16.dp),
        onClick = {
            styledMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                        //SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                        else -> Unit
                    }
                }
        }
    )
}



//用于在聊天界面中显示消息的作者名称和时间戳。
@Composable
private fun AuthorNameTimestamp(msg: Message) {
    Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Text(
            text = msg.author,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = msg.timestamp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.alignBy(LastBaseline),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


//在聊天界面中显示日期头部的样式
    @Composable
    fun DayHeader(dayString: String) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .height(16.dp)
        ) {
            DayHeaderLine()
            Text(
                text = dayString,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            DayHeaderLine()
        }
    }


    @Composable
    private fun RowScope.DayHeaderLine() {
        Divider(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    }


