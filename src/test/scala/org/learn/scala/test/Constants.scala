package org.learn.scala.test

object Constants {
  val PARENT_DIR: String = "./data-dir"
  val DATABASE_NAME: String = "my-h2-db"
  val DATABASE_DIR: String = s"$PARENT_DIR/$DATABASE_NAME"
  val DATABASE_URL: String = s"jdbc:h2:$DATABASE_DIR;MODE=DB2;INIT=runscript from './src/test/resources/initial_script.sql'"
}
