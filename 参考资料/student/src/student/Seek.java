package student;

public class Seek {
	public WriterRead wr=new WriterRead();
	   public Information ints=new Information();
	    public String[][] students=wr.getxueshengs(); 
	    public String[] interest=ints.getinterest();
	    @SuppressWarnings("finally")
		public String[][] findstudents(String x)
	    {
	    	int n=students.length;
	    	int k=0;
	    	String[][] finds=new String[n][7];
	    	try {
	    	for(int c=0;c<students.length;c++)
	    	{
	    		for(int j=0;j<students[0].length-1;j++)
	    		{
	    			if(students[c][j].contains(x))
	    			{
	    				for(int l=0;l<students[c].length;l++)
	    				{
	    					finds[k][l]=students[c][l];
	    				}
	    				finds[k][6]= String.valueOf(c);	    				
	    				k++;
	    				break;
	    			}
	    			
	    		}
	    	}
	    	}
	    	catch(Exception e){
	    	}
	    	finally{
			return finds;}
	    	
	    }
	    @SuppressWarnings("finally")
		public String[][] findbyinterest(String x)
	    {
	    	int n=students.length;
	    	int k=0;
	    	String[][] finds=new String[n][7];
	    	{
	    		try {
	    		for(int i=0;i<students.length;i++)
	    		{
	    			String[] in=students[i][5].split("\\;");
	    			int[] ini=new int[in.length];
	    			for(int j=0;j<in.length;j++)
	    			ini[j]=Integer.parseInt(in[j]);
	    			for(int j=0;j<in.length;j++)
	    			{
	    				if(x.equals(interest[ini[j]]))
	    				{
	    					for(int l=0;l<students[i].length;l++)
		    				{
		    					finds[k][l]=students[i][l];
		    				}
		    				finds[k][6]= String.valueOf(i);
		    				k++;
		    				break;
	    				}
	    			}
	    		}
	    	}
	    	
	    	catch(Exception e){
	    	}
	    	finally{
			return finds;}
	    	}
	    }
	    
	    

}
