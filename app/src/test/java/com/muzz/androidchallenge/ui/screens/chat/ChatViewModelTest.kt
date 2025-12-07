package com.muzz.androidchallenge.ui.screens.chat

import com.muzz.androidchallenge.domain.interactors.GetAllMessages
import com.muzz.androidchallenge.domain.interactors.GetCurrentUser
import com.muzz.androidchallenge.domain.interactors.SendMessage
import com.muzz.androidchallenge.domain.interactors.SetCurrentUser
import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.models.Result
import com.muzz.androidchallenge.domain.models.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    private val fakeTimestamp = 1_000_000_000_000L

    @MockK
    private lateinit var getAllMessages: GetAllMessages

    @MockK
    private lateinit var sendMessage: SendMessage

    @MockK
    private lateinit var getCurrentUser: GetCurrentUser

    @MockK
    private lateinit var setCurrentUser: SetCurrentUser

    private lateinit var viewModel: ChatViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())

        coEvery { getCurrentUser(Unit) } returns flowOf(Result.Success(User.SARAH))
        coEvery { getAllMessages(Unit) } returns flowOf(Result.Success(emptyList()))

        viewModel = ChatViewModel(
            getAllMessages = getAllMessages,
            sendMessage = sendMessage,
            getCurrentUser = getCurrentUser,
            setCurrentUser = setCurrentUser
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    /** Tests for processMessages() method. **/
    @Test
    fun `GIVEN empty messages list WHEN processMessages is called THEN returns empty list`() {
        val messages = emptyList<Message>()
        val result = viewModel.processMessages(messages)
        assertTrue("Result should be empty", result.isEmpty())
        assertEquals("Result size should be 0", 0, result.size)
    }

    @Test
    fun `GIVEN single message WHEN processMessages is called THEN returns list with header and message item`() {
        val messages =
            listOf(Message(id = 1, senderId = 1, text = "Hello", timestamp = fakeTimestamp))

        val result = viewModel.processMessages(messages)
        val messageItem = result[1] as ChatListItem.MessageItem

        assertEquals("Result should have 2 items (header + message)", 2, result.size)
        assertTrue("First item should be SectionHeader", result[0] is ChatListItem.SectionHeader)
        assertTrue("Second item should be MessageItem", result[1] is ChatListItem.MessageItem)
        assertEquals("Message text should match", "Hello", messageItem.message.text)
        assertFalse("Single message should not be grouped", messageItem.isGrouped)
    }

    @Test
    fun `GIVEN two messages from same sender within 20 seconds WHEN processMessages is called THEN second message is grouped`() {
        val messages = listOf(
            Message(id = 1, senderId = 1, text = "First", timestamp = fakeTimestamp),
            Message(id = 2, senderId = 1, text = "Second", timestamp = fakeTimestamp + 10_000)
        )

        val result = viewModel.processMessages(messages)
        assertEquals("Result should have 3 items (header + 2 messages)", 3, result.size)

        val firstMessage = result[1] as ChatListItem.MessageItem
        val secondMessage = result[2] as ChatListItem.MessageItem

        assertFalse("First message should not be grouped", firstMessage.isGrouped)
        assertTrue("Second message should be grouped", secondMessage.isGrouped)
        assertEquals("First message text", "First", firstMessage.message.text)
        assertEquals("Second message text", "Second", secondMessage.message.text)
    }

    @Test
    fun `GIVEN two messages from same sender one hour apart WHEN processMessages is called THEN second message has new header and is not grouped`() {
        val messages = listOf(
            Message(id = 1, senderId = 1, text = "First", timestamp = fakeTimestamp),
            Message(id = 2, senderId = 1, text = "Second", timestamp = fakeTimestamp + 3_600_001L)
        )

        val result = viewModel.processMessages(messages)
        val firstMessageItem = result[1] as ChatListItem.MessageItem
        val secondMessageItem = result[3] as ChatListItem.MessageItem

        assertEquals("Result should have 4 items (header + msg + header + msg)", 4, result.size)
        assertTrue("First item should be SectionHeader", result[0] is ChatListItem.SectionHeader)
        assertTrue("Second item should be MessageItem", result[1] is ChatListItem.MessageItem)
        assertTrue("Third item should be SectionHeader", result[2] is ChatListItem.SectionHeader)
        assertTrue("Fourth item should be MessageItem", result[3] is ChatListItem.MessageItem)
        assertFalse("First message should not be grouped", firstMessageItem.isGrouped)
        assertFalse("Second message should NOT be grouped (more than 20 seconds apart)", secondMessageItem.isGrouped)
        assertEquals("First", firstMessageItem.message.text)
        assertEquals("Second", secondMessageItem.message.text)
    }
}