package com.example.myscreen.articles



import androidx.compose.foundation.Image
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
import com.example.myscreen.ui.theme.Purple40

@Composable

fun SavingsAccount() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "How To Open A Savings Account For A Child",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

                )

        }



        item {
            Image(
                painter = painterResource(id = R.drawable.savingsac),
                contentDescription = "Image Description",
                modifier = Modifier

                    .size(400.dp)
            )
        }

        items(section) { it ->

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(text = it.content)
                }
            }
        }
    }
}


data class SectionDatas(val title: String, val content: String)

val section = listOf(
    SectionDatas("What Is a Kids’ Savings Account?", "A kids’ savings account is designed for kids under age 18: The child and a parent or guardian act as joint account holders. Unlike regular savings accounts, children’s savings accounts often come with additional perks such as:\n" +
            "\n" +
            "No monthly account fees\n" +
            "Low to no opening balance requirements\n" +
            "Online learning tools to boost a child’s financial education\n" +
            "Mobile apps so kids can easily view their accounts\n" +
            "Remember that perks vary by bank, so it pays to ask about the specific kid-centric features offered."),
    SectionDatas("Types of Kids Bank Accounts", "There are several types of kids’ bank accounts, each with differing features depending on your child’s needs. Here are a few bank account options for kids:\n" +
            "\n" +
            "Custodial account. Custodial accounts are a type of bank or investment account you can open for your child. Any money put into the account is considered a gift and owned by your child, but you are the account custodian until your child is an adult. The funds must be used for the benefit of the minor, and ownership is transferred to the minor at the age of majority according to the minor’s state of residence.\n" +
            "\n" +
            "Joint account. Joint bank accounts allow you to open a checking or savings account with your child as a joint account owner. This account has fewer restrictions than a custodial account, as your child and you both have equal ownership over the funds within the account. A majority of teen checking accounts and other kids’ bank accounts are set up as joint accounts.\n" +
            "\n" +
            "Educational account. Education savings accounts are a type of savings account that may offer tax benefits when saving for college or other educational expenses. The most popular education account is a 529 account, which offers federal and state tax benefits, investment options and the ability to roll unused funds into a Roth IRA (limitations apply)."),
    SectionDatas("Should You Choose a Kids’ Savings Account or a Custodial Account?", "There are typically two types of accounts you can open for your child: a savings account or a custodial account, and the difference is important. If you open a savings account, you and your child will have joint ownership of the account, and your child will be able to access the funds (with you, the parent, being able to monitor account activity).\n" +
            "\n" +
            "If you open a custodial account, also referred to as a Uniform Gifts to Minors Act (UGMA) or Uniform Transfers to Minors Act (UTMA) account, money in the account is treated as a gifted asset your child fully owns; funds cannot be accessed directly by the child until the child turns 18 (or the age of majority in their state of residence). Yet using this type of account may complicate your taxes. So unless you have a particular reason for choosing a custodial account, you may want to go with a typical savings account."),
    SectionDatas("What Features Should One Look for in a Kids’ Savings Account?",
        "Along with a decent APY, the best children’s savings accounts offer unique features that help make saving fun for kids. For example:\n" +
                "\n" +
                "Automatic savings plans. Banks may offer an automatic savings deposit plan that lets you transfer a certain amount of money into a child’s account each month.\n" +
                "Financial education. Some banks offer interactive apps and websites with financial literacy tools that help kids learn about money. For example, Bank of America’s Better Money Habits website helps parents and kids boost their financial knowledge.\n" +
                "Mobile apps/online banking. Not all banking apps have the same features, but most let you deposit checks and move money between accounts. When choosing a savings account for your kid, be sure to find out what features the bank’s app has—or doesn’t have.\n" +
                "Savings goals. The Capital One Kids Savings Account lets you create multiple accounts and track different savings goals. For example, you could help your child set up separate accounts for summer camp, an Xbox or a new bike.\n" +
                "ATM card. Some banks offer ATM cards your child can use to withdraw cash directly from their savings accounts at ATMs.\n" +
                "FDIC insurance. Your child’s savings account should be FDIC insured, just like any other bank account. Double-check the bank website to ensure it’s an FDIC-insured institution.\n" +
                "These are just a few features of kids’ savings accounts that you may want to look for or ask about. Keep in mind that some banks have more offerings than others.\n" +
                "\n" +
                "Pro Tip\n")
)