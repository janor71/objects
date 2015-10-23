//////// Multiple Balls and Birds

Ball a,b,c,d,e;

float horizon;

void setup() {
  size(400, 300 );
  horizon=  height/4;
  a=  new Ball();
  a.r=255;
  a.b=255;
  a.name=  "a";
  //
  b=  new Ball();
  b.g=255;
  b.name=  "b";
  //
  c=  new Ball();
  c.g=255;
  c.b=255;
  c.name=  "c";
  //
  d=  new Ball();
  d.g=127;
  d.b=127;
  d.name=  "d";
  //
  e=  new Ball();
  e.r=127;
  e.name=  "e";
  //
  reset();
}
void reset() {
  a.reset();
  b.reset();
  c.reset();
  d.reset();
  e.reset();
}

//// NEXT FRAME:  scene, birds, balls.
void draw() {
  scene();
  balls();
}

//// SCENE:  sky & grass.
void scene() {
  background(150,200,250);
  fill( 150,250,150 );
}

//// Move & show each ball
void balls() {
  collision( a, b );
  collision( a, c );
  collision( a, d );
  collision( a, e );
  //
  collision( b, c );
  collision( b, d );
  collision( b, e );
  //
  collision( c, d );
  collision( c, e );
  collision( d, e );
  //
  a.move();
  b.move();
  c.move();
  d.move();
  e.move();
  //
  a.show();
  b.show();
  c.show();
  d.show();
  e.show();
}

//// Elastic collisions.
void collision( Ball p, Ball q ) {
  if ( p.hit( q.x,q.y ) ) {
    float tmp;
    tmp=p.dx;  p.dx=q.dx;  q.dx=tmp;      // Swap the velocities.
    tmp=p.dy;  p.dy=q.dy;  q.dy=tmp;
    p.move();  p.move();   p.move();
    q.move();  q.move();   q.move();
  }
}


//// HANDLERS:  keys & clicks.
void keyPressed() {
  if (key == 'q') exit();
  if (key == 'r') {
        a.reset();
        b.reset();
        c.reset();
        d.reset();
        e.reset();
  }
  if (key == 'A') {
    a.dx *= 2;          // Make a ball go faster!
    a.dy *= 2;
  }
}
    

//// OBJECTS ////
class Ball {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Ball "has".)
  float x,y, dx,dy;
  int r,g,b;
  String name="";
  
  //// CONSTRUCTORS (if any). ////
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void show() {
    fill(r,g,b);
    ellipse( x,y, 30,30 );
    fill(0);
    text( name, x-5,y );
  }
  void move() {
    if (x>width || x<0) {  dx=  -dx; }
    if (y>height || y<horizon) {  dy=  -dy; }
    x=  x+dx;
    y=  y+dy;
  }
  void reset() {
    x=  random( width/2, width-100 );
    y=  random( horizon+0, height-50 );
    dx=  random( 1,5 );
    dy=  random( 1,3 );
  }
  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
  }
}

