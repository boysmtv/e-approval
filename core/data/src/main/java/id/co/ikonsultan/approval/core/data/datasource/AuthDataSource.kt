package id.co.ikonsultan.approval.core.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import id.co.ikonsultan.approval.core.data.dto.UserDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun login(email: String, password: String): Pair<String, UserDto>? {

        val query = firestore.collection("users")
            .whereEqualTo("email", email)
            .get()
            .await()

        val doc = query.documents.firstOrNull() ?: return null
        val dto = doc.toObject(UserDto::class.java) ?: return null

        return doc.id to dto
    }
}