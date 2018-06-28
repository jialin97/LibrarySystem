package ch.makery.library.model

import ch.makery.library.util.Database
import scalafx.beans.property.StringProperty
import scalikejdbc._

import scala.util.Try

class Member (usernameS: String, passwordS: String) extends User (usernameS, passwordS){
  def this() = this(null, null)

  def save() : Try[Int] = { //instance method
    if (!(isExist)) {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into Member (username, password) values
						(${username.value}, ${password.value})
				""".update.apply()
      })
    }
    else
      throw new Exception("Username already exists") // add error dialog for existing username

  }

  def isExist : Boolean =  {
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

}
