package SortThePCInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SortTheComputerInfo {
	  static String ComputerNo = null;//����������
	  static List<Map<String,Double>> ComputerPriceList = new ArrayList<>();//��list���鱣��
	  static double EvaluePrice = 0.0;//����ĵ���
	  static String ActualPrice = "";//ʵ�ʵ���
	  static double HadDiskPrice = 0.0;//Ӳ������
	  static String ComputerName = "";//��������   
	  static int TempNo = 0;
	  //ƥ�������ļ۸�  
	  public static void MatchComputerPrice(String str){
		    
		    PcInfoMatchPrice InfoTypePrice = new PcInfoMatchPrice();
		    if(str.charAt(0) >= '1' && str.charAt(0) <= '9'){
		    	if(!str.equals(ComputerNo)){
		    		if(ComputerNo != null){
		    			Map<String,Double> ComputerPrice = new HashMap<String,Double>();//����������ƺͼ۸�
		    			
		    			ComputerPrice.put(ComputerNo+ComputerName,(EvaluePrice/Double.parseDouble(ActualPrice)));
		    			ComputerPriceList.add(ComputerPrice);
		    			EvaluePrice = 0.0;
		    			System.out.println(ComputerNo);//���������ź�����
				    	System.out.println(ComputerNo+ComputerName+" "+ComputerPriceList.get(Integer.parseInt(ComputerNo)-1).get(ComputerNo+ComputerName));}//��������Ȩֵ		
		    		
		    		TempNo ++;
		    	    ComputerNo = Integer.toString(TempNo);
		    	    ComputerName = "";}
		    	
		    }
		    else{
		    	int len = -1;
		    	if(str.indexOf("CPU�ͺ�=Intel ") >= 0){//ƥ��cpu�ͺ�
		    		
		    		if((len = str.indexOf("���i3")) >= 0){//ƥ�䵽core i3
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
		    		
		    		if((len = str.indexOf("���i5")) >= 0){//ƥ�䵽core i5
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
		    		
		    		if((len = str.indexOf("���i7")) >= 0){//ƥ�䵽core i5
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
		    	
		    	else if((len = str.indexOf("�ڴ�����=")) >= 0){//ƥ���ڴ�����
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
		    	
		    	else if((len =  str.indexOf("Ӳ������=")) >= 0){//ƥ��Ӳ������
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
		    		else if( str.indexOf("��") > 0){
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
		    	
		    	else if((len =  str.indexOf("Ӳ������=")) >= 0){
		    		if(str.indexOf("���") > 0){
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
		    	
		    	else if((len =  str.indexOf("�Դ�����=")) >= 0){//ƥ���Կ�����
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
		    	
		    	else if((len =  str.indexOf("[PRC]=")) >= 0){//ƥ����ʵ�۸�
		    		len = len + 6;
		    		ActualPrice = str.substring(len);
		    		System.out.println("��ʵ�۸�"+ActualPrice+"\n");
		    	}
		    	else{
		    		ComputerName = str;
		    	}
		    }
	  }
	  //�������������ݳ�������
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
