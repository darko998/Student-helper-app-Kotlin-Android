package rs.raf.projekat2.darko_dimitrijevic_rn9418.data.models.note

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val archived: Boolean
)