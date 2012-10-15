CodeThread
==========

CodeThread is libary for processing that lets you create g-code as you calculate coordinates for a toolpath.  One of the things we love about makerbot in contrast to commercial printers is that you have complete control over every aspect of the print technique. We think there is a lot of opportunity to develop new printing styles with makerbots, beyond traditional solid prints.  We wanted to experiment with the materiality of makerbot prints by working directly in gcode with processing, so we made this small library that provides some simple functions for generating gcode commands, and prints a gcode file. 


****For example, here is some code for a 30mm thin-walled cube:****

`CodeThread codeThread= new CodeThread();

int feed = 1200;

codeThread.setDefaults();
codeThread.extruderOnFwd();

codeThread.generateRaft(40, 40, 560, 1500, 2.5, 1.5);

for(float i=0; i<30; i+=0.35) {
codeThread.moveTo(15, 15, 1.27+i, feed);
codeThread.moveTo(-15, 15, 1.27+i, feed);
codeThread.moveTo(-15, -15, 1.27+i, feed);
codeThread.moveTo(15, -15, 1.27+i, feed);
}

codeThread.extruderOff();
codeThread.writeToFile(sketchPath + "30mmbox.gcode");`