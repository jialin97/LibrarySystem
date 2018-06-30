package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.{getClass, roots}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.control.Alert.AlertType

@sfxml
class HomePageController {

  var logged : Boolean = false

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

  def logOut() ={

    val alertBox = new Alert(AlertType.Confirmation) {
      initOwner(MainApp.stage)
      headerText = "Confirm logout?"
    }

    val confirm = alertBox.showAndWait()

    confirm match {
      case Some(ButtonType.OK)=>
        logged = true
      case _ =>
        logged = false
    }

    if(logged)
      MainApp.showAuthentication()

  }

}
