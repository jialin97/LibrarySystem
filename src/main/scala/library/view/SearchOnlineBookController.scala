package library.view

import ch.makery.library.MainApp.roots
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}

@sfxml
class SearchOnlineBookController {

  def homePage = {
    val resource1 = getClass.getResourceAsStream("HomePage.fxml")
    val loader1 = new FXMLLoader(null, NoDependencyResolver)
    loader1.load(resource1);
    val home = loader1.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(home)
  }

  def search = {
    val resource2 = getClass.getResourceAsStream("ShowOnlineBookResult.fxml")
    val loader2 = new FXMLLoader(null, NoDependencyResolver)
    loader2.load(resource2);
    val search1 = loader2.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(search1)
  }

}
