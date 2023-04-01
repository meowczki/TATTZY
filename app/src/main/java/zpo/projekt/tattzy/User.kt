package zpo.projekt.tattzy

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val username: String? = null, val email: String? = null,val name: String? = null,val phone: String? = null,val birthday: String? = null,val clientType: ClientType? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
enum class ClientType {
   CLIENT, ARTIST
}