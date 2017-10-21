# JOGL PlyObjectRenderer

JOGL PlyObjectRenderer is a JOGL application that allows user to render and perform transformations on a PlyObject.

## Screenshots
<div>
  <img src="/screenshots/screenshot1.png" width="240">
  <img src="/screenshots/screenshot2.png" width="240">
  <img src="/screenshots/screenshot3.png" width="240">
  <img src="/screenshots/screenshot4.png" width="240">
</div>

## Usage ##

Requirements:

1. Eclipse with JOGL

Steps:

1. Clone this project and extract it on your computer
2. Open Eclipse
3. Ensure JOGL is included in the workspace, otherwise setup according to
    https://solarianprogrammer.com/2014/12/08/getting-started-jogl-java-opengl-eclipse/
4. Click File > "Open Projects from File System"
5. Select the A2-25532928 project directory
6. Click Finish
7. Navigate to Main.java using the Project Explorer
8. Right Click > Run As > Java Application

# Mouse Actions
  1. Hold the LEFT Mouse button and drag to rotate the model (about X-axis and Y-axis)
  2. MouseWheelUp and MouseWheelDown to zoom in and out

# Keyboard Actions
  1. "A"/"a" and "D"/"d" to rotate about X-axis
  2. "X"/"x" and "W"/"w" to rotate about Y-axis
  3. "Z"/"z" and "E"/"e" to rotate about Z-axis
  4. "+"/"=" and "-"     to zoom in and out
