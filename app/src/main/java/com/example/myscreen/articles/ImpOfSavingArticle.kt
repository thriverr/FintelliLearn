

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.R
import com.example.myscreen.ui.theme.Pink80
import com.example.myscreen.ui.theme.Purple40

@Composable

fun ImpOfSavingArticle() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Pink80),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "Meet Savvy the Saver:",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

            )
            Text(
                text = "Your Magical Piggy Bank",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

                )
        }

        item {
            Text(
                text = "Magical Piggy Bank",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

            )
        }

        item {
            Text(
                text = "Companion!\n",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

            )
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.piggy),
                contentDescription = "Image Description",
                modifier = Modifier

                    .size(400.dp)
            )
        }

        items(sections) { section ->
            val backgroundColor = Pink80
            Box(
                modifier = Modifier.background(backgroundColor),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(
                        text = section.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(text = section.content)
                }
            }
        }
    }
}


data class SectionData(val title: String, val content: String)

val sections = listOf(
    SectionData("Savvy Keeps You Safe", "Think of Savvy as your trusty shield against unexpected troubles. If you ever need money for something important, like fixing a broken toy or visiting the doctor, Savvy will be there to help you out."),
    SectionData("Savvy Helps You Reach Your Dreams", "Close your eyes and picture your biggest dreams – maybe it's a new toy, a fun trip, or even saving up for college one day. Whatever it is, Savvy can make it happen! With Savvy by your side, you can turn your dreams into reality."),
    SectionData("Savvy Teaches You Patience", "Saving with Savvy is like planting a magical garden. Each day, you add a little bit of money, just like planting tiny seeds. With patience and care, your savings will grow into something big and amazing!"),
    SectionData("Savvy Makes You Feel Proud", "Imagine looking at Savvy and seeing how much you've saved. It fills your heart with pride! You'll feel proud of yourself for being smart with your money and for having the power to make your dreams come true.")
    ,SectionData("How much you should save?","It’s generally considered a good idea to have at least three months worth of living expenses saved somewhere safe and accessible for emergencies.\n" +
            "\n" +
            "However, how much you save depends on what you can afford.\n" +
            "\n" +
            "Some popular ways to budget are the 50/30/20 method and the 80/20 method. These suggest keeping 20% of your income as savings and using the rest to pay for other costs each month.")

)