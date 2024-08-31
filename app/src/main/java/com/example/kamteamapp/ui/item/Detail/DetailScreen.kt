package com.example.kamteamapp.ui.item.Detail

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.outlined.AddIcCall
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.R
import com.example.kamteamapp.componets.MyTopAppBar
import com.example.kamteamapp.data.Temp_Detail_Items
import com.example.kamteamapp.ui.theme.Detail1
import com.example.kamteamapp.ui.theme.Detail2
import com.example.kamteamapp.ui.theme.Detail3
import com.example.kamteamapp.ui.theme.KamTeamAppTheme



@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    itemId:Int?,
    navigateBack: () -> Unit,
    onNavigateToMain:()->Unit,
    viewModel: DetailViewModel = viewModel()
){
    val detailUiState by viewModel.detailUiState.collectAsState()

    Scaffold(
        topBar = {
            MyTopAppBar(
                navigateUp = { navigateBack() },
                onNavigateToMain = { onNavigateToMain() }
            )
        }

    ) { innerPadding ->
        DetailScreenBody(
            item = detailUiState.itemList.find { itemss -> itemss.trval_id == itemId },
            padding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }

}



@Composable
fun DetailScreenBody(
    item:Temp_Detail_Items?,
    padding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        if (item == null){
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        }else{
            MyShowScreen(
                item = item
            )
        }
    }

}

@Composable
fun MyShowScreen(
    item:Temp_Detail_Items?,
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        if (item?.imageResources !=null){
            Image(
                painter = painterResource(
                    id = item.imageResources),
                    contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))

            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = item!!.name,
                style = MaterialTheme.typography.Detail1,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier
                .weight(1f))
            Icon(
                imageVector = Icons.Outlined.AddIcCall,
                contentDescription = null,
            )

        }
        LazyRow {
            items(item!!.special){itemss->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .border(0.5.dp, Color.Black, RoundedCornerShape(4.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = itemss,
                        style = MaterialTheme.typography.Detail2,
                        modifier = Modifier
                            .padding(start = 3.dp, end = 3.dp, top = 1.dp, bottom = 1.dp),

                    )
                }
            }
        }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = item!!.jianjie,
                style = MaterialTheme.typography.Detail3,
                textAlign = TextAlign.Justify
                )
        }




    }
}


@Preview
@Composable
fun ShowPre(){
    KamTeamAppTheme {
        MyShowScreen(
            Temp_Detail_Items(
                1,
                1,
                imageResources = R.drawable.shengxin,
                name = "圣心大教堂",
                special = listOf(
                    "拍照",
                    "日落",
                    "礼拜",
                    "历史",
                    "博物馆",
                    "基督教",
                    "风土人情",
                    "不一样的地方"
                ),
                jianjie = "1858年，两广地区的教务由澳门教区独立出来，交由巴黎外方传教会接管，成立粤桂监牧区，该会法籍会士明稽章（Philippe François Zéphirin Guillemin）获教廷委任为首任宗座监牧。明稽章本人早于1848年10月来粤传教，当时他已有要在广州建立一间大教堂的设想。咸丰元年（1851年）在当时的两广部堂旁边购置房产，并建立了一间小教堂。咸丰三年（1853年）开始与广东地方当局谈判建造教堂的有关事宜，但当时的谈判并不顺利。其后第二次鸦片战争爆发，英法联军攻陷广州城，新城内靠近珠江的两广总督部堂被摧毁，当时的两广总督叶名琛被俘。\n" +
                        "\n" +
                        "战后明稽章依据清廷所签的《北京条约》中第六款“传教士可在各省租买田地建造天主教堂”的规定，要求广东地方当局归还以前被充公的教堂屋宇，用作新教堂的用地。广州当局最初提议了几块城外的土地，被明稽章拒绝，他以原来的教堂位于城内为由，要求以城内的土地作为补偿。最终在当时驻扎广州的法国海军总兵艾梅·库普旺的军事支持下，逼使两广总督劳崇光于1861年1月25日签订合约，将两广总督行署基址，永租给巴黎外方传教会兴建天主堂，面积为42亩6分6釐，每亩租金为1,500文钱，1863年3月又再增租地17亩6分9厘。自此以教堂为中心，在北至大新路，南至卖麻街，东至白米巷，西至玉子巷的广阔区域，逐步建立起孤儿院、育婴院、圣心书院、明德女子中学、日新小学、圣方济各小修院和中华无原罪圣母女修会等等附属设施，形成教徒群集区和华南的天主教传教中心。\n" +
                        "\n" +
                        "明稽章在1858年回法国觐见法皇拿破仑三世，他陈述英美两国在广州的势力发展，引起拿破仑三世对法国在广州的影响力逊色于他国的忧虑，明稽章借势建言利用建立教堂和传教来扩大法国在华的影响力，以弥补贸易上的劣势，最终拿破仑三世许诺个人资助五十万法郎，同时也容许明稽章向法国的天主教徒募集捐款。而在法兰西第二帝国倒台后，法国议会在1873年7月以491票赞成、100票反对的票数批准，再拨付七万五千法郎以完成教堂的建设。\n" +
                        "\n" +
                        "教堂的地基部分在1861年6月28日耶稣圣心节当日开工，1863年完成，同年12月8日的圣母无原罪日举行盛大的奠基仪式，周边的街道被装饰一新，也吸引了大批民众围观。两广总督乘坐八人大轿到场，一众文官、约300名八旗官兵，传教士和约20名神父，以及欧洲各国所有的驻粤领事和高级官员也一同出席。在奠基仪式上，当时的法国驻广州领事李天嘉（Gilbert de Trenqualye）和明稽章主教分别致辞。取自耶路撒冷的一块石头和取自罗马的一千克泥土被放置于两块奠基石之下，以示基督教创立于东方之耶路撒冷，而兴起于西方之罗马之意，两块奠基石上分别刻有“Jerusalem 1863”和“Roma 1863”的字样。\n" +
                        "\n" +
                        "教堂的建造遭遇不少阻力，除了兴建教堂及附属的建筑需要征用大量的民房土地以外，在历经两次鸦片战争之后，当时的社会气氛是广州的民众普遍排斥和敌视洋人。在建筑本身方面，与十三行和沙面的情况不同，教堂的位置是位于城内，在周围一片低矮民居的包围下非常突出，而教堂的建筑与传统的中式建筑风格和理念大异，其高耸的两座尖塔尤为当时的广州民众所反感，认为会为城市和周边的居民带来厄运。教堂的建设在1869年被迫中断，因当时的两广总督接到朝廷的指示禁止采石，面对这种局面，主教明稽章直接与北京的朝廷官员进行交涉，令问题得以解决。在1884年，因为中法战争的原因排外情绪再度高涨，传教士从广州撤出，而教堂遭到民众的洗劫。尽管从构思建堂到筹集款项，再到监督建设，明稽章是教堂计划得以实现的关键人物，但他本人却无缘得见教堂的完工。明稽章在1879年9月被教廷召回，此后未能重返中国，于1886年，即教堂建成前的两年，以72岁的高龄在巴黎病逝。石室教堂在接任主教邵斯（Augustin Chausse）的主持下建造完成。\n" +
                        "\n",
            )
        )
    }
}
