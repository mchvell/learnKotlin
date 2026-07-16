package moduleThree

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class ReportSectionTest {

    @Test
    fun checkRenderSummary(){
        val result = SummarySection().render()
        assertContains(result,"Итоги")
        assertContains(result, "Страница 3")
    }

    @Test
    fun checkRenderFailures(){
        val result = FailuresSection().render()
        assertContains(result, "Падения")
        assertContains(result, "Страница 4")
    }


    @Test
    fun checkRenderChain(){
        val sections = listOf(SummarySection(), FailuresSection())
        for (s in sections){
            assertTrue {s.render().isNotBlank()}
        }
    }

}