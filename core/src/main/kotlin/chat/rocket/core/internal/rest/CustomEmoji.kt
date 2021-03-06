package chat.rocket.core.internal.rest

import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.RestResult
import chat.rocket.core.model.CustomEmoji
import com.squareup.moshi.Types
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext

suspend fun RocketChatClient.getCustomEmojis(): List<CustomEmoji> = withContext(CommonPool) {
    val url = requestUrl(restUrl, "emoji-custom").build()

    val request = requestBuilderForAuthenticatedMethods(url).get().build()

    val type = Types.newParameterizedType(
        RestResult::class.java,
        Types.newParameterizedType(List::class.java, CustomEmoji::class.java)
    )
    return@withContext handleRestCall<RestResult<List<CustomEmoji>>>(request, type).result()
}
