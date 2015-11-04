//////// Multiple Balls and Birds.  Click bird or ball to reset.  Keys:  a, b, c, and A

// Add trees. //


int[] list=  { 99, 44, 33, 77, 66, 101, 4, 666, -99, 1,2,3  };



Tree[] forest;

String s="Click bird or ball to reset.  \nKeys:  r, a, b, c, h, i, j = reset \n A = speed up!";

float horizon;

void setup() {
  size(750, 500 );
  horizon=  height/4;

  forest= new Tree[20];


/*
  forest[0]=  new Tree( 100,horizon, 40,80 );
  forest[1]=  new Tree( 200,horizon+50, 60,90 );
  forest[2]=  new Tree( 300,horizon+25, 30,70 );
  forest[3]=  new Tree( 400,horizon-35, 50,60 );
  forest[4]=  new Tree( 500,horizon+5, 80,120 );
*/
  float treeX=50;
  for( int i=0; i<forest.length; i++) {
     forest[i]=  new Tree( treeX,horizon, random(40,90), random(80,120 )  );
     treeX += 50;
  }

  reset();
}
void reset() {
}

//// NEXT FRAME:  scene, birds, balls.
void draw() {
  scene();
//  birds();
//  balls();
  text( s, width/2, 20 );
  
  // Display all #s
  int many=  list.length;
  
  float x=300, y=200;
  fill(0);
  textSize(12);
  for( int i=0; i<many; i++ ) {
    text( list[i], x, y);
    y=  y + 15;
  }
  

  //  Add up the numbers, and get the average.
  int total=0;
  for( int i=0; i<many; i++ ) {
    total += list[i];
  }
  fill(0);
  textSize(20);
  text( "Total is:    "+total, 10,210 );
  text( "(There are "+ many + " numbers.)", 10,225 );
  text( "Average is:  "+total/many, 10,260 );

}

//// SCENE:  sky & grass.
void scene() {
  background(150,200,250);
  fill( 150,250,150 );
  rect( 0,horizon, width, height-horizon );
/*
  // Sun
  fill(255,200,0);
  noStroke();
  ellipse( frameCount%width, horizon-40-sin( (PI*frameCount/width) % PI )*horizon/2, 40, 40 );
*/
  stroke(0);
  // Trees.
  for (int i=0; i<forest.length; i++) {
    forest[i].show();
  }  
}

//// Move and show birds
void birds() {
}



//// HANDLERS:  keys & clicks.
void keyPressed() {
  if (key == 'q') exit();
}
void mousePressed() {
}
    


//// OBJECTS ////


class Tree{
  float x, y;            // Position of base.
  float trunk, leaves;   // Width of top, height of trunk;
  int r=100,g=250,b=100;
  // CONSTRUCTOR //
  Tree( float x, float y, float leaves, float trunk ){
    this.x=x;  this.y=y;
    this.leaves=  leaves;
    this.trunk=trunk;
    r=  int( random(50,150)  );
    g=  int( random( 180,255 )  );
    b=  int( random( 50,200 )  );
  }
  // METHODS //
  // Display the tre.
  void show() {
    fill(150,0,0);          // Brown
    rect(x,y, leaves/6,-trunk );
    fill(r,g,b);
    ellipse( x, y-trunk, leaves, leaves );
    x = ( x + random( 7 ) )  %   width;
  }
}


