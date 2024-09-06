package com.example.kamteamapp.ui.chat

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipDescription
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.MarsUiState
import com.example.kamteamapp.MyViewModel
import com.example.kamteamapp.R
import com.example.kamteamapp.base.databasefinal.CardorImage
import com.example.kamteamapp.base.databasefinal.Messagechat
import com.example.kamteamapp.componets.MyAppBar
import com.example.kamteamapp.ui.HistoryProgram.MyItem
import kotlinx.coroutines.launch


data class TopMassage(
    var authorMe :String = "me",
    var timeNow : String = "8:30PM"
)




@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ConversationScreen(
    updata:Boolean,
    updataInit:() -> Unit,
    index_message_id:Int,
    index_trval_id:Int,
    index_main_id:Int,
    messages:List<Messagechat>,
    navigateUp:() -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    viewModel:MyViewModel,
    //viewModels: ConversationViewModel = viewModel(),
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier,
){
    LaunchedEffect(messages.size) { viewModel.getallmessagechat(index_message_id) }


    var topmassage = TopMassage()
    val scrollState = rememberLazyListState()
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val scope = rememberCoroutineScope()

    var background by remember {
        mutableStateOf(Color.Transparent)
    }

    var borderStroke by remember {
        mutableStateOf(Color.Transparent)
    }
    //val messages by viewModels.getMessages().observeAsState(listOf())


    val dragAndDropCallback = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val clipData = event.toAndroidDragEvent().clipData

                if (clipData.itemCount < 1) {
                    return false
                }

                return true
            }

            override fun onStarted(event: DragAndDropEvent) {
                super.onStarted(event)
                borderStroke = Color.Red
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                background = Color.Red.copy(alpha = .3f)
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onExited(event)
                background = Color.Transparent
            }

            override fun onEnded(event: DragAndDropEvent) {
                super.onEnded(event)
                background = Color.Transparent
                borderStroke = Color.Transparent
            }
        }
    }




    Scaffold(
        topBar = {
            ChannelNameBar(
                navigateUp = navigateUp,
                channelName = "新对话",
                //channelMembers = uiState.channelMembers,
                //onNavIconPressed = onNavIconPressed,
                scrollBehavior = scrollBehavior,
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ){paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = background)
                .border(width = 2.dp, color = borderStroke)
                .dragAndDropTarget(shouldStartDragAndDrop = { event ->
                    event
                        .mimeTypes()
                        .contains(
                            ClipDescription.MIMETYPE_TEXT_PLAIN
                        )
                }, target = dragAndDropCallback)
        ) {
            if (!updata){
                InitDialog(
                    updataInit,
                    gotoPOST = { viewModel.fetchPost(it,index_message_id,index_trval_id,index_main_id) }
                )
            }
            Messages(
                data = topmassage,
                messages = messages,
                navigateToItemUpdate = navigateToItemUpdate ,
                modifier = Modifier.weight(1f),
                scrollState = scrollState,
                viewModel = viewModel,
                marsUiState = marsUiState,
            )
            UserInput(
                onMessageSent = { content ->
//                    viewModel.insertmessagechat(
//                        topmassage.authorMe, index_id,content, topmassage.timeNow,
//                    )
                    viewModel.fetchPost(content,index_message_id,index_trval_id,index_main_id)
                },
                resetScroll = {
                    scope.launch {
                        scrollState.scrollToItem(0)
                    }
                },
                modifier = Modifier.imePadding()
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelNameBar(
    //navigateToItemUpdate: () -> Unit,
    navigateUp: () -> Unit ,
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
        navigateUp = navigateUp,
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
                    .clickable(onClick = {})
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .height(24.dp),
                contentDescription = stringResource(id = R.string.info)
            )
        }
    )

}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Messages(
    data: TopMassage,
    messages: List<Messagechat>,
    navigateToItemUpdate: (Int) -> Unit,
    scrollState: LazyListState,
    viewModel:MyViewModel,
    modifier: Modifier = Modifier,
    marsUiState: MarsUiState,
){
    val scope = rememberCoroutineScope()
    Box (modifier = modifier){
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            modifier = Modifier
                .testTag("TestTag")
                .fillMaxSize()
        ) {
            for (index in messages.indices){
                val prevAuthor = messages.getOrNull(index - 1)?.author
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val content = messages[index]
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author


                if (index == messages.size - 1) {
                    item {
                        DayHeader("7月6日")
                    }
                } else if (index == 2) {
                    item {
                        DayHeader("Today")
                    }
                }

                item {
                    Message(
                        navigateToItemUpdate = navigateToItemUpdate,
                        msg = content,
                        isUserMe = content.author == data.authorMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor,
                        viewModel = viewModel,
                        marsUiState = marsUiState,
                    )
                }

            }
        }
        // 返回顶部 TODO{返回顶部}
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Message(
    navigateToItemUpdate: (Int) -> Unit,
    msg: Messagechat,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    viewModel:MyViewModel,
    marsUiState: MarsUiState,
){
    val borderColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }
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
                .weight(1f),
            viewModel,
            marsUiState = marsUiState
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthorAndTextMessage(
    navigateToItemUpdate: (Int) -> Unit,
    msg: Messagechat,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    modifier: Modifier = Modifier,
    viewModel:MyViewModel,
    marsUiState: MarsUiState,
) {
    Column(modifier = modifier) {
        if (isLastMessageByAuthor) {
            AuthorNameTimestamp(msg)
        }
        ChatItemBubble(msg, isUserMe,navigateToItemUpdate,viewModel,marsUiState)
        if (isFirstMessageByAuthor) {
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatItemBubble(
    message: Messagechat,
    isUserMe: Boolean,
    navigateToItemUpdate: (Int) -> Unit,
    viewModel:MyViewModel,
    marsUiState: MarsUiState,
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
        //if (message.cardid!=null && message.cardid!=0){

            when (marsUiState) {
                is MarsUiState.Loading -> LoadingScreen()

                is MarsUiState.Success ->{
                    if (message.cardid!=null && message.cardid!=0){
                        val res = viewModel.getmainItem(message.cardid)
                        MyItem(
                            res,
                            historyClick = {},
                            modifier = Modifier
                                .clickable {navigateToItemUpdate(res.travel_id)}
                        )
                    }
                }

                is MarsUiState.Error -> ErrorScreen()


            }
        //}

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = null
//    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
    }
}

@Composable
fun ClickableMessage(
    message: Messagechat,
    isUserMe: Boolean,
    //authorClicked: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current

    val styledMessage = messageFormatter(
        text = message.message,
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


@Composable
private fun AuthorNameTimestamp(msg: Messagechat) {
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


