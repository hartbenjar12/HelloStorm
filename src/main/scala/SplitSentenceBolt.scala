import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;


/**
 * Created by benjarman on 11/15/15.
 */
class SplitSentenceBolt extends  BaseBasicBolt{
  def execute(t: Tuple, out: BasicOutputCollector) = {
    val s = t.getString(0)
    s.split(" ").foreach(r=> {out.emit(new Values(r))})
  }
  def declareOutputFields(fields: OutputFieldsDeclarer) = {
    fields.declare(new Fields("words"))
  }
}
