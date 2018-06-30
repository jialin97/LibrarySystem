package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Member, User}
import scalafx.scene.control.{Alert, PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.Stage

@sfxml
class RegisterController (
                         private val usernameField : TextField,
                         private val passwordField: PasswordField,
                         private val checkPasswordField : PasswordField
                         ){

  var dialogStage : Stage  = null
  private val _member = new Member()

  def member : Member =_member

  def nullChecking (x : String) = x == null || x.length == 0

  def passwordMatching (passwordField: PasswordField, checkPasswordField: PasswordField)={
    passwordField.text.value != checkPasswordField.text.value
  }

  def isInputValid() : Boolean = {
    var errorMessage = ""

    if (nullChecking(usernameField.text.value))
      errorMessage += "Please enter a username!\n"
    if (nullChecking(passwordField.text.value))
      errorMessage += "Please enter a password!\n"
    if (passwordMatching(passwordField, checkPasswordField))
      errorMessage += "Please enter the same passwords!\n"

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

  def register(): Unit ={

    if(isInputValid()){
      _member.username = usernameField.text
      _member.password = passwordField.text

      if(_member.usernameUsed()){
        var dialogStage : Stage  = null

        val alert = new Alert(Alert.AlertType.Error){
          initOwner(dialogStage)
          headerText = "Invalid Username"
          contentText = "The username that you have entered is invalid. \nPlease enter another username."
        }.showAndWait()

      }
      else{
        _member.save()

        val alertBox = new Alert(AlertType.Information) {
          initOwner(MainApp.stage)
          headerText = "Registration successful!"
        }.showAndWait()

        MainApp.showHomePage()
      }
    }

  }

}
