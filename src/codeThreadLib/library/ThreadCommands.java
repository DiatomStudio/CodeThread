package codeThreadLib.library;

import processing.core.PApplet;





class PathCommand{
  

	
  
  
  String print(){
   return ""; 
  }
  
  
  void render(){
  }


public void render(PApplet myParent) {
	// TODO Auto-generated method stub
	
}
  
} 


class MoveCommand extends PathCommand{
  float x, y, z;
  float speed;
  
 MoveCommand(float x, float y, float z, float speed){
  this.x = x;
  this.y = y;
  this.z = z;
  this.speed = speed;
 }
 
 String print(){
  return "G1 X"+x+" Y"+y+" Z"+z+" F"+speed; 
 }

 public void render(PApplet myParent) {
	 myParent.vertex(x,y,z); 	
 }
 
}


class MillimeterCommand extends PathCommand{ 
 String print(){
  return "G21"; 
 }
}


class InchCommand extends PathCommand{ 
 String print(){
  return "G20"; 
 }
}

class TurnOffSteppersCommand extends PathCommand{ 
	 String print(){
	  return "M18"; 
	 }
	}

class PauseCommand extends PathCommand{ 
  int pauseMilliSecs ;
  PauseCommand(int milliSec){
    this.pauseMilliSecs = milliSec;
    
  }
 String print(){
  return "G4 P"+pauseMilliSecs; 
 }
}




class ExtruderOnFwdCommand extends PathCommand{ 
 String print(){
  return "M101"; 
 }
}

class ExtruderOnRevCommand extends PathCommand{ 
 String print(){
  return "M102"; 
 }
}

class ExtruderOffCommand extends PathCommand{ 
 String print(){
  return "M103"; 
 }
}


class RaftCommand extends PathCommand{ 
 String print(){
  return ""; 
 }
}


class setAbsolutePositioningCommand extends PathCommand{ 
 String print(){
  return "G90"; 
 }
}

class waitForToolToHeatCommand extends PathCommand{ 
  int toolNum;
  waitForToolToHeatCommand(int toolNum){
    this.toolNum = toolNum;
  }
 String print(){
  return "M6 T"+toolNum; 
 }
}


class setCurrentHomeCommand extends PathCommand{ 
  int x,y,z;
  setCurrentHomeCommand(int x, int y, int z){
   this.x = x;
  this.y = y;
 this.z = z; 
  }
 String print(){
  return "G92 X"+x+" Y"+y+" Z"+z+" "; 
 }
}




class ToolTempCommand extends PathCommand{ 
  int temp;
  int toolNum;
  ToolTempCommand(int toolNum, int temp){
   this.temp = temp; 
   this.toolNum = toolNum;
  }
 String print(){
  return "M104 S"+temp+" T"+toolNum; 
 }
}

class ToolSpeedCommand extends PathCommand{ 
  int speed;
  int toolNum;
  ToolSpeedCommand(int toolNum, int speed){
   this.speed = speed; 
   this.toolNum = toolNum;
  }
 String print(){
  return "M"+(1+toolNum)+"08 S"+speed; 
 }
 
}

class BuildPlatformTempCommand extends PathCommand{ 
  int temp;
  int toolNum;
  BuildPlatformTempCommand(int toolNum, int temp){
   this.temp = temp; 
   this.toolNum = toolNum;
  }
 String print(){
  return "M109 S"+temp+" T"+toolNum; 
 }
 
}


/*

M103
G1 X-17 Y-25 Z0.35 F592.593

M101

G1 X-17 Y17 Z0.35 F592.593

G1 X-14 Y17 Z0.35 F592.593

G1 X-14 Y-17 Z0.35 F592.593

G1 X-11 Y-17 Z0.35 F592.593

G1 X-11 Y17 Z0.35 F592.593

G1 X-8 Y17 Z0.35 F592.593

G1 X-8 Y-17 Z0.35 F592.593

G1 X-5 Y-17 Z0.35 F592.593

G1 X-5 Y17 Z0.35 F592.593

G1 X-2 Y17 Z0.35 F592.593

G1 X-2 Y-17 Z0.35 F592.593

G1 X1 Y-17 Z0.35 F592.593

G1 X1 Y17 Z0.35 F592.593

G1 X4 Y17 Z0.35 F592.593

G1 X4 Y-17 Z0.35 F592.593

G1 X7 Y-17 Z0.35 F592.593

G1 X7 Y17 Z0.35 F592.593

G1 X10 Y17 Z0.35 F592.593

G1 X10 Y-17 Z0.35 F592.593

G1 X13 Y-17 Z0.35 F592.593

G1 X13 Y17 Z0.35 F592.593

G1 X16 Y17 Z0.35 F592.593

G1 X16 Y-17 Z0.35 F592.593

M103

(</layer>)

(<layer> 0.805 )

G1 X17 Y17 Z0.81 F3000

M101

G1 X-17 Y17 Z0.81 F3000

G1 X-17 Y15.5 Z0.81 F3000

G1 X17 Y15.5 Z0.81 F3000

G1 X17 Y14 Z0.81 F3000

G1 X-17 Y14 Z0.81 F3000

G1 X-17 Y12.5 Z0.81 F3000

G1 X17 Y12.5 Z0.81 F3000

G1 X17 Y11 Z0.81 F3000

G1 X-17 Y11 Z0.81 F3000

G1 X-17 Y9.5 Z0.81 F3000

G1 X17 Y9.5 Z0.81 F3000

G1 X17 Y8 Z0.81 F3000

G1 X-17 Y8 Z0.81 F3000

G1 X-17 Y6.5 Z0.81 F3000

G1 X17 Y6.5 Z0.81 F3000

G1 X17 Y5 Z0.81 F3000

G1 X-17 Y5 Z0.81 F3000

G1 X-17 Y3.5 Z0.81 F3000

G1 X17 Y3.5 Z0.81 F3000

G1 X17 Y2 Z0.81 F3000

G1 X-17 Y2 Z0.81 F3000

G1 X-17 Y0.5 Z0.81 F3000

G1 X17 Y0.5 Z0.81 F3000

G1 X17 Y-1 Z0.81 F3000

G1 X-17 Y-1 Z0.81 F3000

G1 X-17 Y-2.5 Z0.81 F3000

G1 X17 Y-2.5 Z0.81 F3000

G1 X17 Y-4 Z0.81 F3000

G1 X-17 Y-4 Z0.81 F3000

G1 X-17 Y-5.5 Z0.81 F3000

G1 X17 Y-5.5 Z0.81 F3000

G1 X17 Y-7 Z0.81 F3000

G1 X-17 Y-7 Z0.81 F3000

G1 X-17 Y-8.5 Z0.81 F3000

G1 X17 Y-8.5 Z0.81 F3000

G1 X17 Y-10 Z0.81 F3000

G1 X-17 Y-10 Z0.81 F3000

G1 X-17 Y-11.5 Z0.81 F3000

G1 X17 Y-11.5 Z0.81 F3000

G1 X17 Y-13 Z0.81 F3000

G1 X-17 Y-13 Z0.81 F3000

G1 X-17 Y-14.5 Z0.81 F3000

G1 X17 Y-14.5 Z0.81 F3000

G1 X17 Y-16 Z0.81 F3000

G1 X-17 Y-16 Z0.81 F3000

G1 X-17 Y-17.5 Z0.81 F3000

M103

*/







