import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AssassinManager{

   // a reference to the front node of the kill ring
   private AssassinNode firstRing;
   
   // a reference to the front node of the graveyard
   private AssassinNode firstGraveyard;

   // constructs new assassinManager with the given list of names and build a kill ring
   public AssassinManager(List<String> names) {
      if (names == null || names.isEmpty()) { // do I need .isEmpty??
         throw new IllegalArgumentException();
      }
      firstRing = new AssassinNode(names.get(0));
      AssassinNode nextNode = firstRing;
      for (int i = names.size() - 1; i >= 1; i--) {
         AssassinNode currentNode = new AssassinNode(names.get(i), nextNode); // using the same name to create an object doesn't override the first object with that name?
         nextNode = currentNode;
      }
      firstRing.next = nextNode; // does this work?
   }

   // Prints the names of the people in the kill ring
   public void printKillRing() {
      AssassinNode currentNode = firstRing;
      while (currentNode.next != firstRing) {// is there a better check?
         System.out.println(currentNode.name + " is stalking " + currentNode.next.name);
         currentNode = currentNode.next;
      }
      
      System.out.println(currentNode.name + " is stalking " + currentNode.next.name);
      currentNode = currentNode.next;
   }
   
   // prints the names of the people in the graveyard
   public void printGraveyard() { // need to flip print order
      AssassinNode currentNode = firstGraveyard;
      List<AssassinNode> orderedDeaths = new ArrayList<AssassinNode>(); // better way than defining this??
      while (currentNode != null) {
         orderedDeaths.add(currentNode);
         System.out.println(currentNode.name + " was killed by " + currentNode.killer);  // prints wrong order
         currentNode = currentNode.next;
      }
      System.out.println();// just separation
      for (int i = orderedDeaths.size() - 1 ; i >= 0 ; i--) {
         currentNode = orderedDeaths.get(i);
         System.out.println(currentNode.name + " was killed by " + currentNode.killer);  // prints correct order
      }
   }
   
   // returns whether the person input is still in the kill ring
   public boolean killRingContains(String name) {
      name = name.toLowerCase();
      AssassinNode currentNode = firstRing;
      do { // Look I did a do while loop!! Couldn't figure out how to do it otherwise :(
         if (currentNode.name.toLowerCase().equals(name)) {
            return true;
         }
         currentNode = currentNode.next;
      } while(currentNode.next != firstRing.next);
      return false;
   }    
   
   // returns wheter the person input is in the graveyard
   public boolean graveyardContains(String name) {
      name = name.toLowerCase();
      AssassinNode currentNode = firstGraveyard;
      do  {
         if (currentNode.name.toLowerCase().equals(name)) {
            return true;
         }
         currentNode = currentNode.next;
      } while(currentNode != null);
      return false;
   }
   
   // returns whether or not the game is over
   public boolean isGameOver() {
      return firstRing.next == firstRing;
   }
   
   // returns winner of the game
   public String winner() {
      if (!isGameOver()){
         return null;
      }
      return firstRing.name;
   }
   
   // moves person input into the graveyard
   public void kill(String name) {
      // check if game is over
      if (isGameOver()) {
         throw new IllegalStateException();
      }
      // determine if name is in kill ring, and who killer is
      AssassinNode killerNode = firstRing;
      AssassinNode killedNode = firstRing.next;
      name = name.toLowerCase();
      boolean firstTimeThrough = true;  // better way than using this boolean??
      while (!killedNode.name.toLowerCase().equals(name)) {
         if (killedNode == firstRing.next && firstTimeThrough == false) {
            throw new IllegalArgumentException();
         }
         killerNode = killerNode.next;
         killedNode = killedNode.next;
         firstTimeThrough = false;
      }
      // reassign node locations
      if (killedNode == firstRing) {
         firstRing = killedNode.next;
      }
      killedNode.killer = killerNode.name;
      killerNode.next = killedNode.next;
      killedNode.next = null;
      if (firstGraveyard == null) {
         firstGraveyard = killedNode;
      } else {
         AssassinNode currentNode = firstGraveyard;
         while (currentNode.next != null) {
            currentNode = currentNode.next;
         }
         currentNode.next = killedNode;
      }
   }

}