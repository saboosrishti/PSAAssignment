package edu.neu.coe.info6205.symbolTable;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BSTClient {
	private static Writer writer;
	private static HashMap<Integer,Integer> col;
	public static void main(String args[]) throws IOException{
		int n = 100;
		int m = 2000;
		int x=10000;
		col = new HashMap<>();
		BSTSimple bstSimple = new BSTSimple();
		Random r = new Random();
		List<Integer> intialTree = new ArrayList<>();
		for(int i =0;i<=n;i++){
			intialTree.add(i);
		}
		Collections.shuffle(intialTree);
		for(int i = 0;i<=n;i++){		
			bstSimple.put(intialTree.get(i), i);
		}
		col.put(bstSimple.nodeTotal(), bstSimple.height());
		for(int i = 0;i<x;i++){
			Boolean check = r.nextBoolean();
			String Operation;
			int firstCount = bstSimple.nodeTotal();
			if(check==true){
				Operation = "Insertion";
				bstSimple.put(r.nextInt(m),r.nextInt(m));
			}
			else{
				Operation = "Deletion";
				bstSimple.delete(r.nextInt(m));
			}
			int afterCount = bstSimple.nodeTotal();
			if(afterCount != firstCount){
				col.put(bstSimple.nodeTotal(), bstSimple.height());
				System.out.println(Operation+": "+bstSimple.nodeTotal() + " " + bstSimple.height());
			}
		}
		generate(bstSimple);
	}
	// Function to create the csv sheet with the above generated data
    private static void generate(BSTSimple bstSimple) throws IOException{
        //generate Product file  
        try {
            
            File file = new File("./BSTSheet.csv");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            System.out.println("File Created");
            writer = new FileWriter(file);
            writer.append("Nodes #,Height");
            writer.append("\n");
            for(Map.Entry<Integer,Integer> e : col.entrySet()){
        		String sheetData = e.getKey()+ "," + e.getValue();
        		writer.append(sheetData);
        		writer.append("\n");
        	}              
        }finally{          
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }        
    }
}
