package ch.makery.library.view

import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Admin, User}
import scalafx.scene.control.{PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}

@sfxml
class AdminSignInController (
                            private val usernameField : TextField,
                            private val passwordField: PasswordField
                            ){
  private val _admin = new Admin()

  def admin : Admin =_admin

  def signIn(): Unit ={

    _admin.username = usernameField.text
    _admin.password = passwordField.text
    // add validation!!

    val resource = getClass.getResourceAsStream("HomePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val homePage = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(homePage)

  }

}
