/**
 * Created by benjarman on 11/15/15.
 */

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import org.apache.log4j.BasicConfigurator;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

object StormRunner extends App {

  val topic = "test"
  val workers = 1

  def createConfig(local: Boolean): Config = {

    val conf = new Config()
    conf.setDebug(true)

    if(local){
      conf.setMaxTaskParallelism(workers)
    }else{
      conf.setNumWorkers(workers)
    }

    conf
  }

  def createTopology(): StormTopology = {
    val kafkaConf = new SpoutConfig(
      new ZkHosts("localhost:2181"),
      topic,
      "/storm_demo",
      "KafkaSpount"
    )
    kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme)
    val topology = new TopologyBuilder

    topology.setSpout("kafka_spout", new KafkaSpout(kafkaConf), 4)

    topology.setBolt("SplitSentence", new SplitSentenceBolt(), 4)
      .shuffleGrouping("kafka_spout")

    topology.setBolt("WordCount", new WordCountBolt(), 4)
      .shuffleGrouping("SplitSentence")

    topology.setBolt("MongoWriter", new MongoWriterBolt(), 4)
      .shuffleGrouping("WordCount")

    topology.createTopology()
  }

  BasicConfigurator.configure()

  StormSubmitter.submitTopology(
    "kafka_sample",
  createConfig(false),
  createTopology())

}
