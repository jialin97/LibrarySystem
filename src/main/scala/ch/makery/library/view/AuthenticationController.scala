package ch.makery.library.view

import ch.makery.library.MainApp.roots
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}


@sfxml
class AuthenticationController{
  def chooseMember={
    val resource = getClass.getResourceAsStream("Signin.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val memberLogin = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(memberLogin)
  }

  def chooseAdmin = {
    val resource = getClass.getResourceAsStream("AdminSignIn.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val adminLogin = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(adminLogin)
  }
}
