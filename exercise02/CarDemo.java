//熟悉类的设计，设计汽车Car类

 public class CarDemo{
	
	public static class Car{
	  final int capacity;
 	 private String carType=null;
	 private int tank=100;
	 private int oilConsumption;

	 public Car(String type,int tank,int capacity,int oilconsumption){
      this.capacity=capacity;	
	  this.carType=type;
	  this.tank=tank;
	  this.oilConsumption= oilconsumption;
	}    

	 public void gas(int incre){
           
           if(this.capacity>=tank+incre){
	
	     System.out.println("just add "+(this.capacity-this.tank)+" is enough!");
	  }else{
 	     this.tank+=incre;
	     System.out.println("OK!the tank has "+this.tank);
	 }
	}
	 public void run(){
	
	   System.out.println(this.carType+" Car is running!");
           for (int i=this.tank;i>=0;i-=this.oilConsumption){
	        if(i==0) System.out.println("the car is out of gas!Stoped!");
                System.out.println("the car has "+i+" now!");
	   }
       
	}
	public void display(){
	
         System.out.println("car info:");
         System.out.println("   capacity: "+this.capacity);
         System.out.println("   type: "+this.carType);
	 System.out.println("   oilConsumption: "+this.oilConsumption);
	}
   }
	public static void main(String[] args){

         Car[] cars=new Car[3];
         cars[0]=new Car("Ford",100,100,20);
         cars[1]=new Car("Benz",100,100,10);
         cars[2]=new Car("Rolls-Royce",120,120,30);
         for(int i=0;i<3;i++){
		cars[i].run();
	       }
	 for(int i=0;i<3;i++){
		  cars[i].display();
	      }

          
	}
	
}
