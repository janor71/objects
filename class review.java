//////// OO version of Balls and Birds
// 1-classes.java //

Ball a,b,c;
Bird p;

float horizon;

void setup() {
  size(750, 500 );
  horizon=  height/4;
  a=  new Ball();
  p=  new Bird();
  b= new Ball();                  // we added this
  reset();
}
void reset() {
  a.x=  random( width/2, width-100);
  a.y=  random( horizon,height );
  a.dx=  random(1,5);
  a.dy=  random(1,5);
  
  b.x= 220;
  b.dx = 1;
  b.y =199;
  b.dy= -1;
}
void draw() {
  scene();
  //// Show ball
  a.show();
  a.move();
  //// Show bird
  p.show();
  p.move();
  // show ball
  b.show();      // we added this 
  b.move();
}
void scene() {
  background(150,200,250);
  fill( 150,250,150 );
  rect( 0,horizon, width, height-horizon );
}

//// HANDLERS
void keyPressed() {
  if (key == 'q') exit();
  if (key == 'r') reset();
}



//// For object-oriented programming, you will need class definitions like these ////

class Ball {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Ball "has".)
  float x,y, dx,dy;
  int r,g,b;
  int number;
  String s= "";
  //// CONSTRUCTORS (if any). ////
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void show() {
    fill(r,g,b);
    ellipse( x,y, 30,30 );
  }
  void move() {
    x=  x+dx;
    if (x>width || x<0) {  dx=  -dx; }
    y=  y+dy;
    if (y>height || y<horizon) {  dy=  -dy; }
  }
  // Detect if this Ball has hit abother Ball. 
}

class Bird {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Bird "has".)
  float x=0,y=50, dx=5,dy=0.5;
  int r,g,b;
  int number;
  boolean wingUp=false;
  
  //// CONSTRUCTORS (if any). ////
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void show() {
    fill(r,g,b);
    triangle( x,y, x-60,y-20, x-60,y+20 );
    if (wingUp) {
      triangle( x-20,y, x-40,y-50, x-40,y+50 );
    }
  }
  void move() {
    x=  x+dx;
    y=  y+dy;
    if (x>width) {
      x=0;
      y=  random( 0, horizon );
    }
  }
}
  
  
  
