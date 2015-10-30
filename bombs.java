//////// Multiple Balls and Birds

Ball a,b,c,d,e;
Bird hawk, oriole, jay;

float horizon;

void setup() {
  size(750, 500 );
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
  hawk=  new Bird();
  oriole=  new Bird();
  oriole.r=255;
  oriole.g=150;
  jay=  new Bird();
  jay.b=255;
  //
  reset();
}
void reset() {
  a.reset();
  b.reset();
  c.reset();
}

//// NEXT FRAME:  scene, birds, balls.
void draw() {
  scene();
  birds();
  balls();
}

//// SCENE:  sky & grass.
void scene() {
  background(150,200,250);
  fill( 150,250,150 );
  rect( 0,horizon, width, height-horizon );
  fill(255,200,0);
  noStroke();
  ellipse( frameCount%width, horizon-40-sin( (PI*frameCount/width) % PI )*horizon/2, 40, 40 );
  stroke(0);
}

//// Move and show birds
void birds() {
  hawk.move();
  oriole.move();
  jay.move();
  //
  hawk.show();
  oriole.show();
  jay.show();
}
//// Move & show each ball
void balls() {
  a.move();
  b.move();
  c.move();
  collision( a, b );
  collision( a, c );
  collision( b, c );
  //
  a.show();
  b.show();
  c.show();
}

//// Elastic collisions.
void collision( Ball p, Ball q ) {
  if ( p.hit( q.x,q.y ) ) {
    float tmp;
    tmp=p.dx;  p.dx=q.dx;  q.dx=tmp;      // Swap the velocities.
    tmp=p.dy;  p.dy=q.dy;  q.dy=tmp;
  }
}


//// HANDLERS:  keys & clicks.
void keyPressed() {
  if (key == 'q') exit();
  if (key == 'a') a.reset();
  if (key == 'b') b.reset();
  if (key == 'c') c.reset();
  if (key == 'r') {
        a.reset();
        b.reset();
        c.reset();
  }
  if (key == 'A') {
    a.dx *= 2;          // Make a ball go faster!
    a.dy *= 2;
  }
  if (key == 'h') { hawk.drop();  }
  if (key == 'i') { oriole.drop();  }
  if (key == 'j') { jay.drop();  }
}
void mousePressed() {
  if ( hawk.hit( mouseX,mouseY ) ) {  hawk.drop(); }
  if ( oriole.hit( mouseX,mouseY ) ) {  oriole.drop(); }
  if ( jay.hit( mouseX,mouseY ) ) {  jay.drop(); }
  //
  if ( a.hit( mouseX,mouseY ) ) {  a.reset(); }
  if ( b.hit( mouseX,mouseY ) ) {  b.reset(); }
  if ( c.hit( mouseX,mouseY ) ) {  c.reset(); }
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

class Bird {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Bird "has".)
  float x=0,y=50, dx=5,dy=1.5;
  float w=60;
  int r,g,b;
  int number;
  boolean wingUp=false;
  float bombY=0, bombDY=0;
  float gravity=  9.81 / 60  ;
  
  //// CONSTRUCTORS (if any). ////
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void move() {
    if (x>width) { reset();  }
    y=  y+dy;
    if (y>horizon || y<20) {
      dy=  -dy * random(0.5,1.5);             // Bounce up from grass!
    }
    x=  x+dx;
    //// Bomb.
    if (bombDY>0) {
      bombDY += gravity;
      bombY += bombDY;
      if (bombY>height) {
        bombY=0;
        bombDY=0;                              // Bomb is gone!
      }
    }
  }
  void drop() {
    bombDY=  gravity;
  }
  void show() {
    fill(r,g,b);
    triangle( x,y, x-w,y-10, x-w,y+10 );
    // Wing
    wingUp=  int(frameCount/30) %2 >0;
    fill(255);
    if (wingUp) {
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y-40 );
    }else{
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y+40 );
    }
    // Show bomb, if DY>0;
    if (bombDY>0) {
      fill(r,g,b);
      ellipse( x,bombY, 30,70 );
      text( bombY, 10,30 );
    }
  }
  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
  }
  void reset() {
      x=0;
      y=  random( 50, horizon-30 );
      dx=  random( 1, 10 );
      bombDY=0;
  }
}
