package ch.makery.library.view

import ch.makery.library.MainApp
import ch.makery.library.MainApp.roots
import ch.makery.library.model.{Admin, User}
import scalafx.scene.control.{Alert, PasswordField, TextField}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafx.scene.control.Alert.AlertType
import scalafx.stage.Stage

@sfxml
class AdminSignInController (
                            private val usernameField : TextField,
                            private val passwordField: PasswordField
                            ){

  var dialogStage : Stage  = null

  val _admin = new Admin()

  def admin : Admin =_admin

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


  def signIn(): Unit ={
    if(isInputValid()){
      _admin.username = usernameField.text
      _admin.password = passwordField.text

      if(_admin.isExist()){

        val alertBox = new Alert(AlertType.Information) {
          initOwner(MainApp.stage)
          headerText = "You have successfully signed in.."
        }.showAndWait()

        MainApp.showHomePage()
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

}
