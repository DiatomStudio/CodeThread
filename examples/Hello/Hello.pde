/**
* codeThread - simple cube example
* by: Diatom Studio (diatom.cc)
* 
* A simple example of using codeThread to generate tool paths or printing a simple cube and raft on makerbot
*
*
*/

import codeThreadLib.library.*;

CodeThread codeThread = new CodeThread(this);

void setup() {
  size(300,300,P3D); 

  codeThread.setDefault(); // set your craft robo to default settings

  //generate a cube 
  float sideLen = 30; // lenght of the cubes side
  float headSpeed = 1556.0f; // speed that the extruder head will travel at whilst printing
  float zPos = 0; // keep track of the z pos of the extruder head
  float zStep = 0.35f; // the amount to move up each step on the z axis

  //generate a raft
  codeThread.generateRaft(sideLen +5, sideLen + 5, 560,1500, 2.5f,1.5f); // generate a raft generateRaft(raftWidth,raftLength, headSpeedBottomLayer,headSpeedTopLayer, distanceBetweenSlatsBottomLayer,DistanceBetweenSlatsTopLayer)
  zPos = 1.27;// after drawing the raft our new zheight is 1.27(mm)

  for(zPos = 1.27; zPos < sideLen ; zPos+=zStep) {

    //move the extruder head to each corner of the box
    codeThread.moveTo(-(sideLen/2),-(sideLen/2),zPos,headSpeed);

    codeThread.extruderOnFwd(); // start extruding, now we're at the start point

    codeThread.moveTo((sideLen/2),-(sideLen/2),zPos,headSpeed); //whilst extruding move extruder to 2nd corner
    codeThread.moveTo((sideLen/2),(sideLen/2),zPos,headSpeed);
    codeThread.moveTo(-(sideLen/2),(sideLen/2),zPos,headSpeed);
    codeThread.moveTo(-(sideLen/2),-(sideLen/2),zPos,headSpeed); //back to the start,

    //now we have a square, step up in the z direction and start all over again
    codeThread.extruderOff(); // stop extruding
  }

  codeThread.printToConsole(); // print to console
  codeThread.writeToFile(sketchPath +"/code01.txt");
}




void draw() {
  
  //make things pretty, rotate and zoom to see cube
  smooth();
  background(255);
  pushMatrix();
  translate(width/2,height/2);//center at 0,0
  rotateX(PI/4);
  rotateZ(PI/4);
  scale(3.5f);
  
  //draw the platform
  noStroke();
  fill(220,220,220);
  rect(-(80/2),-(80/2),(80),(80)); // draw the makerbot platform for reference
  stroke(255,0,0);
  
  
  codeThread.render(); //render the tool paths
  
  
  popMatrix();
}

