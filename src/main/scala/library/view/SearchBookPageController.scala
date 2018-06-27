package library.view

import ch.makery.library.MainApp.roots
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml

@sfxml
class SearchBookPageController {

  def physicalBook = {
    val resource = getClass.getResourceAsStream("SearchPhysicalBook.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val physical = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(physical)
  }

  def onlineBook = {
    val resource1 = getClass.getResourceAsStream("SeachOnlineBook.fxml")
    val loader1 = new FXMLLoader(null, NoDependencyResolver)
    loader1.load(resource1);
    val online = loader1.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(online)
  }

}
