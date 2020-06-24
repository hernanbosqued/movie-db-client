import com.hernanbosqued.movie_db_client.domain.ifNull
import com.hernanbosqued.movie_db_client.domain.orElse
import com.hernanbosqued.movie_db_client.domain.then
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
    fun testTrue() {
        val str = "false"
        thenOrElse(true, null, str, null)
        thenOrElse(true, "true", str, "true")
        thenOrElse(true, "true", str, "true")

        val int = 0
        thenOrElse(true, 1, int, 1)

        val nullable: String? = null
        thenOrElse(true, "true", nullable, "true")
    }

    @Test
    fun testFalse() {
        val str = "false"
        thenOrElse(false, null, str, str)
        thenOrElse(false, "true", str, str)
        val int = 0
        thenOrElse(false, 1, int, int)

        val nullable: String? = null
        thenOrElse(false, "true", nullable, nullable)
    }

    private fun <CLASS> thenOrElse(
        condition: Boolean,
        ifResult: CLASS?,
        elseResult: CLASS?,
        expectedValue: CLASS?
    ) {
        assertEquals(expectedValue, condition.then(ifResult).orElse(elseResult))
        assertEquals(expectedValue, condition then ifResult orElse elseResult)
    }

    @Test
    fun testLock() {

    }
}
