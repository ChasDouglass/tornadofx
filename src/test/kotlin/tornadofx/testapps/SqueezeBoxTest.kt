package tornadofx.testapps

import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import tornadofx.*

class SqueezeBoxTestApp : App(SqueezeBoxTestView::class)

object AddFoldEvent : FXEvent()

class AccelTestApp : App(AccelTest::class)
class AccelTest : View() {
    override val root = button("Click me") {
        accelerator(KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_ANY, KeyCombination.SHIFT_ANY))
        setOnAction {
            alert(Alert.AlertType.INFORMATION, "Fire!", "You clicked.")
        }
    }
}
class SqueezeBoxTestView : View("SqueezeBox Multiple Open Folds") {
    override val root = vbox(5.0) {
        setPrefSize(300.0, 600.0)

        button("Add node") {
            setOnAction {
                fire(AddFoldEvent)
            }
            accelerator(KeyCombination.valueOf("Ctrl+A"))
        }

        scrollpane(fitToWidth = true) {
            squeezebox {
                fold("Pane 1", expanded = true, closeable = true) {
                    stackpane {
                        vbox {
                            alignment = Pos.CENTER
                            label("I'm inside 1")
                            label("Me too!")
                        }
                    }
                }
                fold("Pane 2", expanded = true, closeable = true) {
                    label("I'm inside 2")
                }
                fold("Pane 3", closeable = true) {
                    label("I'm inside 3")
                }
                fold("Pane 4", expanded = true, closeable = true) {
                    label("I'm inside 4")
                }
                fold("Pane 5", closeable = true) {
                    label("I'm inside 5")
                }
                subscribe<AddFoldEvent> {
                    fold("Another fold by subscription", closeable = true) {
                        stackpane {
                            label("Yo!")
                        }
                    }
                }
            }
        }
    }
}