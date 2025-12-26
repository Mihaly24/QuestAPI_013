package com.example.questapi.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questapi.R
import com.example.questapi.uicontroller.route.DestinasiDetail
import com.example.questapi.viewmodel.DetailViewModel
import com.example.questapi.viewmodel.StatusUIDetail
import com.example.questapi.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val id = (viewModel.statusUIDetail as? StatusUIDetail.Success)?.satusiswa?.id
                    if (id != null) navigateToEditItem(id)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_siswa)
                )
            }
        },
    ) { innerPadding ->
        BodyDetailSiswa(
            statusUIDetail = viewModel.statusUIDetail,
            retryAction = viewModel::getSatuSiswa,
            onDeleteClick = {
                viewModel.hapusSatuSiswa()
                navigateBack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailSiswa(
    statusUIDetail: StatusUIDetail,
    retryAction: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {