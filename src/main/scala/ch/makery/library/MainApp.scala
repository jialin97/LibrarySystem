package ch.makery.library

import ch.makery.library.model.{DiscussionRoom, OnlineBook, PhysicalBook}
import ch.makery.library.util.Database
import ch.makery.library.view.{SearchBookPageController, SearchPhysicalBookController}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.stage.{Modality, Stage}


object MainApp extends JFXApp{

  Database.setupDB()
  Database.setupPhysicalBookDB()
  Database.setupOnlineBookDB()

  var roomData = new ObservableBuffer[DiscussionRoom]()
    roomData ++= DiscussionRoom.getRoom

  var physicalBookData = new ObservableBuffer[PhysicalBook]()
    physicalBookData ++= PhysicalBook.getData


  var onlineBookData = new ObservableBuffer[OnlineBook]()
    onlineBookData ++= OnlineBook.getData1

  val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource);

  val roots =loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage{
    title = "Library System"
    scene = new Scene(height = 600, width = 600){
      root = roots
    }
  }

  def showHomePage() ={
    val resource1 = getClass.getResourceAsStream("view/Authentication.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource1);
    val homePage = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(homePage)
  }
  showHomePage()

}
