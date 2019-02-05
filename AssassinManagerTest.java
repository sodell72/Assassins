import java.util.*;
import java.io.*;

public class AssassinManagerTest {
   public static void main(String [] args) throws FileNotFoundException {
   

   Scanner fileScan = new Scanner(new File("names.txt"));
   List<String> nameList = new ArrayList<String>();
   while (fileScan.hasNextLine()) {
      String line = fileScan.nextLine();
      nameList.add(line);
   }
   // print names in input list
   System.out.println(nameList.toString());
   System.out.println();
   
   AssassinManager myManager = new AssassinManager(nameList);
   
   // test printKillRing
   myManager.printKillRing();
   
   System.out.println();
//    
//    System.out.println("game over " + myManager.isGameOver());
//    System.out.println("winner is " + myManager.winner());
   
   
   
   
   
   // test killRingContains names.txt
   System.out.println("Testing killRingContains (should be true):");
   System.out.println(myManager.killRingContains("Grace Hopper"));
   System.out.println(myManager.killRingContains("Don Knuth"));
   System.out.println(myManager.killRingContains("Alonzo Church"));
   System.out.println(myManager.killRingContains("Alan Turing"));
   
   // test kill
   myManager.kill("Grace Hopper");
   myManager.kill("Don Knuth");
   myManager.kill("Alonzo Church");
   myManager.kill("Alan Turing");
   
   System.out.println();
   
   // test killRingContains after kills
   System.out.println("Testing killRingContains after kills (should be false):");
   System.out.println(myManager.killRingContains("Grace Hopper"));
   System.out.println(myManager.killRingContains("Don Knuth"));
   System.out.println(myManager.killRingContains("Alonzo Church"));
   System.out.println(myManager.killRingContains("Alan Turing"));
   
   System.out.println();
   
   // Print kill ring with first, last, and a middle person killed
   myManager.printKillRing();
   
   System.out.println();
   
   // test graveyardContains
   System.out.println("Testing graveyardContains (should be true):");
   System.out.println(myManager.graveyardContains("Grace Hopper"));
   System.out.println(myManager.graveyardContains("Don Knuth"));
   System.out.println(myManager.graveyardContains("Alonzo Church"));
   System.out.println(myManager.graveyardContains("Alan Turing"));
   
   System.out.println();
   
   // test isGameOver
   System.out.println("Testing isGameOver (should be false):");
   System.out.println(myManager.isGameOver());
   
   System.out.println();
   
   myManager.printGraveyard();
   
   }
}