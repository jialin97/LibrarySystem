package ch.makery.library.model

import ch.makery.library.util.Database
import scalikejdbc._

import scala.util.Try

class Admin (usernameS: String, passwordS: String) extends User (usernameS, passwordS){
  def this() = this(null, null)

  def save() : Try[Int] = { //instance method
    if (!(isExist())) {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into Admin (username, password) values
						(${username.value}, ${password.value})
				""".update.apply()
      })
    }
    else
      throw new Exception("Username already exists") // add error dialog for existing username

  }

  def isExist() : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from Admin where
				username = ${username.value} and password = ${password.value}
			""".map(rs => rs.string("username")).single.apply() //.single returns an option (Some or None)
    } match {
      case Some(x) => true
      case None => false
    }

  }

}

object Admin extends Database{

  def apply(usernameS: String, passwordS: String): Admin = new Admin(usernameS, passwordS)

  def initializeAdminTable = {
    DB autoCommit{ implicit session =>
      sql"""
          create table Admin(
           aid int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
           username varchar(50) not null,
           password varchar(100) not null
          )

        """.execute.apply()
    }
  }
  
  def initData{
      DB autoCommit { implicit session =>
        sql"insert into Admin (username, password) values('admin', 'admin')"
				.update.apply()
      }

  }
  def getAllAdmins : List[Admin] = { // for select queries use "readOnly"
    DB readOnly { implicit session =>
      sql"select * from Admin".map(rs => Admin(rs.string("username"), // use map collection as select queries usually returns multiple values
        rs.string("password") )).list.apply()
    } // return every row in database as an Admin
  }


}
