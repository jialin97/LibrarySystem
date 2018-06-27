package ch.makery.library.util

import ch.makery.address.model.{OnlineBook, PhysicalBook}
import ch.makery.library.model.DiscussionRoom
import scalikejdbc._    //third party to connect to database

trait Database {     //trait dont have constructors
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"     //driver to connect database

  val dbURL = "jdbc:derby:myDB;create=true;";   //"myDB" is the database name, "create=true" create database
  // initialize JDBC driver & connection pool
  Class.forName(derbyDriverClassname)

  ConnectionPool.singleton(dbURL, "me", "mine")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

  /*
    AutoSession is singleton, so everytime we extends the trait database it will point to the same AutoSession
  */

}
object Database extends Database{
  def setupDB() = {
    if (!foundRoomTable){
      DiscussionRoom.createRoomTable()
    }
  }
  def setupPhysicalBookDB() = {
    if (!foundPhysicalBookTable){
      PhysicalBook.createPhysicalTable
    }
  }
  def setupOnlineBookDB() = {
    if(!foundOnlineBookTable){
      OnlineBook.createOnlineTable
    }
  }
  def foundRoomTable : Boolean = {

    DB getTable "room" match {    //Db.getTable("room")
      case Some(x) => true
      case None => false
    }

  }
  def foundPhysicalBookTable: Boolean = {
    DB getTable "physicalBook" match {
      case Some(y) => true
      case None => false
    }
  }
  def foundOnlineBookTable: Boolean = {
    DB getTable "onlineBook" match {
      case Some(z) => true
      case None => false
    }
  }
}

