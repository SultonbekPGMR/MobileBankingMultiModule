package uz.gita.mobilebankingmultimodule.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import kotlinx.coroutines.delay
import uz.gita.common.util.formatBalance
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.components.card.AppCard
import uz.gita.mobilebankingmultimodule.ui.components.card.BannerCard
import uz.gita.mobilebankingmultimodule.ui.theme.*
import uz.gita.presenter.contract.HomeScreenContract.*
import uz.gita.presenter.contract.HomeScreenContract.Intent.*
import uz.gita.presenter.viewmodel.HomeViewModel


/**
 * Created by Sultonbek Tulanov on 02/01/2025.
 */

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getScreenModel<HomeViewModel>()
        HomeScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            viewModel::onEventDispatcher
        )
    }

    @Preview()
    @Composable
    private fun HomeScreenPreview() {
        MobileBankingMultiModuleTheme {
            HomeScreenContent()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun HomeScreenContent(
        uiState: State<UiState> = remember { mutableStateOf(UiState()) },
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        val horizontalPadding = remember { 18.dp }

        var balanceVisibility by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary)

        ) {

            Column(
            ) {
                CustomTopAppBar(userName = uiState.value.userName) { eventDispatcher(Intent.OnProfileClick) }


                PullToRefreshBox(
                    isRefreshing = uiState.value.isRefreshing,
                    onRefresh = { eventDispatcher(OnRefresh) },


                    ) {


                    LazyColumn() {

                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(brush = homeGradientSummer)
                                    .padding(
                                        bottom = 38.dp,
                                        top = 16.dp,
                                        start = horizontalPadding,
                                        end = horizontalPadding
                                    )

                            ) {
                                Text(
                                    text = stringResource(R.string.total_balance),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )



                                Row(
                                    modifier = Modifier.padding(top = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (balanceVisibility) formatBalance(uiState.value.totalBalance) else "•••••",
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )


                                    Icon(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .clip(shape = RoundedCornerShape(12.dp))
                                            .clickable {
                                                balanceVisibility = !balanceVisibility
                                            },
                                        imageVector = Icons.Outlined.Visibility,
                                        tint = Color.White,
                                        contentDescription = null
                                    )
                                }

                            }
                        }

                        // main services
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = horizontalPadding, vertical = 18.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val chunkedItems =
                                    uiState.value.mainServicesList.chunked(3)
                                chunkedItems.forEach { rowItems ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        rowItems.forEach { data ->
                                            Card(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .shadow(
                                                        elevation = 0.5.dp,
                                                        shape = RoundedCornerShape(8.dp)
                                                    ),
                                                shape = RoundedCornerShape(8.dp),

                                                ) {
                                                Column(modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                                    .clickable {
                                                        eventDispatcher(
                                                            Intent.OnServiceClicked(
                                                                data
                                                            )
                                                        )
                                                    }
                                                    .padding(top = 10.dp, bottom = 12.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center) {
                                                    Icon(
                                                        modifier = Modifier.padding(4.dp),
                                                        painter = painterResource(data.iconId),
                                                        contentDescription = data.iconId.toString(),
                                                        tint = lightGreen
                                                    )
                                                    Text(
                                                        modifier = Modifier.padding(8.dp),
                                                        text = stringResource(data.nameId),
                                                        fontSize = 14.sp,
                                                        maxLines = 1,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            }
                                        }


                                    }

                                }
                            }

                        }

                        // user cards
                        item {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = horizontalPadding)
                                    .padding(bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.lbl_cards),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Row(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .clickable {

                                        },
                                    verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = stringResource(R.string.all),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = lightGreen
                                    )
                                    Icon(
                                        imageVector = Icons.Filled.ChevronRight,
                                        contentDescription = null,
                                        tint = lightGreen
                                    )
                                }
                            }

                        }
                        item {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                items(items = uiState.value.userCardsList) {
                                    AppCard(cardData = it) {
                                        eventDispatcher(OnCardClick(it))
                                    }
                                }
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .width(65.dp)
                                            .clip(
                                                shape = RoundedCornerShape(12.dp)

                                            )
                                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                            .clickable {
                                                eventDispatcher(OnAddCardClick)
                                            },

                                        contentAlignment = Alignment.Center

                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxWidth(),
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = "add card",
                                            tint = MaterialTheme.colorScheme.primary,
                                        )
                                    }

                                }
                            }
                        }


                        val endlessPagerMultiplier = 1000
                        val originalPageCount = uiState.value.suggestedActionsList.size
                        val pageCount = endlessPagerMultiplier * originalPageCount
                        val initialPage = pageCount / 2

                        item {
                            val pagerState = rememberPagerState(initialPage = initialPage,
                                initialPageOffsetFraction = 0f,
                                pageCount = { pageCount })

                            LaunchedEffect(Unit) {
                                while (true) {
                                    delay(4000)
                                    pagerState.animateScrollToPage(
                                        (pagerState.currentPage + 1) % pageCount,
                                        animationSpec = tween(durationMillis = 1000)
                                    )
                                }
                            }
                            HorizontalPager(
                                modifier = Modifier.padding(vertical = 16.dp),
                                state = pagerState,
                                pageSpacing = 8.dp,
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                flingBehavior = PagerDefaults.flingBehavior(
                                    state = pagerState,
                                    pagerSnapDistance = PagerSnapDistance.atMost(0)
                                )
                            ) { page ->
                                val resolvedPageContentIndex = page % originalPageCount
                                val banner =
                                    uiState.value.suggestedActionsList[resolvedPageContentIndex]
                                Card(
                                    Modifier
                                        .clip(shape = RoundedCornerShape(17.8.dp))
                                        .background(color = MaterialTheme.colorScheme.onSurface)
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .border(border = BorderStroke(1.dp, lightGreen))
                                ) {
                                    BannerCard(
                                        bannerData = banner
                                    ) {}
                                }
                            }
                        }


                        // currency
                        item {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                text = stringResource(R.string.lbl_currency_rate),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .shadow(
                                        elevation = 1.dp, shape = RoundedCornerShape(8.dp)
                                    ),
                                shape = RoundedCornerShape(8.dp),
                            ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                                        .padding(12.dp)
                                ) {
                                    repeat(3) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 4.dp),

                                            ) {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(8.dp),
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.primary,
                                                text = "USD"
                                            )

                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(8.dp),
                                            ) {
                                                Text(
                                                    modifier = Modifier.padding(bottom = 2.dp),
                                                    style = TextStyle(
                                                        platformStyle = PlatformTextStyle(
                                                            includeFontPadding = false,
                                                        ),
                                                    ),
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Normal,
                                                    color = gray,
                                                    text = stringResource(R.string.lbl_buy)
                                                )
                                                Text(
                                                    style = TextStyle(
                                                        platformStyle = PlatformTextStyle(
                                                            includeFontPadding = false,
                                                        ),
                                                    ),
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    text = "12 845.00"
                                                )

                                            }
                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(8.dp),
                                            ) {
                                                Text(
                                                    modifier = Modifier.padding(bottom = 2.dp),
                                                    style = TextStyle(
                                                        platformStyle = PlatformTextStyle(
                                                            includeFontPadding = false,
                                                        ),
                                                    ),
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Normal,
                                                    color = gray,
                                                    text = stringResource(R.string.lbl_sell)
                                                )
                                                Text(
                                                    style = TextStyle(
                                                        platformStyle = PlatformTextStyle(
                                                            includeFontPadding = false,
                                                        ),
                                                    ),
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    text = "12 905.00"
                                                )

                                            }

                                        }

                                        if (it < 2) HorizontalDivider(
                                            modifier = Modifier.padding(8.dp),
                                            thickness = 1.dp,
                                            color = green,
                                        )
                                    }
                                }

                            }
                        }


                        // widget settings button
                        item {

                            OutlinedButton(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .background(Color.Transparent),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, lightGreen),
                                onClick = {}) {


                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 4.dp
                                    ),
                                    text = stringResource(R.string.widget_settings),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                        }

                    }

                }
            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTopAppBar(
        userName: String,
        onProfileClick: () -> Unit,
    ) {
        TopAppBar(
            modifier = Modifier
                .background(brush = homeGradientTopBar)
                .padding(top = 24.dp),
            windowInsets = WindowInsets(
                top = 0.dp, bottom = 0.dp
            ),
            title = {
                Text(
                    modifier = Modifier.clickable { onProfileClick() },
                    text = userName,
                    fontSize = 18.sp,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo_xazna),
                        contentDescription = "Menu Icon",
                        tint = Color.White

                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_payment_search),
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_messenger),
                        contentDescription = "Message",
                        tint = Companion.White
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notifications",
                        tint = Companion.White
                    )
                }
            },
        )
    }

}