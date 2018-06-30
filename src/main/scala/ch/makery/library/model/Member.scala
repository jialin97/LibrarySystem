package ch.makery.library.model

import ch.makery.library.util.Database
import scalafx.beans.property.StringProperty
import scalafx.scene.control.Alert
import scalafx.stage.Stage
import scalikejdbc._

import scala.util.Try

class Member (usernameS: String, passwordS: String) extends User (usernameS, passwordS){
  def this() = this(null, null)

  def save(){ //instance method
      DB autoCommit { implicit session =>
        sql"""
					insert into Member (username, password) values
						(${username.value}, ${password.value})
				""".update.apply()
      }
  }

  def usernameUsed() : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from Member where
				username = ${username.value}
			""".map(rs => rs.string("username")).single.apply() //.single returns an option (Some or None)
    } match {
      case Some(x) => true
      case None => false
    }

  }

  def isExist() : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from Member where
				username = ${username.value} and password = ${password.value}
			""".map(rs => rs.string("username")).single.apply() //.single returns an option (Some or None)
    } match {
      case Some(x) => true
      case None => false
    }

  }

}

object Member extends Database{

  def apply(
             usernameS: String,
             passwordS: String,
           ): Member = {
    new Member(usernameS, passwordS)
  }

  def initializeMemberTable = {
    DB autoCommit{ implicit session =>
      sql"""
          create table Member(
           memberid int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
           username varchar(50) not null,
           password varchar(100) not null
          )

        """.execute.apply()
    }
  }
  def getAllMembers : List[Member] = { // for select queries use "readOnly"
    DB readOnly { implicit session =>
      sql"select * from Member".map(rs => Member(rs.string("username"), // use map collection as select queries usually returns multiple values
        rs.string("password"))).list.apply()
    } // return every row in database as a Member
  }

}
