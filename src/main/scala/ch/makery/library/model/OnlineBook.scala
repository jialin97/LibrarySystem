package ch.makery.library.model

import ch.makery.library.util.Database
import scalafx.beans.property.StringProperty
import scalikejdbc._

class OnlineBook (name1: String, author1: String, publishDate1: String, url1: String) extends Book(name1: String, author1: String, publishDate1: String){

  var url: StringProperty = new StringProperty(url1)
}

object OnlineBook extends Database{
  def apply(
             name1: String,
             author1: String,
             publishDate1: String,
             url1: String): OnlineBook = {
    new OnlineBook(name1, author1, publishDate1, url1)
  }
  def createOnlineTable = {
    DB autoCommit{ implicit session =>
      sql"""
          create table onlineBook(
           name varchar(50) not null,
           author varchar(50) not null,
           publishDate varchar(15) not null,
           url varchar(50) not null
          )
        """.execute.apply()
    }
  }
  def insertPhysicalData ={
    DB autoCommit{ implicit  session =>
      sql"""
           insert into onlineBook(name, author, publishDate, url) values
           ("Mathematics", "Leong", "2011-07-09", "www.mathematics.com")
           insert into onlineBook(name, author, publishDate, url) values
           ("Database", "Joe", "2014-02-28", "www.database.com")
           insert into onlineBook(name, author, publishDate, url) values
           ("Java", "Jessica", "2011-09-01", "www.java.com")
       """.update().apply()
    }
  }

  def getData1 : List[OnlineBook] ={
    DB readOnly {implicit session =>
      sql"""
        select * from onlineBook
      """.map(BookInfo => OnlineBook(
        BookInfo.string("name"),
        BookInfo.string("author"),
        BookInfo.string("publishDate"),
        BookInfo.string("url")
      )
      ).list.apply()
    }
  }
}