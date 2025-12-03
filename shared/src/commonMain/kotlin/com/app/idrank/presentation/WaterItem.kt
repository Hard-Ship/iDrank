package com.app.idrank.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.idrank.data.WaterEntity
import com.app.idrank.fromMillisToDate
import idrank.shared.generated.resources.Res
import idrank.shared.generated.resources.round_delete_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun WaterItem(
    modifier: Modifier,
    water: WaterEntity,
    onDelete: (Long) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column {
                Text("${water.amountMl} ml")
                Spacer(Modifier.height(8.dp))
                Text(
                    water.timestamp.fromMillisToDate("dd/MM/yyyy HH:mm:ss"),
                    style = MaterialTheme.typography.bodySmall,
                    color = lowEmphasisColor
                )
            }

            IconButton(onClick = {
                onDelete(water.id)
            }) {
                Icon(
                    painterResource(Res.drawable.round_delete_24),
                    "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }

        }
    }
}