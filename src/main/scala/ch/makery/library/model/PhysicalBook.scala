package ch.makery.library.model

import ch.makery.library.model.DiscussionRoom
import ch.makery.library.util.Database
import scalafx.beans.property.StringProperty
import scalikejdbc._

class PhysicalBook (name1: String, author1: String, publishDate1: String, bookId1: String, location1: String, availability1: String)extends Book(name1, author1, publishDate1) with Database {

  var bookId: StringProperty = new StringProperty(bookId1)
  var location: StringProperty = new StringProperty(location1)
  var availability : StringProperty = new StringProperty("3")
}

object PhysicalBook extends Database{
  def apply(
             name1: String,
             author1: String,
             publishDate1: String,
             bookId1: String,
             location1: String,
             availability1: String): PhysicalBook = {
    new PhysicalBook(name1, author1, publishDate1, bookId1, location1, availability1)
  }

  def createPhysicalTable = {
    DB autoCommit{ implicit session =>
      sql"""
          create table physicalBook(
           name varchar(50) not null,
           author varchar(50) not null,
           publishDate varchar(15) not null,
           bookId varchar(10) not null,
           location varchar(10) not null,
           availability varchar(3) not null
          )

        """.execute.apply()
    }
  }

  def insertPhysicalData ={
    DB autoCommit{ implicit  session =>
      sql"""
           insert into physicalBook(name, author, publishDate, bookId, location, availability) values
           ("Java", "Leong", "2014-01-12", "1234", "A12", "1")
           insert into physicalBook(name, author, publishDate, bookId, location, availability) values
           ("Chemistry", "Jia", "2011-09-16", "5566", "D54", "1")
           insert into physicalBook(name, author, publishDate, bookId, location, availability) values
           ("Accounting", "Joe", "2003-05-01", "3454", "A1", "0")
       """.update().apply()
    }
  }

  def getData : List[PhysicalBook] ={
    DB readOnly {implicit session =>
      sql"""
        select * from physicalBook
      """.map(BookInfo => PhysicalBook(
        BookInfo.string("name"),
        BookInfo.string("author"),
        BookInfo.string("publishDate"),
        BookInfo.string("bookId"),
        BookInfo.string("location"),
        BookInfo.string("availability")
      )
      ).list.apply()
    }
  }
}
