package library.view

import ch.makery.library.MainApp.roots
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}

@sfxml
class OnlineBookResultController {

  def homePage: Unit ={
    val resource1 = getClass.getResourceAsStream("HomePage.fxml")
    val loader1 = new FXMLLoader(null, NoDependencyResolver)
    loader1.load(resource1);
    val home = loader1.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(home)
  }

}
