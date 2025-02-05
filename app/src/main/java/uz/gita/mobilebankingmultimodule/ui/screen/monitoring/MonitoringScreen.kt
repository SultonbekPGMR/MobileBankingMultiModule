package uz.gita.mobilebankingmultimodule.ui.screen.monitoring

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import uz.gita.common.model.TransactionData
import uz.gita.common.util.formatBalance
import uz.gita.common.util.formatDate
import uz.gita.common.util.formatDateWithHour
import uz.gita.mobilebankingmultimodule.R
import uz.gita.mobilebankingmultimodule.ui.theme.*
import uz.gita.presenter.contract.MonitoringScreenContract.*
import uz.gita.presenter.contract.MonitoringScreenContract.Intent.*
import uz.gita.presenter.viewmodel.MonitoringScreenViewModel

/**
 * Created by Sultonbek Tulanov on 1/12/2025.
 */

class MonitoringScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ViewModel = getScreenModel<MonitoringScreenViewModel>()
        MonitoringScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            eventDispatcher = viewModel::onEventDispatcher,
        )
    }

//    @Preview
//    @Composable
//    private fun MonitoringScreenPreview() {
//        MonitoringScreenContent()
//    }

    @Composable
    private fun MonitoringScreenContent(
        uiState: State<UiState> = mutableStateOf(UiState()),
        eventDispatcher: (Intent) -> Unit = {},
    ) {

        val pagerState = rememberPagerState(initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { uiState.value.tabs.size })


        LaunchedEffect(pagerState.currentPage) {
            eventDispatcher(OnTabSelected(pagerState.currentPage))
        }
        LaunchedEffect(uiState.value.selectedTabIndex) {

            Log.d("TTTTkmkmkmkm", "MonitoringScreenContent: ")
            pagerState.animateScrollToPage(uiState.value.selectedTabIndex)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()

                .background(color = MaterialTheme.colorScheme.secondary),
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.monitoring),
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )
                Icon(
                    modifier = Modifier.clickable {},
                    contentDescription = null,
                    painter = painterResource(R.drawable.ic_rocket_selected),
                    tint = MaterialTheme.colorScheme.primary
                )

            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .clickable { eventDispatcher(OnBtnIncomeClick) }, border = BorderStroke(
                        width = 1.dp,
                        color = if (uiState.value.btnIncome) lightGreen else Color.Transparent
                    ), shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surfaceContainer)
                            .padding(start = 12.dp, bottom = 12.dp)

                    ) {

                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                contentDescription = null,
                                painter = painterResource(R.drawable.ic_rocket_selected),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = stringResource(R.string.income),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = gray,
                                textAlign = TextAlign.Start
                            )

                        }

                        Text(
                            text = formatBalance(uiState.value.income),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Start
                        )

                    }

                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .clickable { eventDispatcher(OnBtnExpenseClick) }, border = BorderStroke(
                        width = 1.dp,
                        color = if (uiState.value.btnExpense) red else Color.Transparent
                    ),

                    shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surfaceContainer)
                            .padding(start = 12.dp, bottom = 12.dp)

                    ) {

                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                contentDescription = null,
                                painter = painterResource(R.drawable.ic_rocket_selected),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = stringResource(R.string.outcome),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = gray,
                                textAlign = TextAlign.Start
                            )

                        }

                        Text(
                            text = formatBalance(uiState.value.expense),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Start
                        )

                    }

                }

            }

            LazyRow(
                modifier = Modifier.padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                uiState.value.tabs.forEachIndexed { index, tab ->
                    item {
                        Box(modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(
                                color = if (uiState.value.selectedTabIndex == index) lightGreen else green2
                            )
                            .clickable {
                                eventDispatcher(OnTabSelected(index))
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)) {
                            Text(
                                text = stringResource(tab.titleId),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (index == uiState.value.selectedTabIndex) MaterialTheme.colorScheme.primary else gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }




            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),

                ) { page ->
                when (page) {
                    0 -> {

                        PageAll(uiState.value.transactionItems.collectAsLazyPagingItems()) {

                        }

                    }

                    1 -> {

                    }
                }
            }


        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun PageAll(
        transactionsList: LazyPagingItems<TransactionData>,
        onItemClick: (Int) -> Unit,
    ) {
        // You can check for load state if you want to show a progress indicator
        val refreshState = transactionsList.loadState.refresh
        val appendState = transactionsList.loadState.append

        when (refreshState) {
            is Loading -> {
                // Show a full-screen loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                return
            }

            is Error -> {
                // Optionally, show an error message with a retry button
                Log.d("KMKMKMKMKMKMKM", "PageAll: ${refreshState.error}")
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text("Error loading transactions")
                }
                return
            }

            else -> { /* Not loading */
            }
        }

        if (transactionsList.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text("No transactions available")
            }
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(horizontal = 20.dp)
        ) {

            var lastTransaction: TransactionData? = null
            for (index in 0 until transactionsList.itemCount) {
                val transactionData = transactionsList.peek(index)
                if (formatDate(lastTransaction?.time ?: 0L) !=
                    formatDate(transactionData?.time ?: 0L)
                ) {
                    stickyHeader {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(
                                text = formatDate(transactionData?.time ?: 0L),
                                color = lightGreen,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }

                item {
                    transactionsList.get(index)?.let {
                        MonitoringItem(
                            transactionData = it,
                            isLastItem = index == transactionsList.itemCount - 1
                        ) { }
                    }
                }

                lastTransaction = transactionData
            }


        if (appendState is Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}


@Composable
private fun MonitoringItem(
    transactionData: TransactionData,
    isLastItem: Boolean,
    onItemClick: (TransactionData) -> Unit,
) {
    val isIncome = transactionData.type == "income"
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable { onItemClick(transactionData) },
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Row for From/To and Date
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = if (isIncome) transactionData.from else transactionData.to,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.weight(1f),
                text = formatDateWithHour(transactionData.time),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.End
            )
        }

        // Row for To/From and Amount
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = if (isIncome) transactionData.to else transactionData.from,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.weight(1f),
                text = if (isIncome) {
                    "+" + formatBalance(transactionData.amount.toDouble())
                } else {
                    "-" + formatBalance(transactionData.amount.toDouble())
                },
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isIncome) lightGreen else red,
                textAlign = TextAlign.End
            )
        }

        // Divider if not the last item
        if (!isLastItem) {
            HorizontalDivider(
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp),
                color = gray
            )
        }
    }
}


}

