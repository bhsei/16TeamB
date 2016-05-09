package org.apache.hadoop.examples;

import java.io.IOException;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class ComputerExtractReducer extends Reducer<Text,Text,Text,Text> {
	public void reduce(Text key,Iterable<Text>values,Context context)throws IOException,InterruptedException{
		//String key2=key.toString();
		String result="";
		for(Text value:values){
			String newValue=value.toString();
			result+=newValue;
			result+="\n";
		}
		result+="\n";
		context.write(key,new Text(result));
	}

}
