package library.view

import ch.makery.library.MainApp
import ch.makery.library.model.DiscussionRoom
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml

@sfxml   //to mark the class is the controller for fxml document
class DiscussionRoomController (
                                 private val roomTable: TableView[DiscussionRoom],
                                 private val roomIdColumn: TableColumn[DiscussionRoom, String],
                                 private val timeSlotColumn: TableColumn[DiscussionRoom, String],

                                 private val roomIdLabel: Label,
                                 private val timeSlotLabel: Label,
                                 private val availabilityLabel: Label    //'1': available, '0': not available(cannot book)
                               ){

  roomTable.items = MainApp.roomData
  roomIdColumn.cellValueFactory = {_.value.roomId}
  timeSlotColumn.cellValueFactory = {_.value.timeSlot}

  showDiscussionRoomDetails(None);

  roomTable.selectionModel().selectedItem.onChange(
    (_, _, newValue) => showDiscussionRoomDetails(Some(newValue))
  )

  private def showDiscussionRoomDetails(discussionRoom: Option[DiscussionRoom]) = {

    discussionRoom match {
      case Some(discussionRoom) =>
        roomIdLabel.text = discussionRoom.roomId.value
        timeSlotLabel.text = discussionRoom.timeSlot.value
        availabilityLabel.text = discussionRoom.availability.toString

      case None =>
        roomIdLabel.text = ""
        timeSlotLabel.text = ""
        availabilityLabel.text = ""
    }
  }

  def bookRoom(action: ActionEvent) = {
    val selectedRoom = roomTable.selectionModel().selectedIndexProperty().value
    if(selectedRoom < 0){  //nothing selected
      val alertBox = new Alert(AlertType.Warning){
        initOwner(MainApp.stage)
        title = "Nothing selected"
        headerText = "No rooms selected"
        contentText = "Please select a room from the table."
      }.showAndWait()
    }
    else {    //if a room is selected
      if (availabilityLabel.text == "0") {          //room is booked by user
        val alertBox = new Alert(AlertType.Information) {
          initOwner(MainApp.stage)
          title = "Booking result"
          headerText = "This room is booked by other user."
          contentText = "You can try to book another room or other time slots."
        }.showAndWait()
      }
      else /*if(availabilityLabel.text == "1")*/{            //room is not booked by user
        availabilityLabel.text = "0"      //set availability to '0' (cannot set)
        val alertBox = new Alert(AlertType.Information){
          initOwner(MainApp.stage)
          title = "Booking result"
          headerText = "Successful"
          contentText = "You have successfully booked a discussion room."
        }.showAndWait()
      }
    }
  }

  def returnRoom(actionEvent: ActionEvent) = {
    val selectedRoom = roomTable.selectionModel().selectedIndexProperty().value
    if(selectedRoom < 0){  //nothing selected
      val alertBox = new Alert(AlertType.Warning){
        initOwner(MainApp.stage)
        title = "Nothing selected"
        headerText = "No rooms selected"
        contentText = "Please select a room from the table."
      }.showAndWait()
    }
    else { //if a room is selected
      if (availabilityLabel.text == "0") {      //room is booked by user

        availabilityLabel.text = "1" //set availability to '1'
        val alertBox = new Alert(AlertType.Information) {
          initOwner(MainApp.stage)
          title = "Return result"
          headerText = "Successful"
          contentText = "You have successfully return a discussion room."
        }.showAndWait()
      }
      if(availabilityLabel.text == "1"){            //room is not booked by user
        val alertBox = new Alert(AlertType.Information) {
          initOwner(MainApp.stage)
          title = "Booking result"
          headerText = "This room is not booked by any user."
          contentText = "You might return the wrong discussion room."
        }.showAndWait()
      }
    }
  }
  def homePage = {
    MainApp.showHomePage()
  }

}
