package com.example.myscreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.Purple40
import com.example.myscreen.ui.theme.bluee
import com.example.myscreen.ui.theme.lightgrey
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


data class Post(var id: String = "", var title: String = "", val content: String = "",
                val userFirstName: String = "", var score: Int = 0,
                var replies: List<Reply> = emptyList(),
                val ans: String = ""
)
data class Reply(
    val userFirstName: String,
    val ans: String
)


@Composable
fun AddPostScreen(navController: NavController, db: FirebaseFirestore,  currentUser: FirebaseUser) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var username by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // Fetch current user's first name from Firestore
        val userDocRef = db.collection("users").document(currentUser.uid)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    username = document.getString("username")
                } else {
                    // Handle the case where the document doesn't exist
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }



    }
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(56.dp))
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = content, onValueChange = { content = it }, label = { Text("Content") })
        Spacer(modifier = Modifier.height(16.dp))
        androidx.compose.material3.Button(onClick = {
            username?.let { username ->
                val post = hashMapOf(
                    "title" to title,
                    "content" to content,
                    "userFirstName" to username,
                    "score" to 0
                )
                db.collection("posts").add(post)
                    .addOnSuccessListener {
                        navController.navigate(Routes.CommunityForum)
                    }
            }
        }) {
            Text("Add Post")
        }
    }
}

@Composable

