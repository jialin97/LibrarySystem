package library.model

import ch.makery.library.util.Database
import scalafx.beans.property.StringProperty
import scalikejdbc._

class DiscussionRoom (roomId1: String, timeSlot1: String) extends Database{
  def this() = this(null, null)
  val roomId: StringProperty = new StringProperty(roomId1)
  val timeSlot: StringProperty = new StringProperty(timeSlot1)
  var availability: Int = 0

}

//create and read table
object DiscussionRoom extends Database{
  def apply(
             roomId1: String,
             timeSlot1: String,
             availability1: Int
           ): DiscussionRoom = {
    new DiscussionRoom(roomId1, timeSlot1){
      availability = availability1
    }
  }

  def createRoomTable() = {
    DB autoCommit { implicit session =>
      sql"""
          create table room(
               roomId varchar(5) not null,
               timeSlot varchar(15) not null,
               availability Integer(2) not null
           )

        """.execute.apply()
    }
  }
  def insertRoomData() ={
    DB autoCommit{ implicit  session =>
      sql"""
            insert into room(roomId, timeSLot, availability) values
            ('R1', '10am - 1pm', 1);
           |insert into room(roomId, timeSLot, availability) values
            ('R2', '10am - 1pm', 0);
           |insert into room(roomId, timeSLot, availability) values
            ('R3', '10am - 1pm', 1);

       """.update().apply()
    }
  }
  def getRoom : List[DiscussionRoom] ={
    DB readOnly {implicit session =>
      sql"""
        select * from room
      """.map(roomInfo => DiscussionRoom(
                          roomInfo.string("roomId"),
                          roomInfo.string("timeSlot"),
                          roomInfo.int("availability")
                          )
      ).list.apply()
    }
  }
}
