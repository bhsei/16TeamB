package GetComputerInformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetComputerParameters {
	  //抓取关键数据函数
	  public static String GetPCParameters(String str,String ComputerNo){
		  
		    String ComputerInfo = null;
		    String ComputerCPU = "";//匹配电脑cpu型号
		    String ComputerMemSize = "";//内存容量
		    String ComputerHardDiskSize = "";//硬盘容量
		    String ComputerHardDiskType = "";//硬盘描述
		    String ComputerAmdSize = "";//显卡容量
		    String ComputerName = "";//电脑名字
		    
		    String ComputerNoRegEx = "\\[.*?\\]";//匹配中括号的电脑序号
		    String ComputerCPURegEx = "CPU型号.*?$";//匹配电脑cpu型号
		    String ComputerMemSizeRegEx = "\\]内存容量.*?$";//内存容量
		    String ComputerHardDiskSizeRegEx = "硬盘容量.*?$";//硬盘容量
		    String ComputerHardDiskTypeRegEx = "硬盘描述.*?$";//硬盘描述
		    String ComputerAmdSizeRegEx = "显存容量.*?$";//显卡容量
		    String ComputerNameRegEx = ">.*?$";//电脑名字
		    
		    //正则表达式匹配电脑序号
		    Pattern pNo = Pattern.compile(ComputerNoRegEx);
		    Matcher matcherNo = pNo.matcher(str);
		    
		    //匹配电脑cpu型号
		    Pattern pCPU = Pattern.compile(ComputerCPURegEx);
		    Matcher matcherCPU = pCPU.matcher(str);
		    
		    //匹配内存容量
		    Pattern pMem = Pattern.compile(ComputerMemSizeRegEx);
		    Matcher matcherMem = pMem.matcher(str);
		    
		    //匹配硬盘容量
		    Pattern pHardDisk = Pattern.compile(ComputerHardDiskSizeRegEx);
		    Matcher matcherHardDisk = pHardDisk.matcher(str);
		    
		    //匹配硬盘描述
		    Pattern pHardDiskType = Pattern.compile(ComputerHardDiskTypeRegEx);
		    Matcher matcherHardDiskType = pHardDiskType.matcher(str);
		    
		    //显存容量
		    Pattern pAmd = Pattern.compile(ComputerAmdSizeRegEx);
		    Matcher matcherAmd = pAmd.matcher(str);
		    
		    //电脑名字
		    Pattern pName = Pattern.compile(ComputerNameRegEx);
		    Matcher matcherName = pName.matcher(str);
		    
		    //得到电脑序号
		    while(matcherNo.find()){
		    	String TempComputerNo = matcherNo.group(0).replace("[", "");
		    	TempComputerNo = TempComputerNo.replace("]", "");
		    	if(!ComputerNo.equals(TempComputerNo)){
		    		ComputerNo = TempComputerNo; 
		    		ComputerInfo = ComputerNo;
		    		System.out.println(ComputerNo);
		    	}
		        break;
		    }
		    
		    //得到电脑cpu型号
		    while(matcherCPU.find()){
		    	String TempComputerCPU = matcherCPU.group(0);
		    	if(!ComputerCPU.equals(TempComputerCPU)){
		    		ComputerCPU = TempComputerCPU; 
		    		ComputerInfo = ComputerCPU;
		    		System.out.println(ComputerCPU);
		    	}
		        break;
		    }
		    
		    //得到电脑内存容量
		    while(matcherMem.find()){
		    	String TempComputerMem = matcherMem.group(0).replace("]","");
		    	if(!ComputerMemSize.equals(TempComputerMem)){
		    		ComputerMemSize = TempComputerMem; 
		    		ComputerInfo = ComputerMemSize;
		    		System.out.println(ComputerMemSize);
		    	}
		        break;
		    }
		    
		    //得到电脑硬盘容量
		    while(matcherHardDisk.find()){
		    	String TempComputerHardDisk = matcherHardDisk.group(0);
		    	if(!ComputerHardDiskSize.equals(TempComputerHardDisk)){
		    		ComputerHardDiskSize = TempComputerHardDisk; 
		    		ComputerInfo = ComputerHardDiskSize;
		    		System.out.println(ComputerHardDiskSize);
		    	}
		        break;
		    }
		    
		    //得到电脑硬盘型号
		    while(matcherHardDiskType.find()){
		    	String TempComputerHardDiskType = matcherHardDiskType.group(0);
		    	if(!ComputerHardDiskType.equals(TempComputerHardDiskType)){
		    		ComputerHardDiskType = TempComputerHardDiskType; 
		    		ComputerInfo = ComputerHardDiskType;
		    		System.out.println(ComputerHardDiskType);
		    	}
		        break;
		    }
		    
		    //得到电脑显卡容量
		    while(matcherAmd.find()){
		    	String TempComputerAmd = matcherAmd.group(0);
		    	if(!ComputerAmdSize.equals(TempComputerAmd)){
		    		ComputerAmdSize = TempComputerAmd; 
		    		ComputerInfo = ComputerAmdSize;
		    		System.out.println(ComputerAmdSize);
		    	}
		        break;
		    }
		    
		    //得到电脑名称
		    while(matcherName.find()){
		    	String TempComputerName = matcherName.group(0).replace("\"", "");
		    	TempComputerName = TempComputerName.replace(">", "");
		    	TempComputerName = TempComputerName.replace(";", "");
		    	if(!ComputerName.equals(TempComputerName)){
		    		ComputerName = TempComputerName; 
		    		ComputerInfo = ComputerName;
		    		System.out.println(ComputerName);
		    	}
		        break;
		    }
		    if(ComputerInfo != null){
				BufferedWriter bw = null;
				try{
					
				    FileWriter fileWriter=new FileWriter("c:\\Result.txt",true);
				    bw = new BufferedWriter(fileWriter);
				    bw.write(ComputerInfo);
				    bw.newLine();  
				
				}catch (IOException e) {
					e.printStackTrace();
				}finally{
				try {
				bw.close();
				} catch (IOException e) {
				e.printStackTrace();
				}
				}

		    }
		    return ComputerNo;
	  }
	   //主函数，将数据初步处理
	  public static void main(String[] args){
		    try { 
	            Scanner in = new Scanner(new File("D:/beihangUniversity/课件/软件工程体系结构实验/数据/1.txt")); 
	            String ComputerNo = "";
	            while (in.hasNextLine()) {
	                String str = in.nextLine();
	                ComputerNo = GetPCParameters(str,ComputerNo);
	            } 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } 

      }
}
