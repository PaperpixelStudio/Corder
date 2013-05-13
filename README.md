REcorder
========

Use processing to generate ready-to-laser-cut objects using physical data (voice spectrum,...)

**Note:** while this project uses processing as a graphics library, this is a java (and not a pde) project. This allows developers to code processing sketches in their favorite IDE, wich is likely to be a lot better than the processing one.

## Getting started

1. Clone the repository
1. Create a project in your favorite IDE with the source code
1. Download [minim library](http://code.compartmental.net/tools/minim/) and put it in your processing folder
1. Download [controlP5 library](http://www.sojamo.de/libraries/controlP5/) (choose the version according to your processing environment - it should work on both 1.5.* and 2.*)
1. Add processing libraries to your IDE project :
   - core.jar, found in [processing]/core/library/
   - controlP5.jar, found in [libraries_folder]/controlP5/library/
   - dxf.jar, found in [processing]/modes/java/libraries/dxf/library/
   - jogl-all.jar and glueten-rt.jar, found in [processing]/core/library/
   - jsminim.jar, minim-spi.jar, minim.jar and tritonus_share.jar, found in [libraries_folder]/minim/library/
1. Start coding