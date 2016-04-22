package SortThePCInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SortTheComputerInfo {
	  static String ComputerNo = null;//保存电脑序号
	  static List<Map<String,Double>> ComputerPriceList = new ArrayList<>();//用list数组保存
	  static double EvaluePrice = 0.0;//估算的单价
	  static String ActualPrice = "";//实际单价
	  static double HadDiskPrice = 0.0;//硬盘容量
	  static String ComputerName = "";//电脑名字   
	  static int TempNo = 0;
	  //匹配计算机的价格  
	  public static void MatchComputerPrice(String str){
		    
		    PcInfoMatchPrice InfoTypePrice = new PcInfoMatchPrice();
		    if(str.charAt(0) >= '1' && str.charAt(0) <= '9'){
		    	if(!str.equals(ComputerNo)){
		    		if(ComputerNo != null){
		    			Map<String,Double> ComputerPrice = new HashMap<String,Double>();//保存电脑名称和价格
		    			
		    			ComputerPrice.put(ComputerNo+ComputerName,(EvaluePrice/Double.parseDouble(ActualPrice)));
		    			ComputerPriceList.add(ComputerPrice);
		    			EvaluePrice = 0.0;
		    			System.out.println(ComputerNo);//输出电脑序号和名字
				    	System.out.println(ComputerNo+ComputerName+" "+ComputerPriceList.get(Integer.parseInt(ComputerNo)-1).get(ComputerNo+ComputerName));}//输出计算的权值		
		    		
		    		TempNo ++;
		    	    ComputerNo = Integer.toString(TempNo);
		    	    ComputerName = "";}
		    	
		    }
		    else{
		    	int len = -1;
		    	if(str.indexOf("CPU型号=Intel ") >= 0){//匹配cpu型号
		    		
		    		if((len = str.indexOf("酷睿i3")) >= 0){//匹配到core i3
		    			len = len + 5;
		    			char ch = str.charAt(len);
		    			if(ch == '4'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i3;
		    			}
		    			else if(ch == '5'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel5_i3;
		    			}
		    			else if(ch == '6'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel6_i3;
		    			}
		    			else{
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i3;
		    			}
		    			
		    			 System.out.println("i3 "+EvaluePrice+"\n");
		    		}
		    		
		    		if((len = str.indexOf("酷睿i5")) >= 0){//匹配到core i5
		    			len = len + 5;
		    			char ch = str.charAt(len);
		    			if(ch == '4'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i5;
		    			}
		    			else if(ch == '5'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel5_i5;
		    			}
		    			else if(ch == '6'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel6_i5;
		    			}
		    			else{
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i5;
		    			}
		    			
		    			System.out.println("i5 "+EvaluePrice+"\n");
		    		}
		    		
		    		if((len = str.indexOf("酷睿i7")) >= 0){//匹配到core i5
		    			len = len + 5;
		    			char ch = str.charAt(len);
		    			if(ch == '4'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i7;
		    			}
		    			else if(ch == '5'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel5_i7;
		    			}
		    			else if(ch == '6'){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel6_i7;
		    			}
		    			else{
		    				EvaluePrice = EvaluePrice + InfoTypePrice.intel4_i7;
		    			}
		    			
		    			System.out.println("i7 " + EvaluePrice+"\n");
		    		}
		    	}
		    	
		    	else if((len = str.indexOf("内存容量=")) >= 0){//匹配内存容量
		    		len = len + 5;
		    		int len_end = str.indexOf("G");
		    		String ch = str.substring(len,len_end);
		    		if(Integer.parseInt(ch) == 2){
		    			EvaluePrice = EvaluePrice + InfoTypePrice.Mem_2G;
		    		}
		    		else if(Integer.parseInt(ch) == 4){
		    			EvaluePrice = EvaluePrice + InfoTypePrice.Mem_4G;
		    		}
		    		else if(Integer.parseInt(ch) == 8){
		    			EvaluePrice = EvaluePrice + InfoTypePrice.Mem_8G;
		    		}
		    		else if(Integer.parseInt(ch) == 16){
		    			EvaluePrice = EvaluePrice + InfoTypePrice.Mem_16G;
		    		}
		    		else{
		    			EvaluePrice = EvaluePrice + InfoTypePrice.Mem_2G;
		    		}
		    		
		    		System.out.println("Mem "+EvaluePrice+"\n");
		    	}
		    	
		    	else if((len =  str.indexOf("硬盘容量=")) >= 0){//匹配硬盘容量
		    		len = len + 5;
		    		int len_Gend = -1;
		    		int len_Tend = -1;
		    		int len_add = str.indexOf("+");
		    		double SizeGB = 0.0;
		    		
		    		if(len_add > 0){
		    			len_add = len_add+1;
		    			len_Gend = str.indexOf("GB");
		    			len_Tend = str.indexOf("TB");
		    			if(len_Gend > len_add){
		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len_add,len_Gend));
		    			}
		    			if(len_Tend > len_add){
		    				 SizeGB = SizeGB + Double.parseDouble(str.substring(len_add,len_Tend))*1000;
		    			}
		    			if(len_Tend < len_add && len_Tend > 0){
		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Tend))*1000;
		    			}
		    			if(len_Gend < len_add && len_Gend > 0){
		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Gend));
		    			}
		    			
