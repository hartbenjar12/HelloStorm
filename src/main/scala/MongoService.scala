import com.mongodb.casbah.{MongoClient, MongoCollection}

/**
 * Created by benjarman on 11/15/15.
 */
class MongoService() {

  private val mongoClient = MongoClient("192.168.0.5", 27017)

  private val database = mongoClient("hello_storm")

  def getCollection(name: String): MongoCollection ={
    database(name)
  }


  def close(): Unit ={
    mongoClient.close()
  }
}
