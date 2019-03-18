import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class pgrk6097 {
private static int nodeCount;
        private static int initialvalue;
        private static String filename;
        private static List<Integer> adjacencyList[];
	private static int edgeCount;
        private static int a,flag;
        private static double d = 0.85;
        int s[];
        private static int errorrate;
        private static List<Integer> j;
        private static int y;
        private static double prl[]; 
        private static double pra[];
        
    public static int[] extracting_nodes6097(String line) throws Exception {
		String []row = line.split(" ");
		
		if (row.length != 2) {
			throw new Exception("Invalid Input found in row:"+row);
		}
		
		int[] result = new int[2];
		result[0] = Integer.parseInt(row[0]);
		result[1] = Integer.parseInt(row[1]);
		
		return result;
	}
    
    public static String reading_File6097(String e) throws IOException {
		 Path path = Paths.get(e);
		 List<String> inputFileList =  Files.readAllLines(path, StandardCharsets.US_ASCII);
		 String inputString = "";
		 for (String string : inputFileList) {
			 inputString = inputString.concat(string) + "\n";
		}
		 		 
		 return inputString;
	}
    
    public static void creating_AdjacencyList6097(String[] rows) throws Exception {
		//Create an adjacency list of the edges
        adjacencyList = new LinkedList[nodeCount];
        for(int o =0; o<nodeCount; o++){
        adjacencyList[o] = new LinkedList<Integer>();   
        }
        int node1, node2;
		for (int index = 1; index <= edgeCount; index++) {
			int[] row = extracting_nodes6097(rows[index]);
                       
			node1 = row[0];
			node2 = row[1];                       
                        adjacencyList[node1].add(node2);	
		}
              
	}
    
    public static void finding_incident_nodes6097(int e){
        
       j = new ArrayList<Integer>();
        for(int r = 0;r<nodeCount;r++){
        int s[] = new int[adjacencyList[r].size()];
            for(int n = 0; n<adjacencyList[r].size();n++){ 
              s[n] = adjacencyList[r].get(n);        
            }
            for(int h = 0; h< s.length;h++){
                if(s[h] == e){
                j.add(r);
                
                }  
            }       
        }
    }
    
    
    
    public static void printAdjacencyList6097() {
        
         for(int v = 0; v<adjacencyList.length;v++){
                        System.out.println("Node "+v+ adjacencyList[v]);
        } 
          for(int b=0;b<adjacencyList.length;b++){ 
                   a = adjacencyList[b].size();
              System.out.println("Node "+b+" : " +a);
		}
	}
    
    public static int get_no_of_incident6097(int l){

           int s = adjacencyList[l].size();
           
        return s;
}   
    
    public static double calculate_sum_of_incident6097(int k, int q){ 
        int d = get_no_of_incident6097(k); 
        int v = q;
        double z = 0;
        
        if(v == 0){
        z =(double) prl[k]/d;
        }
        else{z =(double) pra[k]/d;
        }
        
        return z;
    }
    
    
    
    
    public static double calculate_intermediate6097(int h,int o){
        double sum = 0;
        int k = o;
        finding_incident_nodes6097(h);
        Integer l[] = j.toArray(new Integer[j.size()]);
    for(int m = 0;m<j.size(); m++){                       
                        int f = l[m];
        sum +=(double) calculate_sum_of_incident6097(f,k);
    }
                      
     if( k == 0){ k = 1;}
     else{ k = 0;}
     
            return sum;                      
    }
    
    
    public static void initializing_values6097(int c){
        prl = new double[nodeCount];
        pra = new double[nodeCount];
        if( c == 0){
            for(int r=0; r<nodeCount;r++){         
            prl[r] =(double) 0;  
           }
        }
        
        else if(c == 1){
        for(int r=0; r<nodeCount;r++){         
            prl[r] =(double) 1;  
           }
        }
        
        else if( c == -2){
            double root = Math.sqrt(nodeCount);
        for(int r=0; r<nodeCount;r++){         
            prl[r] =(double) 1/root;  
           }
        }
        else{
        for(int r=0; r<nodeCount;r++){         
            prl[r] =(double) 1/nodeCount;  
           }
        }
           
    }
    
    
    public static void calculate_pagerank6097() throws FileNotFoundException{
        
        if( nodeCount >10){
             DecimalFormat df = new DecimalFormat("0.0000000");
            DecimalFormat dw = new DecimalFormat("00");
            PrintStream console = new PrintStream(new File("pgrkoutput6097.txt"));
            System.setOut(console);
            double x = (1-d)/nodeCount; 
            int p = 0;
            
            
            if(errorrate > 0){
            for(int j = 0; j<= errorrate;j++){   
               
               if (j == 0){
//                   System.out.println("Base : "+dw.format(j)+" : ");
                   console.println("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
                       
//                   System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
                   console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
                   }

               }
               
               else{
//                   System.out.println("Iter : "+dw.format(j)+" : ");
                   console.println("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
          
           }
            if(p == 0){ p =1;}
            else{p = 0;}
               }  
            System.out.println("\n");    
           }
    }
            
            else if(errorrate == 0){
                int j = 0;
            while(j >= 0){   
               
               if (j == 0){
//                   System.out.println("Base : "+dw.format(j)+" : ");
                   console.println("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
                   console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
//                   System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");                       
                   }

               }
               
               else{
//                   System.out.println("Iter : "+dw.format(j)+" : ");
                   console.println("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
          
           }
            if(p == 0){ p =1;}
            else{p = 0;}           
            for(int m = 0; m<nodeCount; m++){
            if(Math.abs(pra[m] - prl[m]) > 0.00001){
            flag = 0;
            break;
            }
            } 
            if(flag == 1){break;}
               }              
            System.out.println("\n");  
            j++;
           }
            }
 
            else{
                int j = 0;
                double exp = (double) Math.pow(10, errorrate);
            while(j >=0){                 
               if (j == 0){
//                   System.out.println("Base : "+dw.format(j)+" : ");
                   console.println("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){                
//                   System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
                   console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
                   }
               }
               
               else{
//                   System.out.println("Iter : "+dw.format(j)+" : ");
                   console.println("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.println("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
          
           }
            if(p == 0){ p =1;}
            else{p = 0;}
            
            for(int m = 0; m<nodeCount; m++){
            if(Math.abs(pra[m] - prl[m]) > exp){
            flag = 0;
            break;
            }
            }
            if(flag == 1){break;}
               }  
            System.out.println("\n");   
            j++;
           }
            }
        }

        else{
             DecimalFormat df = new DecimalFormat("0.0000000");
            DecimalFormat dw = new DecimalFormat("00");
            PrintStream console = new PrintStream(new File("pgrkoutput6097.txt"));
            System.setOut(console);
            double x = (1-d)/nodeCount; 
            int p = 0;
 
            if(errorrate > 0){
            for(int j = 0; j<= errorrate;j++){   
               
               if (j == 0){
//                   System.out.print("Base : "+dw.format(j)+" : ");
                   console.print("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
                   console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
//                   System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");                       
                   }
               }
               
               else{
//                   System.out.print("Iter : "+dw.format(j)+" : ");
                   console.print("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
          
           }
            if(p == 0){ p =1;}
            else{p = 0;}
               }  
            System.out.println("\n");    
           }
    }
            
            else if(errorrate == 0){
                int j = 0;
            while(j >= 0){   
               
               if (j == 0){
//                   System.out.print("Base : "+dw.format(j)+" : ");
                   console.print("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
                   console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
//                   System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    "); 
                   }
               }
               
               else{
//                   System.out.print("Iter : "+dw.format(j)+" : ");
                   console.print("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
          
           }
            if(p == 0){ p =1;}
            else{p = 0;}
            
            for(int m = 0; m<nodeCount; m++){
            if(Math.abs(pra[m] - prl[m]) > 0.00001){
            flag = 0;
            break;
            }
            }
            if(flag == 1){break;}
               }             
            System.out.println("\n");  
            j++;
           }
            }
            
            else{
                int j = 0;
                double exp = (double) Math.pow(10, errorrate);
            while(j >=0){   
               
               if (j == 0){
//                   System.out.print("Base : "+dw.format(j)+" : ");
                   console.print("Base : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
                   console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
//                   System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");  
                   }
               }
               
               else{
//                   System.out.print("Iter : "+dw.format(j)+" : ");
                   console.print("Iter : "+dw.format(j)+" : ");
                   for(int e=0; e<nodeCount;e++){
           double y = calculate_intermediate6097(e,p);
           if(p == 0){
           pra[e] = x+(d*y); 
//           System.out.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(pra[e])+"    ");
           flag = 1;
           }
           
           else{prl[e] = x+(d*y);
//           System.out.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           console.print("P["+dw.format(e)+"] : "+df.format(prl[e])+"    ");
           flag = 1;
           }
           }
            if(p == 0){ p =1;}
            else{p = 0;}
            
            for(int m = 0; m<nodeCount; m++){
            if(Math.abs(pra[m] - prl[m]) > exp){
            flag = 0;
            break;
            }
            }
            if(flag == 1){break;}
               } 
            System.out.println("\n");   
            j++;
           }
            }
        }
                    }
    
    
      public static void main(String[] args) throws Exception {
          String x = args[2];  
          String input = "";
          input = reading_File6097(x);
          String[] rows = input.split("\n");
          
          int[] headerArray = extracting_nodes6097(rows[0]);
			
			//Get the number of nodes and edges
			nodeCount = headerArray[0]; 
			edgeCount = headerArray[1];
                        if(nodeCount >10){
                        y = -1;
                        errorrate = 0;
                        }
                        else{
                            errorrate = Integer.parseInt(args[0]);
                        y = Integer.parseInt(args[1]);
                            }
                        creating_AdjacencyList6097(rows);
                        initializing_values6097(y);
                        calculate_pagerank6097();
       
    } 
}
