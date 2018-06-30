package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.roots
import scalafxml.core.macros.sfxml
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}

@sfxml
class SearchOnlineBookController {

  def homePage = {
    MainApp.showHomePage()
  }

  def search = {
    val resource2 = getClass.getResourceAsStream("ShowOnlineBookResult.fxml")
    val loader2 = new FXMLLoader(null, NoDependencyResolver)
    loader2.load(resource2);
    val search1 = loader2.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(search1)
  }

}
