import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;


public class hits6097 {

   private static int initial;
   private static int flag;
   private static int iteration;
   private static int first;
   private static int second;
   private static double authority[];
   private static double authorityprev[];
   private static double hub[];
   private static double hubprev[];
   private static int total_vertices;
   private static int total_edges;
   private static int adjacency_matrix[][];
   private static int transpose_matrix[][];
   private static String rows_of_file[];
   
   
   public static void initialize6097(int a){
   
   authority = new double[total_vertices];
   hub = new double[total_vertices];
   authorityprev = new double[total_vertices];
   hubprev = new double[total_vertices];
   if(a == 0){
   for(int s=0; s<total_vertices; s++){
   authority[s] = 0;
   hub[s] = 0;
   }
   }
       
   else if(a == 1){
   for(int d=0; d<total_vertices; d++){
   authority[d] = 1;
   hub[d] = 1;
   }
   }
       
   else if(a == -1){
   for(int f=0; f<total_vertices; f++){
   authority[f] =(double) 1/total_vertices;
   hub[f] =(double) 1/total_vertices;
   }
   }
       
   else{             // default case
       double root_vertices = Math.sqrt(total_vertices);
   for(int g=0; g<total_vertices;g++){
   authority[g] =(double) 1/root_vertices;
   hub[g] =(double) 1/root_vertices;    
   }
   }
   adjacency_matrix = new int[total_vertices][total_vertices];
   for(int k=0; k<total_vertices; k++){
   for(int l=0; l<total_vertices; l++){
   adjacency_matrix[k][l] = 0;
   }
   }    
       
   }
   
   public static String reading_inputfile6097(String file_path) throws IOException {

		 Path path = Paths.get(file_path);
		 List<String> inputList =  Files.readAllLines(path, StandardCharsets.US_ASCII);
		 String input_value = "";
		 for (String string : inputList) {
			 input_value = input_value.concat(string) + "\n";
		}
		 		 
		 return input_value;
	}
   
   public static int[] extract_values_of_nodes6097(String input) throws Exception {
		String row[] = input.split(" ");
		
		if (row.length != 2) {
			throw new Exception("Input not proper at row : "+row);
		}
		
		int[] extracted_values = new int[2];
		extracted_values[0] = Integer.parseInt(row[0]);
		extracted_values[1] = Integer.parseInt(row[1]);
		
		return extracted_values;
	}
   
   
   public static void update_adjacencymatrix6097() throws Exception{
   
   for(int h = 1; h<=total_edges; h++){
   int row[] = extract_values_of_nodes6097(rows_of_file[h]);
   first = row[0];
   second = row[1];
   adjacency_matrix[first][second] = 1;
   }
   }
   
   public static void transpose_matrix6097(){
   transpose_matrix = new int[total_vertices][total_vertices];
   for(int c = 0; c<total_vertices; c++){
   for(int v = 0; v<total_vertices; v++){
   transpose_matrix[v][c] = adjacency_matrix[c][v];
   }
   }
   }
   
