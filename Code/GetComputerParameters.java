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
	  //ץȡ�ؼ����ݺ���
	  public static String GetPCParameters(String str,String ComputerNo){
		  
		    String ComputerInfo = null;
		    String ComputerCPU = "";//ƥ�����cpu�ͺ�
		    String ComputerMemSize = "";//�ڴ�����
		    String ComputerHardDiskSize = "";//Ӳ������
		    String ComputerHardDiskType = "";//Ӳ������
		    String ComputerAmdSize = "";//�Կ�����
		    String ComputerName = "";//��������
		    
		    String ComputerNoRegEx = "\\[.*?\\]";//ƥ�������ŵĵ������
		    String ComputerCPURegEx = "CPU�ͺ�.*?$";//ƥ�����cpu�ͺ�
		    String ComputerMemSizeRegEx = "\\]�ڴ�����.*?$";//�ڴ�����
		    String ComputerHardDiskSizeRegEx = "Ӳ������.*?$";//Ӳ������
		    String ComputerHardDiskTypeRegEx = "Ӳ������.*?$";//Ӳ������
		    String ComputerAmdSizeRegEx = "�Դ�����.*?$";//�Կ�����
		    String ComputerNameRegEx = ">.*?$";//��������
		    
		    //������ʽƥ��������
		    Pattern pNo = Pattern.compile(ComputerNoRegEx);
		    Matcher matcherNo = pNo.matcher(str);
		    
		    //ƥ�����cpu�ͺ�
		    Pattern pCPU = Pattern.compile(ComputerCPURegEx);
		    Matcher matcherCPU = pCPU.matcher(str);
		    
		    //ƥ���ڴ�����
		    Pattern pMem = Pattern.compile(ComputerMemSizeRegEx);
		    Matcher matcherMem = pMem.matcher(str);
		    
		    //ƥ��Ӳ������
		    Pattern pHardDisk = Pattern.compile(ComputerHardDiskSizeRegEx);
		    Matcher matcherHardDisk = pHardDisk.matcher(str);
		    
		    //ƥ��Ӳ������
		    Pattern pHardDiskType = Pattern.compile(ComputerHardDiskTypeRegEx);
		    Matcher matcherHardDiskType = pHardDiskType.matcher(str);
		    
		    //�Դ�����
		    Pattern pAmd = Pattern.compile(ComputerAmdSizeRegEx);
		    Matcher matcherAmd = pAmd.matcher(str);
		    
		    //��������
		    Pattern pName = Pattern.compile(ComputerNameRegEx);
		    Matcher matcherName = pName.matcher(str);
		    
		    //�õ��������
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
		    
		    //�õ�����cpu�ͺ�
		    while(matcherCPU.find()){
		    	String TempComputerCPU = matcherCPU.group(0);
		    	if(!ComputerCPU.equals(TempComputerCPU)){
		    		ComputerCPU = TempComputerCPU; 
		    		ComputerInfo = ComputerCPU;
		    		System.out.println(ComputerCPU);
		    	}
		        break;
		    }
		    
		    //�õ������ڴ�����
		    while(matcherMem.find()){
		    	String TempComputerMem = matcherMem.group(0).replace("]","");
		    	if(!ComputerMemSize.equals(TempComputerMem)){
		    		ComputerMemSize = TempComputerMem; 
		    		ComputerInfo = ComputerMemSize;
		    		System.out.println(ComputerMemSize);
		    	}
		        break;
		    }
		    
		    //�õ�����Ӳ������
		    while(matcherHardDisk.find()){
		    	String TempComputerHardDisk = matcherHardDisk.group(0);
		    	if(!ComputerHardDiskSize.equals(TempComputerHardDisk)){
		    		ComputerHardDiskSize = TempComputerHardDisk; 
		    		ComputerInfo = ComputerHardDiskSize;
		    		System.out.println(ComputerHardDiskSize);
		    	}
		        break;
		    }
		    
		    //�õ�����Ӳ���ͺ�
		    while(matcherHardDiskType.find()){
		    	String TempComputerHardDiskType = matcherHardDiskType.group(0);
		    	if(!ComputerHardDiskType.equals(TempComputerHardDiskType)){
		    		ComputerHardDiskType = TempComputerHardDiskType; 
		    		ComputerInfo = ComputerHardDiskType;
		    		System.out.println(ComputerHardDiskType);
		    	}
		        break;
		    }
		    
		    //�õ������Կ�����
		    while(matcherAmd.find()){
		    	String TempComputerAmd = matcherAmd.group(0);
		    	if(!ComputerAmdSize.equals(TempComputerAmd)){
		    		ComputerAmdSize = TempComputerAmd; 
		    		ComputerInfo = ComputerAmdSize;
		    		System.out.println(ComputerAmdSize);
		    	}
		        break;
		    }
		    
		    //�õ���������
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
	   //�������������ݳ�������
	  public static void main(String[] args){
		    try { 
	            Scanner in = new Scanner(new File("D:/beihangUniversity/�μ�/���������ϵ�ṹʵ��/����/1.txt")); 
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
