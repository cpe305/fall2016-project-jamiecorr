package edu.calpoly.cpe305;

public interface Subject {
   public abstract void register(Observer o);   
   public abstract void unregister(Observer o);     
   public abstract void notifyObservers(); 
}
