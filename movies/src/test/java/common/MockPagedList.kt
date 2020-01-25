package common

import androidx.paging.PagedList
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    return mockk {
        val pageSlot = slot<Int>()
        every { get(capture(pageSlot)) } answers { list[pageSlot.captured] }
        every { size } returns list.size
    }
}
