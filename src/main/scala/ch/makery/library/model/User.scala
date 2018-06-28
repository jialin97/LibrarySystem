package ch.makery.library.model

import scalafx.beans.property.StringProperty

class User (usernameS: String, passwordS: String){
  def this() = this(null, null)
  var username = new StringProperty(usernameS)
  var password = new StringProperty(passwordS)



}
