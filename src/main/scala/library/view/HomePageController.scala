package library.view

import ch.makery.library.MainApp.roots
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}

@sfxml
class HomePageController {

  def searchBook: Unit ={
    val resource2 = getClass.getResourceAsStream("SearchBook.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource2);
    val searchBookPage = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(searchBookPage)
  }

  def returnBook() = {

  }

  def bookDiscussionRoom = {
    val resource4 = getClass.getResourceAsStream("DiscussionRoomOverview.fxml")
    val loader2 = new FXMLLoader(null, NoDependencyResolver)
    loader2.load(resource4);
    val roomPage = loader2.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(roomPage)
  }

}
