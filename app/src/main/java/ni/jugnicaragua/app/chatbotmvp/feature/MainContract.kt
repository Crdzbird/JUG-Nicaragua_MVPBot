package ni.jugnicaragua.app.chatbotmvp.feature

interface MainContract {

    interface View{

    }

    interface Presenter{
        fun sendMessage(message: String)
        fun onDestroy()
    }

}