package ch.makery.library.model

import ch.makery.library.util.Database
import scalafx.beans.property.IntegerProperty
import scalikejdbc._

import scala.util.Try

class Loan (val loanIds: Int, val userIds: Int, val bookIds: Int) extends Database{

  def this (){this((null).asInstanceOf[Int],(null).asInstanceOf[Int],(null).asInstanceOf[Int])}
  var loanId = IntegerProperty(loanIds)
  var userId = IntegerProperty(userIds)
  var bookId = IntegerProperty(bookIds)

  def save()  { //instance method
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into Loan (loadId, userId,
						bookId) values
						(${loanId.value}, ${userId.value}, ${bookId.value})
				""".update.apply()
      })

  }


}

object Loan extends Database {

  def apply(
    loanId: Int,
    userId: Int,
    bookId: Int
    ): Loan = new Loan(
    loanId,
    userId,
    bookId
    )

  def initializeLoanTable: Unit ={
    DB autoCommit{ implicit session =>
      sql"""
        create table Loan(
           loanId int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
           userId int not null,
           bookId int not null
        )
        """.execute.apply()
    }
  }
  def getAllLoans : List[Loan] = { // for select queries use "readOnly"
    DB readOnly { implicit session =>
      sql"select * from Loan".map(rs => Loan(rs.int("loanId"), // use map collection as select queries usually returns multiple values
        rs.int("userId"),rs.int("bookId"))).list.apply()
    } // return every row in database as a Loan
  }
}