   public static void copy_values6097(){
   for(int y=0; y<total_vertices; y++){
   authorityprev[y] = authority[y];
   hubprev[y] = hub[y];
   }
   }
   
  
   public static void hits_calculate6097() throws FileNotFoundException{
    PrintStream console = new PrintStream(new File("hitsoutput6097.txt"));
    System.setOut(console);
    if(total_vertices >10){
       int i=0;
   while(i >=0){
      DecimalFormat df = new DecimalFormat("0.0000000");
      DecimalFormat df1 = new DecimalFormat("00");
       if(i == 0){
//       System.out.println("Base : "+df1.format(i)+" : " );
       console.println("Base : "+df1.format(i)+" : ");
       for(int t=0; t<total_vertices; t++){
       console.println("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
//       System.out.println("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
       }
       System.out.print("\n");
       }
       
       else{
       flag = 1;
       copy_values6097();
//       System.out.println("Iter : "+df1.format(i)+" : ");
        console.println("Iter : "+i+" : ");
         //calculating authority ranks
   for(int j=0; j<total_vertices; j++){
   authority[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(transpose_matrix[b][n] == 1){
   authority[b] += hub[n];
   }
   }
   }    
   
   
   //calculating hub rank
   for(int j=0; j<total_vertices; j++){
   hub[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(adjacency_matrix[b][n] == 1){
   hub[b] += authority[n];
   }
   }
   }    
   scaling_authority6097();
   scaling_hub6097();
      for(int k=0; k<total_vertices; k++){
//   System.out.println("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   console.println("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   }
    System.out.print("\n");
       }
   
   for(int u=0; u<total_vertices; u++){
   if((authority[u]-authorityprev[u] > Math.pow(10, -5)) || (hub[u]-hubprev[u] > Math.pow(10, -5))){
   flag = 0;
   }
       }
   if(flag == 1){
   break;
   }
   i++;
   }

    }
    
    
    else{
    if(iteration > 0){
       DecimalFormat df = new DecimalFormat("0.0000000");
       DecimalFormat df1 = new DecimalFormat("00");
   for(int r=0; r<= iteration; r++){
       if(r == 0){
//       System.out.print("Base : "+df1.format(r)+" : " );
       console.print("Base : "+df1.format(r)+" : ");
       for(int t=0; t<total_vertices; t++){
       console.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
//       System.out.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
       }
       System.out.println("\n");
       }
       
       else{
       flag = 1;
       copy_values6097();
//       System.out.print("Iter : "+df1.format(r)+" : ");
       console.print("Iter : "+df1.format(r)+" : ");
   for(int j=0; j<total_vertices; j++){
   authority[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(transpose_matrix[b][n] == 1){
   authority[b] += hub[n];
   }
   }
   }    
   
   
   //calculating hub rank
   for(int j=0; j<total_vertices; j++){
   hub[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(adjacency_matrix[b][n] == 1){
   hub[b] += authority[n];
   }
   }
   }    
   scaling_authority6097();
   scaling_hub6097();
      for(int k=0; k<total_vertices; k++){
//   System.out.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   console.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   }
      System.out.println("\n");
       }
   
   }
   }
    
    else if(iteration == 0){
           int i=0;
   while(i >=0){
      DecimalFormat df = new DecimalFormat("0.0000000");
      DecimalFormat df1 = new DecimalFormat("00");
       if(i == 0){
//       System.out.println("Base : "+df1.format(i)+" : " );
       console.print("Base : "+df1.format(i)+" : ");
       for(int t=0; t<total_vertices; t++){ 
       console.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
//       System.out.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
       }
        System.out.println("\n");
       }
       
       else{
       flag = 1;
       copy_values6097();
//       System.out.print("Iter : "+df1.format(i)+" : ");
       console.print("Iter : "+df1.format(i)+" : ");
   for(int j=0; j<total_vertices; j++){
   authority[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(transpose_matrix[b][n] == 1){
   authority[b] += hub[n];
   }
   }
   }    
   
   
   //calculating hub rank
   for(int j=0; j<total_vertices; j++){
   hub[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(adjacency_matrix[b][n] == 1){
   hub[b] += authority[n];
   }
   }
   }    
   scaling_authority6097();
   scaling_hub6097();
      for(int k=0; k<total_vertices; k++){
//   System.out.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   console.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   }
      System.out.println("\n");
       }
   for(int u=0; u<total_vertices; u++){
   if((authority[u]-authorityprev[u] > Math.pow(10, -5)) || (hub[u]-hubprev[u] > Math.pow(10, -5))){
   flag = 0;
   }
       }
   if(flag == 1){
   break;
   }
   i++;
   }
    }
   
   else{
       int i=0;
   while(i >=0){
      DecimalFormat df = new DecimalFormat("0.0000000");
      DecimalFormat df1 = new DecimalFormat("00");
       if(i == 0){
//       System.out.println("Base : "+df1.format(i)+" : " );
       console.print("Base : "+df1.format(i)+" : ");
       for(int t=0; t<total_vertices; t++){ 
       console.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
//       System.out.print("A/H["+df1.format(t)+"] : "+df.format(authority[t])+"/"+df.format(hub[t])+"    ");
       }
        System.out.println("\n");
       }
       
       else{
       flag = 1;
       copy_values6097();
//       System.out.print("Iter : "+df1.format(i)+" : ");
       console.print("Iter : "+df1.format(i)+" : ");
   for(int j=0; j<total_vertices; j++){
   authority[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(transpose_matrix[b][n] == 1){
   authority[b] += hub[n];
   }
   }
   }    
   
   
   //calculating hub rank
   for(int j=0; j<total_vertices; j++){
   hub[j] = 0;
   }    
   for(int b=0; b<total_vertices; b++){
   for(int n=0; n<total_vertices; n++){
   if(adjacency_matrix[b][n] == 1){
   hub[b] += authority[n];
   }
   }
   }    
   scaling_authority6097();
   scaling_hub6097();
      for(int k=0; k<total_vertices; k++){
//   System.out.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   console.print("A/H["+df1.format(k)+"] : "+ df.format(authority[k])+"/"+df.format(hub[k])+"    ");
   }
      System.out.println("\n");
       }
   for(int u=0; u<total_vertices; u++){
   if((authority[u]-authorityprev[u] > Math.pow(10, iteration)) || (hub[u]-hubprev[u] > Math.pow(10, iteration))){
   flag = 0;
   }
       }
   if(flag == 1){
   break;
   }
   i++;
   }
   }
    }
   }
   
   public static void scaling_authority6097(){
   DecimalFormat df = new DecimalFormat("0.0000000");  
   double scale = 0;
   for(int h=0; h<total_vertices; h++){
   scale += authority[h]*authority[h];
   }
   scale = Math.sqrt(scale);
   for(int e=0; e<total_vertices; e++){
   authority[e] = Double.parseDouble(df.format(authority[e]/scale));
   
   }
   
   }
   
   public static void scaling_hub6097(){
   DecimalFormat df = new DecimalFormat("0.0000000");  
   double scale = 0;
   for(int h=0; h<total_vertices; h++){
   scale += hub[h]*hub[h];
   }
   scale = Math.sqrt(scale);
   for(int e=0; e<total_vertices; e++){
   hub[e] = Double.parseDouble(df.format(hub[e]/scale));
   }
   
   }

    public static void main(String[] args) throws IOException, Exception {
    String path_of_file = args[2];
    String input_values = reading_inputfile6097(path_of_file);
    rows_of_file = input_values.split("\n");
    int head_row[] = extract_values_of_nodes6097(rows_of_file[0]);
    total_vertices = head_row[0];
    total_edges = head_row[1];
    if(total_vertices > 10){
    initial = -1;
    iteration = 0;
    }
    else{
    initial = Integer.parseInt(args[1]);
     iteration = Integer.parseInt(args[0]);
    }
    initialize6097(initial);
    update_adjacencymatrix6097();
    transpose_matrix6097();
    hits_calculate6097();
       
    }
    
}
