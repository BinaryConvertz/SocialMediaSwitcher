package org.example.demo
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.awt.Desktop
import java.net.URI
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {
    override fun shouldBeAvailable(project: Project) = true

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow()
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    class MyToolWindow {

        private fun openDesktop(textURL: URI?) {
            Desktop.getDesktop().browse((textURL))
        }

        private val content = JBPanel<JBPanel<*>>().apply {

            val button = JButton("Twitter")
            val googleButton = JButton("Google")
            val ytButton = JButton("YouTube")

            ytButton.addActionListener {

                openDesktop(URI.create("https://www.youtube.com"))
            }

            googleButton.addActionListener {
                openDesktop(URI.create("https://www.google.com"))
            }

            button.addActionListener {
                openDesktop(URI.create("https://www.x.com"))
            }

            add(googleButton)
            add(ytButton)
            add(button)
        }

        fun getContent(): JBPanel<JBPanel<*>> = content

    }

}