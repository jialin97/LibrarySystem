package ch.makery.library.view

import ch.makery.library.MainApp
import scalafxml.core.macros.sfxml
import ch.makery.library.MainApp.roots
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}

@sfxml
class PhysicalBookResultController {

  def homePage: Unit ={
    MainApp.showHomePage()
  }
}
