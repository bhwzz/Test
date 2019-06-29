package student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriterRead {
	 static int increacenum=10;
	 static String [][]xueshengs=new String[40][];
	 WriterRead()
	 {
		 read();
	 }
	 public void read()
	{
	        String pathname = "students.txt";
			File file = new File(pathname);
			 BufferedReader reader = null;  
			 String [] temp = new String [40];
		        int line=0;  
		        try{  
		                reader=new BufferedReader(new FileReader(file));  
		                while((temp[line]=reader.readLine())!=null){  
		                	if(line==0)
		                	{
		                		char ch=temp[0].charAt(0);
		                		int  num = ch - '0'; 
		                		xueshengs=new String[40][num];
		                		
		                	}
		                	else {
		                    if(line>=temp.length-1) {
		                    	temp=increa(temp,temp.length);
		                    }
		                    String[]  one= temp[line].split("\\|");
		                    setxueshengs(one,line-1);
		                	}
		                    line++;  
		                }  
		               
		        }  
		        catch(Exception e){  
		            e.printStackTrace();  
		        }  
		}
	    public void write(String x) {
	    	String pathname = "students.txt";
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

			
		
	    String[] increa(String[] a,int num)
		{
			String [] b = new String [num+increacenum];
			xueshengs=new String[num+increacenum][8];
			System.arraycopy(b,0,a,0,num);
			return b;
			
		}
		void setxueshengs(String[] a,int x)
		{
			for(int i=0;i<a.length;i++)
			{
				xueshengs[x][i]=a[i];			}
		}
		String[][] getxueshengs(){
			return xueshengs;
			
		}
		public  int change( int n, String newStr)  {
			read();
			String[][] dd=new String[xueshengs.length][xueshengs[0].length];
		     int k=0;
			  FileWriter writer = null;  
		        try {     
		            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
		            writer = new FileWriter("students.txt", false); 
		            for(int i=0;i<xueshengs.length;i++)
		            {
		            	if(i==n)
		            	{
		            		String[] kk=newStr.split("\\|");
		            		for(int j=0;j<dd[i].length;j++)
		            			dd[i][j]=kk[j];
		            	}
		            	else
		            		for(int j=0;j<xueshengs[i].length;j++)
		            			dd[i][j]=xueshengs[i][j];
		            	
		            }
	            BufferedWriter bw = new BufferedWriter(writer);
	            bw.write(String.valueOf(xueshengs[0].length));
	            int j=0;
	           while(dd[j][0]!=null)
	            {	            	
		            bw.write("\r\n");
		            bw.write(dd[j][0]+"|"+dd[j][1]+"|"+dd[j][2]+"|"+dd[j][3]+"|"+dd[j][4]+"|"+dd[j][5]);
		            j++;
	            	
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
		public  int delete( int n)  {
			read();
			if(n>xueshengs.length)
				return 0;
			String[][] dd=new String[xueshengs.length-1][xueshengs[0].length];
		     int k=0;
			  FileWriter writer = null;  
		        try {     
		            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示覆盖的方式写入
		            writer = new FileWriter("students.txt", false); 
		            int i=0;
		            int m=0;
		            while(xueshengs[i][0]!=null)
		            {
		            	
		            	if(i==n)
		            	{
		            		m--;
		            	}
		            	else
		            		for(int j=0;j<xueshengs[i].length;j++)
		            			dd[m][j]=xueshengs[i][j];
		            			
		            	m++;
		            	i++;
		            }
		            System.out.println(dd[0][4]);
	            BufferedWriter bw = new BufferedWriter(writer);
	            bw.write(Integer.toString(xueshengs[0].length));
	            int h=0;
	            while(dd[h][0]!=null)
	            {	            	
		            bw.write("\r\n");
		            bw.write(dd[h][0]+"|"+dd[h][1]+"|"+dd[h][2]+"|"+dd[h][3]+"|"+dd[h][4]+"|"+dd[h][5]);
		            h++;
	            	
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
