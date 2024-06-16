package com.example.myscreen.articles



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.R

@Composable

fun IntroBudgeting() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "Introduction to Budget",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,

                )

        }

        item {
            Image(
                painter = painterResource(id = R.drawable.introbudget),
                contentDescription = "Image Description",
                modifier = Modifier

                    .size(400.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text =
                        "What is a Budget",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,

                )

        }
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "A Budget is a statement that gives the details of ‘where money comes from’ and ‘where the money goes to’.\n" +
                        "\n" +
                        "In technical terms, the money that ‘comes in’ is referred to by terms such as income, revenue, receipts, etc., and the money that ‘goes out’ is referred to as expenses, expenditure, spending, etc. \n" +
                        "\n" +
                        "A Budget has to have at least three details: \n" +
                        "\n" +
                        "It has to be for an entity, and for a defined purpose: an individual, an event, an organisation, a household, a business, a government, etc.\n" +
                        "It is for a defined time period: generally, a budget is drawn up for a year, but this can vary. For example - in the case of events or projects, budget can be for the duration of those events/projects.\n" +
                        "It gives details of receipts and expenditure: it lists all the sources from where money comes, and all the destinations where the money will go. \n" +
                        "The following figures are a few examples of budgets:\n" +
                        "\n",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,

                )

        }
        item {
            Image(
                painter = painterResource(id = R.drawable.fig1),
                contentDescription = "Fig 1",
                modifier = Modifier

                    .size(400.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "Figure 2 provides a sample of annual budget for a household. In this particular year, this household expects to earn money from four sources, namely salary, business, interest on the money in a bank account, and rent from a house they own. The column under expenditure details the expenses the family expects to incur. The figures noted in second last row show the total of income and expenditure of the household. The last row shows that at the end of the year, the family expects to have a surplus of Rs 4,10,000. For families that have expenditure higher than income, this amount will be negative (a deficit), which means they have either used up their savings from the previous years, or they borrowed money that needs to be repaid. ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,

                )

        }
        item {
            Image(
                painter = painterResource(id = R.drawable.fig2),
                contentDescription = "Fig 2",
                modifier = Modifier

                    .size(400.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "In this year, the expenditure is Rs 13,00,000 crore, while the receipt is Rs 10,50,000 crore. This results in a deficit of Rs 2,50,000 crore. The government finances this deficit by borrowing, partly from the domestic market, and partly through foreign borrowings. The government will need to repay this borrowed amount. One entry under expenditure, named ‘interest payment’, refers to the payment of interest on government borrowings in the past. Most government budgets are in deficit, but they can also have a surplus, i.e., the receipts can exceed the expenditure.\n" +
                        "\n" +
                        "It should be noted that these are merely samples, and actual budgets are much more complex. This is especially the case for businesses and governments. Further details about government budgets are given in the following sections. ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,

                )

        }

    }
}


data class SectionDataBudget(val title: String, val content: String)

val sectionsBudget = listOf(
    SectionDataBudget("1." +
            "What is a Budget", "A Budget is a statement that gives the details of ‘where money comes from’ and ‘where the money goes to’.\n" +
            "\n" +
            "In technical terms, the money that ‘comes in’ is referred to by terms such as income, revenue, receipts, etc., and the money that ‘goes out’ is referred to as expenses, expenditure, spending, etc. \n" +
            "\n" +
            "A Budget has to have at least three details: \n" +
            "\n" +
            "It has to be for an entity, and for a defined purpose: an individual, an event, an organisation, a household, a business, a government, etc.\n" +
            "It is for a defined time period: generally, a budget is drawn up for a year, but this can vary. For example - in the case of events or projects, budget can be for the duration of those events/projects.\n" +
            "It gives details of receipts and expenditure: it lists all the sources from where money comes, and all the destinations where the money will go. \n" +
            "The following figures are a few examples of budgets:\n" +
            "\n"),
    SectionDataBudget("Savvy Helps You Reach Your Dreams", "Close your eyes and picture your biggest dreams – maybe it's a new toy, a fun trip, or even saving up for college one day. Whatever it is, Savvy can make it happen! With Savvy by your side, you can turn your dreams into reality."),
    SectionDataBudget("Savvy Teaches You Patience", "Saving with Savvy is like planting a magical garden. Each day, you add a little bit of money, just like planting tiny seeds. With patience and care, your savings will grow into something big and amazing!"),
    SectionDataBudget("Savvy Makes You Feel Proud", "Imagine looking at Savvy and seeing how much you've saved. It fills your heart with pride! You'll feel proud of yourself for being smart with your money and for having the power to make your dreams come true.")
    ,SectionDataBudget("How much you should save?","It’s generally considered a good idea to have at least three months worth of living expenses saved somewhere safe and accessible for emergencies.\n" +
            "\n" +
            "However, how much you save depends on what you can afford.\n" +
            "\n" +
            "Some popular ways to budget are the 50/30/20 method and the 80/20 method. These suggest keeping 20% of your income as savings and using the rest to pay for other costs each month.")

)