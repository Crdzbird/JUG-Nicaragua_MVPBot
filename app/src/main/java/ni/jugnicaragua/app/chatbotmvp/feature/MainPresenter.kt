package ni.jugnicaragua.app.chatbotmvp.feature

import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import com.google.firebase.database.DatabaseReference
import ni.jugnicaragua.app.chatbotmvp.entity.ChatMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.Job
import ni.jugnicaragua.app.chatbotmvp.feature.MainContract

class MainPresenter(val aiDataAIService: AIDataService,
                    val ref: DatabaseReference) : MainContract.Presenter {

    val aiRequest = AIRequest()

    val job = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun sendMessage(message: String) {
        val chatMessage = ChatMessage(message, "user")
        ref.child("chat").push().setValue(chatMessage)
        aiRequest.setQuery(message)


        val response: Deferred<AIResponse>? = coroutineScope.async {
            aiDataAIService.request(aiRequest)
        }

        coroutineScope.launch {
            println(response?.toString())
            sendMessageToServer(response?.await())
        }

    }

    private fun sendMessageToServer(response: AIResponse?) {
        val result = response?.result
        val reply = result?.fulfillment?.speech
        val chatMessage = ChatMessage(reply, "bot")
        ref.child("chat").push().setValue(chatMessage)

    }

    override fun onDestroy() {
        job.cancel()
    }
}