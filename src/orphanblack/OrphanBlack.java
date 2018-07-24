/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orphanblack;

import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class OrphanBlack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String dna = sc.nextLine();
            sc.nextLine(); // ignore the second line
            String msg=retrieveDNAMessage(dna);
            if (msg!=null)
                System.out.println(msg);
            else System.out.println("Invalid DNA");
        }
    }

    private static String retrieveDNAMessage(String dna) {
        String bin = dnaToBin(dna);
        if (bin==null) return null;
        String msg = binToMessage(bin);
        if (msg != null) {
            return msg;
        }
        bin = compensateBin(bin);
        msg = binToMessage(bin);
        return msg;

    }

    private static String dnaToBin(String dna) {
        String bin="";
        for (int i=0;i<dna.length();i++) {
            switch (dna.charAt(i)) {
                case 'A':
                case 'T':
                    bin+=1;
                    break;
                case 'C':
                case 'G':
                    bin+=0;
                    break;
                default:
                    return null;
            }
        }
        return bin;
    }

    private static String binToMessage(String bin) {
        int length = bin.length();
        for (int i = 0; i <= 7; i++) {
            int noOfChars = (length - i) / 8;
            String msg = "";
            boolean findFullMessage = true;
            for (int k = 0; k < noOfChars; k++) {
                String binChar = bin.substring(i + k * 8, i+k*8+8);
                char c = binCharsToChar(binChar);
                if (c == 32 || c >= 65 && c <= 90) {
                    msg += c;
                } else {
                    findFullMessage = false;
                    break;
                }
            }
            if (findFullMessage) {
                return msg;
            }
        }
        return null;
    }

    private static String compensateBin(String bin) {
        String compensate="";
        for (int i=0;i<bin.length();i++) {
            if (bin.charAt(i)=='0') compensate+='1';
            else compensate+='0';
        }
        return compensate;
    }

    private static char binCharsToChar(String binChar) {
        char c=0;
        for (int i=0;i<8;i++) {
            c<<=1;
            if (binChar.charAt(i)=='1')
                c+=1;
        }
        return c;
    }

}
