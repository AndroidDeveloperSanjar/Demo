package uz.example.android.util.events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ChannelEventSource<EVENT> : EventSource<EVENT> {
    private val eventsChannel by lazy { Channel<EVENT>(Channel.BUFFERED) }

    override val events by lazy { eventsChannel.receiveAsFlow() }

    override suspend fun sendEvent(event: EVENT) {
        eventsChannel.send(event)
    }
}
