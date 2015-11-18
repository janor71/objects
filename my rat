Rat r1;
 
 int count = 0;
 void setup() {
   size(400,400);
   
   r1= new Rat ();
 }
   void draw() {
   background(255);
   count = count +1;
   r1. display();
   r1. move();
  
   }
   
  class Rat {
    float ratX;
    float ratY;
    float ratDX;
    
Rat(){
     ratX=20;
     ratY = random(height);
     ratDX = random(width);
   
}
    
    void display () {  
    strokeWeight(1);
    stroke(0);
    fill(155);                                  // ear and body's fill
    ellipse(ratX,ratY-10,40,30 );                // rat's body
    ellipse(ratX+10,ratY-27,15,15);             // ear
    fill(95,62,21);                            // tail color
    ellipse(ratX-25,ratY-15,10,1);              // tail
    fill(95,62,21);                            // ear color
    fill(0);                                   // eye, nose color
    ellipse(ratX+10,ratY-15,5,3);              // eye
    ellipse(ratX+20,ratY-5,5,5);              // nose

// RAT LEGS
   float leg1=ratX+10, leg2=ratX-15;

   strokeWeight(5);
   stroke(0); 
   if (count/30 % 2 == 0) {
      line(leg1,ratY, leg1,ratY+10);
      line(leg2,ratY, leg2,ratY+10);
   } else{                                
      line(leg1,ratY, leg1-5,ratY+10);
      line(leg2,ratY, leg2-5,ratY+10);
   }
   strokeWeight(1);
 }
 void move() {
   ratX = ratX + 1;
   if(ratX > width) {
     ratX = 0;
   }
    }
}
