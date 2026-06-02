package data.data_source.dto.CoinDetailDTO

data class Links(
    val announcement_url: List<Any>,
    val bitcointalk_thread_identifier: Any,
    val blockchain_site: List<String>,
    val chat_url: List<Any>,
    val facebook_username: String,
    val homepage: List<String>,
    val official_forum_url: List<String>,
    val repos_url: ReposUrl,
    val snapshot_url: Any,
    val subreddit_url: String,
    val telegram_channel_identifier: String,
    val twitter_screen_name: String,
    val whitepaper: String
)