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
public class CloumnClient {
    public static void main(String str[]) throws IOException{
      
        Socket s=new Socket("localhost",9000);
        System.out.println("Client started!");
        PrintWriter pr=new PrintWriter(s.getOutputStream(),true);
        BufferedReader bw=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader bi=new BufferedReader(new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Plaintext: ");
        String pt=sc.next();
        int pn=pt.length();
        System.out.println("Enter the key size: ");
                int k=sc.nextInt();
           System.out.println("Enter the key: ");
        String key=sc.next(); 
       // int a[]=new int[k];
        String p[]= key.split("-",-1);      
        String as[]=new String[k];
        while(pt.length()%k!=0){
            pt+='x';
        }
       for(int i=0;i<k;i++){
           int up=Integer.parseInt(p[i]);
           as[up-1]="";
           for(int j=i;j<pt.length();j+=k){
               as[up-1]=as[up-1]+pt.charAt(j);
           }    
         
       }
       String ct="";
         for(int i=0;i<k;i++){
         
         ct+=as[i];
         }
       System.out.println("Cipher text: "+ct);
       pr.println(ct);
       pr.println(key);         
    }  
}
