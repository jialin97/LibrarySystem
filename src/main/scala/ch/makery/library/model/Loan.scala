package ch.makery.library.model

import ch.makery.library.util.Database
import scalafx.beans.property.IntegerProperty
import scalikejdbc._

import scala.util.Try

class Loan (val loanIds: Int, val memberIds: Int, val bookIds: Int) extends Database{

  def this (){this((null).asInstanceOf[Int],(null).asInstanceOf[Int],(null).asInstanceOf[Int])}
  var loanId = IntegerProperty(loanIds)
  var memberId = IntegerProperty(memberIds)
  var bookId = IntegerProperty(bookIds)

  def save()  { //instance method
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into Loan (loadId, memberId,
						bookId) values
						(${loanId.value}, ${memberId.value}, ${bookId.value})
				""".update.apply()
      })

  }


}

object Loan extends Database {

  def apply(
    loanId: Int,
    memberId: Int,
    bookId: Int
    ): Loan = new Loan(
    loanId,
    memberId,
    bookId
    )

  def initializeLoanTable: Unit ={
    DB autoCommit{ implicit session =>
      sql"""
        create table Loan(
           loanId int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
           memberId int not null,
           bookId int not null
        )
        """.execute.apply()
    }
  }
  def getAllLoans : List[Loan] = { // for select queries use "readOnly"
    DB readOnly { implicit session =>
      sql"select * from Loan".map(rs => Loan(rs.int("loanId"), // use map collection as select queries usually returns multiple values
        rs.int("memberId"),rs.int("bookId"))).list.apply()
    } // return every row in database as a Loan
  }
}
