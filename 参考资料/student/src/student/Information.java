package student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Information {;
		 static int increacenum=10;
		 static String []its=new String[40];
		 Information()
		 {
			read();
		 }
		 public static void read()
		{
		        String pathname = "interest.txt";
				File file = new File(pathname);
				 BufferedReader reader = null;  
				 String [] temp = new String [40];
			        int line=0;  
			        try{  
			                reader=new BufferedReader(new FileReader(file));  
			                while((temp[line]=reader.readLine())!=null){  			                	
			                    if(line>=temp.length-1) {
			                    	temp=increa(temp,temp.length);
			                    }
			                    line++;  
			                	}
			                    setinterest(temp,line);
			                 
			               
			        }  
			        catch(Exception e){  
			            e.printStackTrace();  
			        }  
			}
		    public void write(String x) {
		    	String pathname = "interest.txt";
		        try {
		        	FileWriter fw = new FileWriter(pathname,true);
		            BufferedWriter bw = new BufferedWriter(fw);
		            bw.write("\r\n");
		            bw.write(x);		            
		            bw.close();
		            fw.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    	}

				
			
		    public static  String[] increa(String[] a,int num)
			{
				String [] b = new String [num+increacenum];
				its=new String[num+increacenum];
				System.arraycopy(b,0,a,0,num);
				return b;
				
			}
			public static  void setinterest(String[] a,int x)
			{
				its=new String[x];
				for(int i=0;i<x;i++)
					its[i]=a[i];			
			}
			public  String[] getinterest(){
				return its;
				
			}
			public  int change( int n, String newStr)  {
				String[] dd=new String[its.length];
			     int k=0;
				  FileWriter writer = null;  
			        try {     
			            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
			            writer = new FileWriter("interest.txt", false); 
			            for(int i=0;i<its.length;i++)
			            {
			            	if(i==n)
			            	{
			            		dd[i]=newStr;
			            		k++;
			            	}
			            	else
			            	dd[i]=its[i];
			            }
		            BufferedWriter bw = new BufferedWriter(writer);
		            for(int j=0;j<dd.length;j++)
		            {
		            	if(j==0)
		            		bw.write(dd[j]);
		            	else {
			            bw.write("\r\n");
			            bw.write(dd[j]);
		            	}
		            }
			            bw.close();
			            writer.close();    
			        } catch (IOException e) {     
			            e.printStackTrace();     
			        } finally {     
			            try {     			            	
			                if(writer != null){  
			                    writer.close();     
			                }  
			            } catch (IOException e) {     
			                e.printStackTrace();     
			            }     
			        }
					return k;
			}
			
			
			
}


