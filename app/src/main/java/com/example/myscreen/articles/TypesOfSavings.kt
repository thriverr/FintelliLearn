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

fun TypesOfSavings() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
            Text(
                text = "Types Of Savings Accounts",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Purple40,

                )

        }





        item {
            Image(
                painter = painterResource(id = R.drawable.typesofsaving),
                contentDescription = "Image Description",
                modifier = Modifier

                    .size(400.dp)
            )
        }

        items(sections) { section ->

            Box(
                modifier = Modifier,
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
    SectionData("Types of Savings Accounts","Distinguishing between different savings accounts means looking at their features, where you can open them and what they’re designed to do.\n" +
            "\n" +
            "As you compare different savings accounts, it can help to ask these kinds of questions:\n" +
            "\n" +
            "Is this account designed for any specific purpose or goal?\n" +
            "How much interest does this account earn?\n" +
            "Are there minimum deposit requirements or minimum balance requirements to meet?\n" +
            "Does the bank charge any fees for this type of savings account?\n" +
            "Are there any tax benefits or advantages associated with this savings account?\n" +
            "How accessible is the money in the account?\n" +
            "Will I pay any penalties for withdrawing money from the account?\n" +
            "Doing this kind of research can help you decide which types of savings accounts to have. From there, you can choose where to open them and how to fund them."),
    SectionData("1. Traditional or Regular Savings Account","Good for: People who need to save money for the short or long term and aren’t as concerned about getting the best interest rate, expressed as the annual percentage yield (APY).\n" +
            "\n" +
            "Traditional savings accounts are what you may immediately think of when you consider where to save. These are the savings accounts you typically find at traditional banks.\n" +
            "\n" +
            "These types of savings accounts generally allow you to earn interest on your money, although they usually pay lower rates than other savings products. Many banks and credit unions allow you to open a regular savings account with a low minimum deposit.\n" +
            "\n" +
            "Traditional savings accounts typically allow you to make three to five monthly withdrawals (not including ATM withdrawals or in-person withdrawals at a branch) before incurring a small penalty. Banks may allow you to manage your account online, via mobile banking, by phone or at a branch.\n" +
            "\n" +
            "Banks in India work under the supervision of the RBI and your deposits are insured for up to INR 5 lakh per depositor, per account ownership category, in the event of a bank failure. \n" +
            "\n" +
            "Know the interest rates of all Savings Bank Accounts in India.\n" +
            "\n" +
            "Pros\n" +
            "\n" +
            "It’s usually easy to open a regular savings account at a branch, and some banks allow you to do so online.\n" +
            "You can earn interest on your savings to grow your money.\n" +
            "You can visit a branch if you need help or want to deposit cash.\n" +
            "Cons\n" +
            "The interest rates are usually low compared to other savings options.\n" +
            "Monthly maintenance fees may cancel out interest earnings.\n" +
            "Additional fees may apply for excess withdrawals."),
    SectionData("2. High-Yield Savings Account","Good for: People who want to earn a more competitive rate on savings while minimizing fees.\n" +
            "\n" +
            "High-yield savings accounts—typically found at online banks, neobanks—are savings accounts that offer a higher APY compared to regular savings accounts. This is one of the best types of savings accounts to maximize your money’s growth.\n" +
            "\n" +
            "Online banks often offer different types of high yield savings accounts to attract savers who want to earn a better interest rate than what is found at brick-and-mortar banks and credit unions. This type of savings account may be appealing if you’re comfortable managing your account via website or mobile banking versus visiting a branch.\n" +
            "\n" +
            "High-yield savings accounts are also RBI insured, just like traditional savings accounts. In addition to offering better rates, online banks tend to charge fewer or lower fees, including monthly maintenance or excess withdrawal fees.\n" +
            "\n" +
            "Pros\n" +
            "You could earn a much higher interest rate compared to traditional savings accounts.\n" +
            "Online banks typically have lower minimum deposit requirements to open an account.\n" +
            "You’re less likely to be charged a monthly fee at an online bank.\n" +
            "Cons\n" +
            "No branch banking access means you can’t deposit cash directly into your account at a branch.\n" +
            "Transferring money between an online savings account and accounts at another bank can take up to a few days to process.\n" +
            "You may or may not have access to your money via ATM, depending on the bank.\n" +
            "These accounts aren’t always covered by FDIC insurance."),
    SectionData("3. Specialty Savings Account","Good for: People who want accounts tailored to specific savings goals.\n" +
            "\n" +
            "Specialty savings accounts are designed to help you reach specific savings goals, rather than being a catch-all for money you don’t plan to spend. And in some cases, they can be intended for a specific type of person, rather than a savings goal. Many banks in India are popularizing the concept. \n" +
            "\n" +
            "For example, there are different types of savings accounts for minors. Three types of savings accounts you might set up on behalf of a child or teen include:\n" +
            "\n" +
            "Kids’ savings accounts\n" +
            "Custodial savings accounts\n" +
            "Student savings accounts\n" +
            "You can also set up different types of education savings accounts. Then there are different types of retirement savings accounts you could set up for yourself.\n" +
            "\n" +
            "You should be able to find most of these accounts at banks, credit unions, brokerages or investment companies. In the case of a Health Savings Account, you’d only have access to one of those if you have a high deductible health plan.\n" +
            "\n" +
            "Opening one or more specialty savings accounts may make sense if you have a singular purpose for saving money. Just keep in mind that there may be restrictions on when and how you can withdraw those funds later.\n" +
            "\n" +
            "Pros\n" +
            "They can help you save money for a variety of specific financial goals.\n" +
            "Specialty accounts can earn interest to help you grow your money, just like other savings accounts.\n" +
            "You may pay low or no monthly maintenance fees depending on the account.\n" +
            "Cons\n" +
            "The interest rates you earn for child savings accounts, student accounts or Christmas Club accounts may be lower than high-yield or even regular savings accounts.\n" +
            "Specialty accounts may have restrictions on who can open them.\n" +
            "Learn about the Best Savings Accounts in India.")

)