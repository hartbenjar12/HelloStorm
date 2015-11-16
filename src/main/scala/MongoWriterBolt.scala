/**
 * Created by benjarman on 11/15/15.
 */
import backtype.storm.topology.BasicOutputCollector
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseBasicBolt
import backtype.storm.tuple.{Fields, Tuple, Values}
import com.mongodb.BasicDBObject

class MongoWriterBolt extends BaseBasicBolt{

  def execute(t: Tuple, out: BasicOutputCollector): Unit ={
    val m = new MongoService()
    val DB = m.getCollection("word_count")
    val word = t.getString(0)
    val count = t.getInteger(1)
    val dbObject = new BasicDBObject()
    dbObject.append("_id", word)
    dbObject.append("count", count)
    DB.save(dbObject)
    m.close()

  }

  def declareOutputFields(o: OutputFieldsDeclarer): Unit ={

  }

}
