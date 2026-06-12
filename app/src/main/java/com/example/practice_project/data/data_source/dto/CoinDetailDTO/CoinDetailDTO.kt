package com.example.practice_project.data.data_source.dto.CoinDetailDTO

import com.example.practice_project.domain.model.CoinDetail

data class CoinDetailDTO(
    val additional_notices: List<Any?>,
    val asset_platform_id: Any,
    val block_time_in_minutes: Double,
    val categories: List<String>,
    val community_data: CommunityData,
    val country_origin: String,
    val description: Description?,
    val detail_platforms: DetailPlatforms,
    val developer_data: DeveloperData,
    val genesis_date: String,
    val hashing_algorithm: String,
    val id: String?,
    val image: Image?,
    val last_updated: String,
    val links: Links,
    val localization: Localization,
    val market_cap_rank: Double?,
    val market_cap_rank_with_rehypothecated: Double?,
    val market_data: MarketData?,
    val name: String?,
    val platforms: Platforms?,
    val preview_listing: Boolean?,
    val public_notice: Any?,
    val sentiment_votes_down_percentage: Double?,
    val sentiment_votes_up_percentage: Double?,
    val status_updates: List<Any?>?,
    val symbol: String?,
    val tickers: List<Ticker>?,
    val watchlist_portfolio_users: Double?,
    val web_slug: String?
)
{
    fun toCoinDetail(): CoinDetail {
        return CoinDetail(
            id = id ?: "",
            name = name ?: "",
            image = image?.large ?: "",
            market_cap = market_data?.market_cap?.usd ?: 0.0,
            price = market_data?.current_price?.usd ?: 0.0,
            price_percentage_change = market_data?.price_change_percentage_24h_in_currency?.usd ?: 0.0,
            low_price = market_data?.low_24h?.usd ?: 0.0,
            high_price = market_data?.high_24h?.usd ?: 0.0,
            description = description?.en ?: "",
        )
    }
}

