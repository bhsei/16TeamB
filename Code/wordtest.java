package org.apache.hadoop.test;

import java.io.IOException;
//import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.examples.WordCount;
//import org.apache.hadoop.examples.WordCount.IntSumReducer;
//import org.apache.hadoop.examples.WordCount.TokenizerMapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

class data{
	private String name;
	private int ID;
	private String memory_size;
	private String hard_des;
	private String RAM_size;
	private String hard_cap;
	public data(String name,int id,String mem,String hard_ds,String hard_c,String RAM_s)
	{
		this.name = name;
		this.ID = id;
		this.hard_des = hard_ds;
		this.hard_cap = hard_c;
		this.RAM_size = RAM_s;
	}
	public String toString()
	{
		return name+" "+ID+" "+hard_des+" "+hard_cap + " "+RAM_size; 
	}
}


public class wordtest {
	 public static class TokenizerMapper 
     extends Mapper<Object, Text, Text, IntWritable>{

  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();
  String ComputerNo = "";
  

  public void map(Object key, Text value, Context context
                  ) throws IOException, InterruptedException {
	 //System.out.println(value.toString());
	 String result = value.toString();
	 //for()
	// int i = result.indexOf("\r\n");
	 //System.out.print(i);
	 //int t = 0; 
     //String []result = value.toString().split("\r\n");
		 //System.out.println(result);

	// for(int i = 0;i <10;i++)
	 //System.out.println(result);
	
	 GetComputerParameters GetInfo = new GetComputerParameters();
	 ComputerNo = GetInfo.GetComputerInfo(result,ComputerNo);
	 System.out.println( ComputerNo);
	 //=========test
	 /*int first = 0,end = 0,t = 0;
	 while(true){
		 end = result.indexOf('\n',first);
		 System.out.print(end);
		 String TempResult = result.substring(first, end);
		 first = end + 1;
		 System.out.println(first);
		 t++;
		 if(t == 1000)
			 break;
	 }*/
	 //======
	 
	//System.out.println(value.toString());
  //  word.set(value.toString());
	//context.write(word,one);
	//int acc = 1;
	/* for(int i = 0;i < result.length;i++)
	{
		if(Integer.parseInt(result[i]) ==acc)
		{
			acc++;
			data d =new data(result[i+5] , acc , result[i+1] , result[i+3] , result[i+2],result[i+4]);
			i += 4;
			word.set(d.toString());
			System.out.println(d.toString());
			context.write(word, one);
		}
	}
*/
   /* while (itr.hasMoreTokens()) {
      word.set(itr.nextToken());
      context.write(word, one);
    }
    */
  }
}

public static class IntSumReducer 
     extends Reducer<Text,IntWritable,Text,IntWritable> {
  private IntWritable result = new IntWritable();

  public void reduce(Text key, Iterable<IntWritable> values, 
                     Context context
                     ) throws IOException, InterruptedException {
    int sum = 0;
    for (IntWritable val : values) {
      sum += val.get();
    }
    result.set(sum);
    context.write(key, result);
  }
}

public static void main(String[] args) throws Exception {
  Configuration conf = new Configuration();
  String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
  if (otherArgs.length != 2) {
    System.err.println("Usage: wordcount <in> <out>");
    System.exit(2);
  }
  Job job = new Job(conf, "word count");
  job.setJarByClass(wordtest.class);
  job.setMapperClass(TokenizerMapper.class);
  job.setCombinerClass(IntSumReducer.class);
  job.setReducerClass(IntSumReducer.class);
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(IntWritable.class);
  FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
  FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
  System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}