//		    			if((len_Gend = str.indexOf("GB",len)) > 0)
//		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Gend));
//		    			else if((len_Tend = str.indexOf("TB",len)) > 0)
//		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Tend))*1000;
//		    			else
//		    				SizeGB = SizeGB + 300;
//		    			
//		    			if((len_Tend = str.indexOf("TB",len_add)) > 0)
//		    			   SizeGB = SizeGB + Double.parseDouble(str.substring(len_add,len_Tend))*1000;
//		    			else if((len_Gend = str.indexOf("GB",len_add)) > 0)
//		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len_add,len_Gend));
		    		}
		    		else if( str.indexOf("×") > 0){
		    			SizeGB = 500;
		    		}
		    		else{
		    			if((len_Gend = str.indexOf("GB")) > 0)
		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Gend));
		    			else if((len_Tend = str.indexOf("TB")) > 0)
		    				SizeGB = SizeGB + Double.parseDouble(str.substring(len,len_Tend))*1000;
		    		}
		    			
		    		HadDiskPrice = SizeGB;
		    		System.out.println("HadDiskPrice "+HadDiskPrice+"\n");
		    	}
		    	
		    	else if((len =  str.indexOf("硬盘描述=")) >= 0){
		    		if(str.indexOf("混合") > 0){
		    			EvaluePrice = EvaluePrice + HadDiskPrice*3;
		    		}
		    		else if(str.indexOf("SSD") > 0){
		    			EvaluePrice = EvaluePrice + HadDiskPrice*10;
		    		}
		    		else{
		    			EvaluePrice = EvaluePrice + HadDiskPrice*5;
		    		}
		    		
		    		System.out.println("yingpan "+EvaluePrice+"\n");
		    		HadDiskPrice = 0.0;
		    	}
		    	
		    	else if((len =  str.indexOf("显存容量=")) >= 0){//匹配显卡容量
		    		len = len + 5;
		    		int len_Mend = str.indexOf("M");
		    		int len_Gend = str.indexOf("G"); 
		    		String GraphCard = "0";
		    		if(str.indexOf("M") > 0){
		    			EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_512MB;
		    		}
		    		else if(str.indexOf("G") > 0){
		    			GraphCard = str.substring(len,len_Gend);
		    			if(Integer.parseInt(GraphCard) == 1){
		    				EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_1G;
		    			}
		    			else if(Integer.parseInt(GraphCard) == 2)
		    				EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_2G;
		    			else if(Integer.parseInt(GraphCard) == 4)
		    				EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_4G;
		    			else
		    				EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_512MB;
		    		}
		    		else
		    			EvaluePrice = EvaluePrice + InfoTypePrice.GraphCard_512MB;
		    		System.out.println("xiancun "+EvaluePrice+"\n");
		    	}
		    	
		    	else if((len =  str.indexOf("[PRC]=")) >= 0){//匹配真实价格
		    		len = len + 6;
		    		ActualPrice = str.substring(len);
		    		System.out.println("真实价格"+ActualPrice+"\n");
		    	}
		    	else{
		    		ComputerName = str;
		    	}
		    }
	  }
	  //主函数，将数据初步处理
	  public static void main(String[] args){
		    try { 
	            Scanner in = new Scanner(new File("Result.txt"),"UTF-8"); 
	            //String ComputerNo = "";
	            //int t = 0;
	            
	            while (in.hasNextLine()) {
	            	String str = in.nextLine();
	            	//System.out.println(str);
	                MatchComputerPrice(str);
	               // t++;
	                //if(t == 20)
	                	//break;
	            } 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } 
    }
}
