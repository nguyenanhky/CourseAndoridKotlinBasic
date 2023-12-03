package kynv1.fsoft.basic.viewmodel

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import kynv1.fsoft.basic.model.Counter
import kynv1.fsoft.basic.model.DataInterface
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class CounterViewModelTest{
    private val dataInterface =mock(DataInterface::class.java)
    private lateinit var viewModel:CounterViewModel

    @Before
    fun setup(){
        viewModel = CounterViewModel(dataInterface)
    }

    @Test
    fun testInitialValue(){
        viewModel.onDataUpdate {
            Assert.assertEquals(0,it)
        }
    }

    @Test
    fun testUpdateCurrent(){
        val value  = 5
        val counter = Counter(value = value, dateInMillis = 3902L)
        val items = listOf(counter)
        val findId = counter.id

        whenever(dataInterface.items).doReturn(items)

        viewModel.updateCurrentId(findId)

        // vertify
        Assert.assertEquals(counter,viewModel.currentCounter)
    }
}