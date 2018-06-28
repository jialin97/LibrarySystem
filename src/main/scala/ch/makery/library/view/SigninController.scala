package ch.makery.library.view

import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Member, User}
import scalafx.scene.control.{PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml

@sfxml
class SigninController (
                       private val usernameField : TextField,
                       private val passwordField : PasswordField
                       ){
  private val _member = new Member()

  def member : Member =_member

  def signIn()={

    _member.username = usernameField.text
    _member.password = passwordField.text
    // add validation!

    val resource = getClass.getResourceAsStream("HomePage.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val homePage = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(homePage)


  }

  def register()={
    val resource = getClass.getResourceAsStream("Register.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val memberRegister = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(memberRegister)
  }

}
