package ch.makery.library.view

import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Member, User}
import scalafx.scene.control.{Alert, PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

@sfxml
class SigninController (
                       private val usernameField : TextField,
                       private val passwordField : PasswordField
                       ){
  var dialogStage : Stage  = null

  val _member = new Member()

  def member : Member =_member

  def nullChecking (x : String) = x == null || x.length == 0

  def isInputValid() : Boolean = {
    var errorMessage = ""

    if (nullChecking(usernameField.text.value))
      errorMessage += "Please enter a username!\n"
    if (nullChecking(passwordField.text.value))
      errorMessage += "Please enter a password!\n"

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error){
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }
  }

  def signIn()={

    if(isInputValid()){
      _member.username = usernameField.text
      _member.password = passwordField.text

      if(_member.isExist()){
        val resource = getClass.getResourceAsStream("HomePage.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource)
        val homePage = loader.getRoot[jfxs.layout.AnchorPane]
        roots.setCenter(homePage)
      }
      else{

        val alert = new Alert(Alert.AlertType.Error){
          initOwner(dialogStage)
          headerText = "Invalid Username / Password"
          contentText = "The username or password that you have entered is invalid!"
        }.showAndWait()

        isInputValid() == false
      }


    }

  }

  def register()={
    val resource = getClass.getResourceAsStream("Register.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val memberRegister = loader.getRoot[jfxs.layout.AnchorPane]
    roots.setCenter(memberRegister)
  }

}
