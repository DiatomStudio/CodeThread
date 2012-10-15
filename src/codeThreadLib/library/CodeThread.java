package codeThreadLib.library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

public class CodeThread {

	
	  
	// myParent is a reference to the parent sketch
	PApplet myParent;

	int myVariable = 0;
	
	public final static String VERSION = "0.1.2";
	

	/**
	 * a library for generating tool paths for makerbot and other cnc tools
	 */
	public CodeThread(PApplet theParent) {
		myParent = theParent;
	}
	

	
	/**
	 * returns the version of the library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
	
	

	  String codeString = "";
	  String endChar = "\n";
	  
	  int defaultToolTemp = 220;
	  int defaultBuildPlatformTemp = 110;
	  int defaultExtruderSpeed = 240;

	  ArrayList<PathCommand> commands = new ArrayList();

	  /**
	  *set units to millimeters
	  */
	  public void setUnitmm() {
	    commands.add( new MillimeterCommand());
	  }

	  /**
	  *set units to inches 
	  */
	  public void setUnitInch() {
	    commands.add( new InchCommand());
	  }
	  
	  /**
	   *move head to absolute position 
	   */
	  public void moveTo(float x, float y, float z, float speed) {
	    commands.add( new MoveCommand(x,y,z,speed));
	  }
	  
	  /**
	   *turn extruder on fwd 
	   */
	  public void extruderOnFwd() {
	    commands.add( new ExtruderOnFwdCommand());
	  }
	  /**
	   *turn extruder on rev
	  */
	  public void extruderOnRev() {
	    commands.add( new ExtruderOnRevCommand());
	  }

	  /**
	  *turn extruder off
	  */
	  public void extruderOff() {
	    commands.add( new ExtruderOffCommand());
	  } 

	  /**
	  *set tool temp
	  */
	  public void setToolTemp(int toolNum, int temp) {
	    commands.add( new ToolTempCommand( toolNum,  temp));
	  } 
	  
	  /**
	  *set build platform temp
	  */
	  public void setBuildPlatformTemp(int toolNum, int temp) {
	    commands.add( new BuildPlatformTempCommand( toolNum,  temp));
	  } 

	  /**
	  *set extrusion speed 
	  */
	  public void setExtrusionSpeed(int toolNum, int speed) {
	    commands.add( new ToolSpeedCommand( toolNum,  speed));
	  } 
	  /**
	  * set machine to absolute positioning
	  */
	  public void setAbsolutePositioning() {
	  commands.add( new setAbsolutePositioningCommand());
	  } 
	  
	  /**
	   * set current postion as home
	   */
	  public void setCurrentHome(int x, int y, int z){
	   commands.add( new setCurrentHomeCommand(x,y,z));
	  }
	  
	  /**
	   * wait for tool to heat
	   @param toolNum
	   */
	  public void waitForToolToHeat(int toolNum ){
	       commands.add( new waitForToolToHeatCommand(toolNum));
	  }
	  
	  /**
	   * pause extruding head
	   * @param secs
	   */
	  public void pause(int millis ){
	       commands.add( new PauseCommand(millis));
	  }
	  
	  
	  /**
	   * turn off machines stepper motors
	   */
	  public void turnOffSteppers(){
	       commands.add( new TurnOffSteppersCommand());
		  
	  }
	  
	  /**
	   * finish the print and cooldown the machine
	   */
	  public void finishPrint(){
		  setCurrentHome(0,0,0);
		  moveTo(0.0f,0.0f,10.0f,1000);
		  turnOffSteppers();
	  }
	  
	  /**
	   * clear the list of commands
	   */
	  public void clear(){
	       commands.clear();
		  
	  }
	  
	  
	  /**
	   *  generate a raft for your print
	   */
	  public void generateRaft(float raftWidth, float raftHeight, int baseFeed, int interfaceFeed, float baseStep, float interfaceStep) {
	    
	    int numBaseLines = (int) (raftWidth/baseStep);
	    int numInterfaceLines = (int) (raftHeight/interfaceStep);
	    
	    moveTo(-raftWidth/2, -raftWidth/2 + -10, 0.35f, interfaceFeed);
	    extruderOnFwd();
	    
	    for (int i=0; i<numBaseLines; i+=2) {
	    moveTo(-raftWidth/2+i*baseStep, -raftHeight/2, 0.35f, baseFeed);    
	    moveTo(-raftWidth/2+i*baseStep, raftHeight/2, 0.35f, baseFeed);
	    moveTo(-raftWidth/2+(i+1)*baseStep, raftHeight/2, 0.35f, baseFeed);
	    moveTo(-raftWidth/2+(i+1)*baseStep, -raftHeight/2, 0.35f, baseFeed);
	    }
	    extruderOff();
	    moveTo(raftWidth/2, raftHeight/2+1, (float) 0.35, interfaceFeed);
	    extruderOnFwd();
	    for (int i=0; i<numInterfaceLines; i+=2) {
	    moveTo(raftWidth/2, raftHeight/2-i*interfaceStep, (float) 0.81f, interfaceFeed);    
	    moveTo(-raftWidth/2, raftHeight/2-i*interfaceStep, (float) 0.81f, interfaceFeed);
	    moveTo(-raftWidth/2, raftHeight/2-(i+1)*interfaceStep, (float) 0.81f, interfaceFeed);
	    moveTo(raftWidth/2, raftHeight/2-(i+1)*interfaceStep, (float) 0.81f, interfaceFeed);  
	    }
	    extruderOff();
	    
	  }


	  /**
	   * set the machine to it's default settings 
	   * tool temp 220 degrees
	   * build platform temp 110 degrees
	   * units (mm)
	   * set machine to absolute positioning
	   * set current position to home, 0,0,0
	   * set tool speed to 255
	   * wait for the tool to heat
	   */
	  public void setDefaults(){
	  
	  setToolTemp(0,defaultToolTemp);
	  setBuildPlatformTemp(0,defaultBuildPlatformTemp);
	  setUnitmm();
	  setAbsolutePositioning();
	  setCurrentHome(0,0,0);
	  setExtrusionSpeed(0,defaultExtruderSpeed);
	  waitForToolToHeat(0);
	  
	}



	  /**
	   * print all commands to the console
	   */
	  public void printToConsole() {

	    for(int i = 0; i < commands.size(); i++) {
	      PathCommand command = commands.get(i);
	      System.out.println(command.print());
	    }
	  } 





	  /**
	   * render thread commands gcode to the screen
	   */
	  public void render() {
		  myParent.beginShape();
	    for(int i = 0; i < commands.size(); i++) {
	      PathCommand command = commands.get(i);
	      if(command instanceof MoveCommand)
	        command.render(myParent);
	    }
	    myParent.endShape();
	  }






	  /**
	   * write g-code file for printing on your machine
	   * @param path
	   */
	  public void writeToFile(String path) {
	    BufferedWriter out = null;

	    try {
	      out = new BufferedWriter( new FileWriter(path));
	    } 
	    catch (IOException e) {
	      e.printStackTrace();
	    }


	    for(int i = 0; i < commands.size(); i++) {
	      PathCommand command = commands.get(i);

	      try {
	        out.write(command.print()+"\n");
	      } 
	      catch (IOException e) {
	        e.printStackTrace();
	      }
	    }


	    try {
	      out.flush();
	      out.close();
	    } 
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	

	
}
