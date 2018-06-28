package ch.makery.library.view

import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Member, User}
import scalafx.scene.control.{PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}

@sfxml
class RegisterController (
                         private val usernameField : TextField,
                         private val passwordField: PasswordField,
                         private val checkPasswordField : PasswordField
                         ){
  private val _member = new Member()

  def member : Member =_member

  def register(): Unit ={
    _member.username = usernameField.text
    _member.password = passwordField.text
    // add validation!

    val resource = getClass.getResourceAsStream("HomePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val homePage = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(homePage)
  }

}
