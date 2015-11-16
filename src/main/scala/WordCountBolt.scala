/**
 * Created by benjarman on 11/15/15.
 */

import backtype.storm.topology.BasicOutputCollector
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseBasicBolt
import backtype.storm.tuple.{Fields, Tuple, Values}

class WordCountBolt extends BaseBasicBolt {

  //I feel like this should not work, as we are on a distributed system but we will see
  val count = scala.collection.mutable.Map[String, Int]()

  def execute(t: Tuple, out: BasicOutputCollector): Unit ={
    val word = t.getStringByField("words")
    val cout = count.getOrElse(word, 0)
    val newCount = cout + 1
    count += word -> newCount
    out.emit(new Values(word.asInstanceOf[Object], newCount.asInstanceOf[Object]))
  }

  def declareOutputFields(o: OutputFieldsDeclarer): Unit ={
    o.declare(new Fields("word", "count"))
  }
}
