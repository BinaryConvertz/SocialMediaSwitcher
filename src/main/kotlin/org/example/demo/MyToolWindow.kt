package org.example.demo
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.awt.Desktop
import java.net.URI
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JLabel

class MyToolWindowFactory : ToolWindowFactory {
    override fun shouldBeAvailable(project: Project) = true

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow()
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    data class Visible(val isActive: Boolean)

    class MyToolWindow {


        private fun openDesktop(textURL: URI?) {
            Desktop.getDesktop().browse((textURL))
        }

        private fun visible(jButton: JButton, isActive: Boolean) {


            val isActiveWindow = Visible(isActive)

            if (isActiveWindow.isActive) {
                jButton.isVisible = isActive

            } else {
                 jButton.isVisible = !isActive

            }

        }

        private val content = JBPanel<JBPanel<*>>().apply {

            val continueButton = JButton("Continue")
            val button = JButton("Twitter")
            val googleButton = JButton("Google")
            val ytButton = JButton("YouTube")
            val check = JCheckBox("Active")
            val labelEnabled = JLabel("Enabled")


            continueButton.addActionListener {

                add(labelEnabled)
                add(check)

                if (check.isSelected) {


                    googleButton.addActionListener {
                        openDesktop(URI.create("https://www.google.com"))
                    }

                    ytButton.addActionListener {
                        openDesktop(URI.create("https://www.youtube.com"))
                    }

                    button.addActionListener {
                        openDesktop(URI.create("https://www.x.com"))
                    }

                    visible(googleButton, true)
                    visible(ytButton, true)
                    visible(button, true)

                    check.isVisible = false
                    labelEnabled.text = "Enabled"
                    visible(continueButton, false)

                }

                if (!check.isSelected) {

                    labelEnabled.text = "Disabled\n"

                }
            }


            visible(googleButton, false)
            visible(ytButton, false)
            visible(button, false)
            visible(continueButton, true)

            check.isVisible = true
            add(googleButton)
            add(ytButton)
            add(button)
            add(continueButton)

        }

        fun getContent(): JBPanel<JBPanel<*>> = content

    }

}