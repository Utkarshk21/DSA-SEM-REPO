package com.cmu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DSAAssignment {

	public static void main(String[] args) throws IOException {
		
		 // Read input hardcoded files
        BufferedReader fi = new BufferedReader(new FileReader("10.in"));
        
        //Prepare output file
        PrintWriter fo = new PrintWriter(new BufferedWriter(new FileWriter("10.out")));
        
        int n = Integer.parseInt(fi.readLine());
        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();
        Integer guard=0;
        for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(fi.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ArrayList<Integer> tempa = new ArrayList<>();
    		ArrayList<Integer> tempb = new ArrayList<>();
    		tempa.add(start);
    		tempa.add(1);
    		tempa.add(guard);
    		tempb.add(end);
    		tempb.add(0);
    		tempb.add(guard);
    		AL.add(tempb);
    		AL.add(tempa);
    		guard++;
		}
		
		Collections.sort(AL, new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0)-o2.get(0);
			}
		
		});
		
		Integer Result = maxTimeAfterRemovingLifeguard(AL,guard);
		fo.println(Result);
        fo.close();
	}
	
	static Integer maxTimeAfterRemovingLifeguard(ArrayList<ArrayList<Integer>> AL,Integer guard) {
		Integer total_time_pool_guarded = 0;
		int[] alone_time = new int[guard];
		int count=0;
		ArrayList<Integer> active_guards = new ArrayList<Integer>();
		for(ArrayList<Integer> data : AL) {
			Integer current_time = data.get(0);
			int forward=count+1;
			int backward=count-1;
			if(active_guards.size()>0) {
				int temp_time_pool_guarded =  current_time - AL.get(backward).get(0);
				total_time_pool_guarded = total_time_pool_guarded + temp_time_pool_guarded;
			}
			if(data.get(1)==0) {
				active_guards.remove(data.get(2));
			}
			else {
				active_guards.add(data.get(2));
			}
			if(active_guards.size()==1) {
				alone_time[active_guards.get(0)]=alone_time[active_guards.get(0)]+(AL.get(forward).get(0)-current_time);
			}
			count++;
		}
		return (total_time_pool_guarded - Arrays.stream(alone_time).min().getAsInt());
		}
	
}
