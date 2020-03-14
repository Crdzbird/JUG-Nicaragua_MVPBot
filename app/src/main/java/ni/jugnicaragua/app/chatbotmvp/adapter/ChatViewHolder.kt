package ni.jugnicaragua.app.chatbotmvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ni.jugnicaragua.app.chatbotmvp.R

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userText : TextView = itemView.findViewById(R.id.userText) as TextView
    var botText  :TextView = itemView.findViewById(R.id.botText) as TextView

}