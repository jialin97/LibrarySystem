package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.roots
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml

@sfxml
class SearchPhysicalBookController {

  def result = {
    val resource = getClass.getResourceAsStream("ShowPhysicalBookResult.fxml")
    val loader2 = new FXMLLoader(null, NoDependencyResolver)
    loader2.load(resource);
    val searchResult = loader2.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(searchResult)
  }

  def homePage ={
    MainApp.showHomePage()
  }
}
