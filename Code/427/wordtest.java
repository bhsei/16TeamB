package org.apache.hadoop.test;

import java.io.IOException;
//import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.examples.WordCount;
//import org.apache.hadoop.examples.WordCount.IntSumReducer;
//import org.apache.hadoop.examples.WordCount.TokenizerMapper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
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
     extends Mapper<LongWritable,Text,Text,Text>{

  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();
  String ComputerNo = "";
  int num =0;
  String info = "";
 // int acc = 0;
  public void map(LongWritable key,Text value,Context context
                  ) throws IOException, InterruptedException {
	 //System.out.println(value.toString());
	 String result = value.toString();
	 GetComputerParameters GetInfo = new GetComputerParameters();
	 ComputerNo = GetInfo.GetComputerInfo(result,ComputerNo);
	// System.out.println(ComputerNo);
	 boolean mat = ComputerNo.matches("\\d+");
	 //System.out.println(ComputerNo);
	 if(mat && Integer.parseInt(ComputerNo) == num + 1)
	 {
		 num ++;
		 //info = "";
		//System.out.println( ComputerNo);
	 }
	 else if(!mat)
	 {
		 //System.out.println(ComputerNo);
		 //System.out.println(num);
		 //context.write(new Text(ComputerNo),new IntWritable(num-1));
		 context.write(new Text(Integer.toString(num-1)),new Text(ComputerNo));
		 //word.set(ComputerNo);
		 //context.write(word, one);
	 }

   /* while (itr.hasMoreTokens()) {
      word.set(itr.nextToken());
      context.write(word, one);
    }
    */
  }
}

public static class IntSumReducer 
     extends Reducer<Text,Text,Text,Text> {
//  private IntWritable result = new IntWritable();
  //int i = 0;
 // int t= 0;
     int t1 =0;
	String Tempkey = "$$$$";
	static Map<String,Double> ComputerPrice = new HashMap<String,Double>();//保存电脑名称和价格
	SortTheComputerInfo SortInfo = new SortTheComputerInfo();
  public void reduce(Text key,Iterable<Text>values,Context context
                     ) throws IOException, InterruptedException {
   // int sum = 0;
	  //System.out.println(key);
	 /* for(IntWritable val : values) {
		  System.out.println(val);
	  }*/
	  //int i = 10;
	  //System.out.println(key);
	
    for (Text val : values) {
    	    if(!Tempkey.equals(key.toString())){
    	    	ComputerPrice = SortInfo.GetReduceInfo(Tempkey);
    	    } else{
    	    	ComputerPrice = SortInfo.GetReduceInfo(val.toString());
    	    }
    	    Tempkey = key.toString();
  	}
   t1++; 
   if(t1 == 6308){
	   ArrayList<Map.Entry<String, Double>> SortComputerPriceList = new ArrayList<Map.Entry<String,Double>>(); 
		  ComputerQuickSort PCQuickSort = new ComputerQuickSort();
		  SortComputerPriceList = PCQuickSort.QuickSort(ComputerPrice);
		  
		  int t = 0;
		  System.out.println("---------Rank---------");
		  for (int i = 0; i < SortComputerPriceList.size(); i++) {  
		      //newMap.put(list.get(i).getKey(), list.get(i).getValue());  
		      System.out.println(SortComputerPriceList.get(i).getKey() + "  " + SortComputerPriceList.get(i).getValue());
		      t++;
		      if(i == 1000)
		    	  break;
		  } 
		  System.out.println("---------Rank---------");
   }
	   
    //result.set(sum);
  }
 // 
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
  job.setNumReduceTasks(1);
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(Text.class);
  FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
  FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
  System.exit(job.waitForCompletion(true) ? 0 : 1);
 // IntSumReducer t = new IntSumReducer();
  //t.TempPrint();
  
}
}

