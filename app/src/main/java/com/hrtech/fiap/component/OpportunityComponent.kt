package com.hrtech.fiap.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hrtech.fiap.R
import com.hrtech.fiap.model.JobOpportunity

@Composable
fun JobOpportunityComponent(item: JobOpportunity, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(bottom = 16.dp)
            .shadow(
                elevation = 20.dp,
                clip = true
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 16.dp, horizontal = 16.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_opportunity_avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .size(50.dp)
                )
                Column( modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(
                        text = item.title,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text(item.company.name)
                }
            }
        }
    }
}