# Note to assessor
  1. This application was made using Eclipse with JOGL
  2. Please find the following documents in addition to the application:
      a. screenshots.pdf

# Instruction to setup:
  1. Open Eclipse
  2. Ensure JOGL is included in the workspace, otherwise setup according to
      https://solarianprogrammer.com/2014/12/08/getting-started-jogl-java-opengl-eclipse/
  3. Click File > "Open Projects from File System"
  4. Select the A2-25532928 project directory
  5. Click Finish
  6. Navigate to Main.java using the Project Explorer
  7. Right Click > Run As > Java Application

# Mouse Actions
  1. Hold the LEFT Mouse button and drag to rotate the model (about X-axis and Y-axis)
  2. MouseWheelUp and MouseWheelDown to zoom in and out

# Keyboard Actions
  1. "A"/"a" and "D"/"d" to rotate about X-axis
  2. "X"/"x" and "W"/"w" to rotate about Y-axis
  3. "Z"/"z" and "E"/"e" to rotate about Z-axis
  4. "+"/"=" and "-"     to zoom in and out

# Interesting features
  1. Not exactly a fancy feature, but my program allows the use of MouseWheel to zoom in and out
  2. I put a lot of effort to ensure that calculations are done dynamically without "Magic" numbers.
      This includes determining a suitable location for the centre of projection that does not cause clipping
  3. For Perspective Projection, I restricted FoV to 90 degrees, which is based on the FoV of the human eye.
      This helps to make the model look visually correct (unlike the shearing effect that happens beyond 90 degrees).
