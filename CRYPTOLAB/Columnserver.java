/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author student
 */
import java.net.*;
import java.io.*;
import java.util.*;
public class Columnserver {
     public static void main(String str[]) throws IOException {
        ServerSocket ss = new ServerSocket(9000);
        Socket s = ss.accept();
        System.out.println("[INFO] : Client connected");
        PrintWriter pr=new PrintWriter(s.getOutputStream(),true);    
       
        BufferedReader bw=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader bi=new BufferedReader(new InputStreamReader(System.in));
         Scanner sc=new Scanner(System.in);
        String ct=bw.readLine();
        String key=bw.readLine();
        String ak[]=key.split("-",-1);
        //System.
        String ps[]=new String[ak.length];
        int l=ct.length()/ak.length;
        for(int i=0,j=0;i<ct.length();i+=l,j++){
            ps[j]=ct.substring(i, i+l);
            //System.out.println(ps[j]);
        }
      //  System.out.println("came");
        String pt="";
         
        for(int i=0;i<l;i++){
          for(int j=0;j<ak.length;j++){
              int up=Integer.parseInt(ak[j])-1;
            //  System.out.println(up+" "+ps[up]);
              pt+=ps[up].charAt(i);
          }            
        }
        System.out.println("PT: "+pt);
     }
     
}

/*
meetmeaftertheclassatrajamhall
4-3-2-1-5-6-7

*/