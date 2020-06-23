package com.hernanbosqued.tests

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Tests {

    //@Rule
    //val systemOutRule: SystemOutRule = SystemOutRule().enableLog()

    @Before
    fun before() {
        println("before")
    }

    @After
    fun after() {
        println("after")
    }

    @Test
    fun ifNull() {
        var obj: Int? = null
        obj.ifNull { assert(true) } ?: run { assert(false) }
        obj = 1
        obj.ifNull { assert(false) } ?: run { assert(true) }
    }

    @Test
    fun testWithoutElse() {
        onlyThen(true, "true", "true")
        onlyThen(true, "true", null)
        onlyThen(condition = true, ifCondition = null, expectedValue = null)
    }

    @Test
    fun testTrueSin() {
        onlyThen(true, "true", "true")
        onlyThen(true, null, null)
        onlyThen(condition = true, ifCondition = true, expectedValue = true)
    }

    @Test
    fun testFalseSin() {
        onlyThen(false, "true", null)
        onlyThen(false, "true", null)
        onlyThen(condition = false, ifCondition = null, expectedValue = null)
    }

    @Test
    fun testTrueCon() {
        thenOrElse(true, null, "false", null)
        thenOrElse(true, "true", "false", "true")
        thenOrElse(true, "true", "false", "true")
        thenOrElse(true, 1, 0, 1)
    }

    @Test
    fun testFalseCon() {
        thenOrElse(false, null, "false", "false")
        thenOrElse(false, "true", "false", "false")
        thenOrElse(false, 1, 0, 0)
    }

    private fun <CLASS> onlyThen(condition: Boolean, ifCondition: CLASS?, expectedValue: CLASS?) {
        assertEquals(condition.then(ifCondition), expectedValue)


        //assertEquals(condition then ifCondition, expectedValue)
    }

    private fun <CLASS> thenOrElse(condition: Boolean, ifResult: CLASS?, elseResult: CLASS?, expectedValue: CLASS?) {
        assertEquals( condition
            .then(ifResult)
            .orElse(elseResult), expectedValue)

        //ssertEquals(condition then ifResult orElse elseResult, expectedValue)
    }

    @Test
    fun testLock() {

    }
}
