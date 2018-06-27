package ch.makery.library.model

import scalafx.beans.property.StringProperty

class Book(name1: String, author1: String, publishDate1: String) {
  var name: StringProperty = new StringProperty(name1)
  var author: StringProperty = new StringProperty(author1)
  var publishDate: StringProperty = new StringProperty(publishDate1)

}
