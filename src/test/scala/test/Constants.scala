package test

object Constants {
  val PARENT_DIR: String = "./data-dir"
  val DATABASE_NAME: String = "my-h2-db"
  val DATABASE_DIR: String = s"$PARENT_DIR/$DATABASE_NAME"
  val DATABASE_URL: String = s"jdbc:h2:mem:$DATABASE_DIR;MODE=DB2"
}
