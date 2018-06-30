package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.roots
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}

@sfxml
class OnlineBookResultController {

  def homePage: Unit ={
    MainApp.showHomePage()
  }

}