fun CommunityForum(currentUser: FirebaseUser,db: FirebaseFirestore,navController: NavController){
    var posts by remember { mutableStateOf(listOf<Post>()) }
    var username by remember { mutableStateOf<String?>(null) }
    var filteredPosts by remember { mutableStateOf(listOf<Post>()) }
    var searchQuery by remember { mutableStateOf("") }
    var thumbsUpClickedMap by remember { mutableStateOf<Map<String, Boolean>>(emptyMap()) }


    LaunchedEffect(Unit) {
        // Fetch posts
        db.collection("posts").get().addOnSuccessListener { result ->
            val postList = result.map { document ->
                val post = Post(
                    id = document.id,
                    title = document.getString("title") ?: "",
                    content = document.getString("content") ?: "",
                    userFirstName = document.getString("userFirstName") ?: "",
                    score = document.getLong("score")?.toInt() ?: 0,

                )

                // Fetch replies as pairs of userFirstName and ans
                val replies = (document.get("replies") as? List<Map<String, String>>)?.map {
                    Reply(it["userFirstName"] ?: "", it["ans"] ?: "")
                } ?: emptyList()
                post.copy(replies = replies)
            }

            posts = postList
            filteredPosts = postList
            thumbsUpClickedMap = postList.associateBy({ it.id }, { false })

        }

        val userDocRef = db.collection("users").document(currentUser.uid)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    username = document.getString("username")
                } else {
                    // Handle the case where the document doesn't exist
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    LaunchedEffect(searchQuery) {
        filteredPosts = if (searchQuery.isEmpty()) {
            posts
        } else {
            posts.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.content.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    fun addReply(postId: String, replyContent: String) {
        val postRef = db.collection("posts").document(postId)
        db.runTransaction { transaction ->
            val snapshot = transaction.get(postRef)
            val replies = snapshot.get("replies") as? List<Map<String,  String>> ?: emptyList()
            val currentUserFirstName = username ?: ""
            val updatedReplies = replies + mapOf(
                "userFirstName" to (currentUserFirstName ?: ""),
                "ans" to (replyContent ?: "")
            )
            transaction.update(postRef, "replies", updatedReplies)
        }.addOnSuccessListener {
            // Handle success
        }.addOnFailureListener { e ->
            // Handle failure
            Log.e("Forum", "Error adding reply: ${e.message}")
        }
    }

    fun upvotePost(postId: String) {
        val currentUpvoteState = thumbsUpClickedMap[postId] ?: false
        val newScore = posts.find { it.id == postId }?.let { post ->
            if (currentUpvoteState) post.score - 1 else post.score + 1
        } ?: return

        val postRef = db.collection("posts").document(postId)
        postRef.update("score", newScore)
            .addOnSuccessListener {
                // Update local state immediately
                posts = posts.map { if (it.id == postId) it.copy(score = newScore) else it }
                filteredPosts = filteredPosts.map { if (it.id == postId) it.copy(score = newScore) else it }
                thumbsUpClickedMap = thumbsUpClickedMap.toMutableMap().apply {
                    this[postId] = !currentUpvoteState
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
                Log.e("Forum", "Error updating score: ${e.message}")
            }
    }

    fun deletePost(postId: String) {
        db.collection("posts").document(postId).delete()
            .addOnSuccessListener {
                // Refresh the post list after deletion
                db.collection("posts").get().addOnSuccessListener { result ->
                    val updatedPosts = result.map { document ->
                        val post = Post(
                            id = document.id,
                            title = document.getString("title") ?: "",
                            content = document.getString("content") ?: "",
                            userFirstName = document.getString("userFirstName") ?: "",
                            score = document.getLong("score")?.toInt() ?: 0,
                            replies = emptyList()
                        )

                        // Fetch replies as pairs of userFirstName and ans
                        val replies = (document.get("replies") as? List<Map<String, String>>)?.map {
                            Reply(it["userFirstName"] ?: "", it["ans"] ?: "")
                        } ?: emptyList()
                        post.copy(replies = replies)}

                    posts = updatedPosts
                    filteredPosts = updatedPosts
                    thumbsUpClickedMap = updatedPosts.associateBy({ it.id }, { false })
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
                Log.e("Forum", "Error deleting post: ${e.message}")
            }
    }

    Column(Modifier.fillMaxSize().background(lightgrey)) {

        Spacer(modifier = Modifier.height(56.dp))
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search", color = Color.Black, fontSize = 18.sp,
                fontWeight = FontWeight.Bold) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth().background(Color.White)
                .padding(16.dp), shape = RoundedCornerShape(100.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        // Row of Buttons
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("Budget", "Saving", "Retirement Planning", "Policy","Investment")) { keyword ->
                androidx.compose.material3.Button(onClick = { searchQuery = keyword }) {
                    Text(keyword, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif)
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp), colors = CardDefaults.cardColors(
                containerColor = Color.White // Set the background color of the card here
            )
        ) {
            Column(
                modifier = Modifier.padding(6.dp)
            ) {
                // First Row: Circular button and oval box
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Circular button with the first letter of the first name
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(color = Purple40, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = username?.first()?.toUpperCase().toString(),
                            color = Color.White,
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    // Oval-shaped box containing a Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)

                    ) {
                        androidx.compose.material3.Button(
                            onClick = { navController.navigate("addPost") },
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(lightgrey),
                            shape = CircleShape,
                        ) {
                            Text(
                                "Do you want to ask or share?",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                // Second Row: Action buttons
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material3.Button(
                        onClick = { navController.navigate("addPost") },
                        colors = ButtonDefaults.buttonColors(Purple40),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Ask", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    androidx.compose.material3.Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(Purple40),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Post", color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        LazyColumn(modifier = Modifier.weight(1f).padding(16.dp)) {
            items(filteredPosts) { post ->
                PostItem(
                    post = post,
                    onReply = { replyContent -> addReply(post.id, replyContent) },
                    onUpvote = { upvotePost(post.id) },
                    onDelete = { deletePost(post.id) },
                    currentUserFirstName = username?:"U",

                    isUpvoted = thumbsUpClickedMap[post.id] ?: false
                )
            }
        }
    }
}

@Composable
fun PostItem(
    post: Post,
    onReply: (String) -> Unit,
    onUpvote: () -> Unit,
    onDelete: () -> Unit,
    currentUserFirstName: String?,
    isUpvoted: Boolean
) {
    var showReplyInput by remember { mutableStateOf(false) }
    var replyContent by remember { mutableStateOf("") }
    var showReplies by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "${post.userFirstName}: ${post.title}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.content)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Button(onClick = onUpvote) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Upvote",
                        tint = if (isUpvoted) bluee else Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = ":${post.score}", color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(40.dp))
                if (currentUserFirstName == post.userFirstName) {
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                androidx.compose.material3.Button(onClick = { showReplyInput = true }) {
                    Text("Reply")
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { showReplies = !showReplies }) {
                    Icon(
                        imageVector = if (showReplies) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                        contentDescription = if (showReplies) "Hide Replies" else "Show Replies"
                    )
                }
            }

            if (showReplyInput) {
                Column {
                    TextField(
                        value = replyContent,
                        onValueChange = { replyContent = it },
                        label = { Text("Reply") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row {
                        androidx.compose.material3.Button(onClick = {
                            if (replyContent.isNotEmpty()) {

                                onReply(replyContent)
                                showReplyInput = false
                                replyContent = ""
                            }
                        }) {
                            Text("Submit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Button(onClick = {
                            showReplyInput = false
                            replyContent = ""
                        }) {
                            Text("Cancel")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            if (showReplies) {
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    post.replies.forEach { reply ->
                        // Display user's first name and reply content
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(Color.LightGray)
                                .padding(8.dp)
                        ) {
                            Text(text = reply.userFirstName, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = reply.ans) // Change reply.content to reply.ans
                        }
                    }
                }
            }

        }}}