package org.apache.hadoop.examples;

import java.io.IOException;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class ComputerExtractMapper extends Mapper<LongWritable,Text,Text,Text> {
	
	public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException{
		String[] line=value.toString().split("\n");
		int j=line.length;
		for(int k=0;k<j;k++){
			//System.out.println(line[k]);
		if(line[k].contains("内存")||line[k].contains("显存")||line[k].contains("CPU")||line[k].contains("硬盘")){
			int i=1;
			String key2="";
			while(line[k].charAt(i)!=']'){
				key2+=line[k].charAt(i);
				i++;
			}
			i++;
			while(line[k].charAt(i)!=']'){
				i++;
			}
			i++;
			while(line[k].length()<=(i-1)&&line[k].charAt(i)!=']'){
				i++;
			}
			//if(line[k].)
			//i=i+3;
			
			if(line[k].contains("CPU")){
				i=i+3;
			}
			else{
				i=i+4;
			}
			String info=line[k].substring(i);
			context.write(new Text(key2), new Text(info));
			System.out.println(key2+"   "+info);
		}
		else{
			continue;
		}
		
	}
	}

}

