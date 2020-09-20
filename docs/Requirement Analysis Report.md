# Requirement Analysis Document
 
|                               | **Names/Ids**               |
|-------------------------:|:--------------------------------|
| *Team members:* |*Jasper van Amerongen*|
|                               |*Illya Averchenko*           |
|                        |*Alexandru Matcov*        |
|                        |*Albina Shynkar*             |
|                        |*Lola Solovyeva*             |
|                        |*Bugra Veysel Yıldız*      |
| *Team ID:*            |22                                    |




**Instructions:**
* Make a document by including all the suggested sections.
* The document should be of a maximum of 4 pages.
## Introduction
We believe that this project is a great solution for the ones that would like to create a more relaxing or party environment at their place. The target customer is going to be anyone that wants to have a smart home system that controls the lighting of a LED strip depending on a set of criteria. The system is supposed to be user-friendly and easy to be used once the user is given a small introduction on how to use the system (learnability).


### Motivation
Creating an ambient and comfortable environment with the help of the colorful lights that adapt according to the preferences of the user or the content they are watching. This is a great alternative to the simple ambient light or light bulbs, but with a larger variety of options on controlling it, as well as switching on/off according to a sensor (LDR).
###  Scope
Implementing a simple smart home system with one light sensor (photoresistor), a user interface (physical/digital) and a LED light strip which is synchronized with the colors of the pixels of a video on a display or with the rhythm of the music.
### Limitations of the current system (if any)
Similar systems do already exist, but the part that is usually absent is a well constructed user interface. As TCS students, this is an area we can gravely improve upon. Whereas current systems are barely controllable - if at all -  by a remote or other basic physical input, our system will be more advanced by the integration of a sophisticated digital system available through a web application. Moreover, having this user interface will allow us to upgrade the system quite easily, which is impossible with current systems.
 ### Objectives
After successfully completing the project we expect to have:
* A smart home system with a LED strip which is triggered by the room lightning (dark, light).
* The LED strip when installed in the back of a display and set up accordingly should synchronize with the colors of the pixels of the display, therefore, creating the illusion of a “bigger picture”.
* The LED lights are synchronized with the music that is played from the smart home system.
### Definitions and Abbreviations 
* **LED**: *Light Emitting Diode*; a source of light powered by electricity.
* **LED Strip**; A series of individually controllable LEDs fabricated on a strip of plastic.
* **RGB**: *Red Green Blue*; this means that the object can emit any colour in the visible spectrum.
* **LDR**: *Light Dependent Resistor*; a sensor whose electrical resistance changes in relation to the amount of light it is exposed to.
* **Photoresistor**: see LDR.
* **UI**: *User Interface*; the part of our system with which the system is operated at a user level.
### Overview of the selected application
A Raspberry Pi 4 to which a photoresistor and a long LED strip is connected. Moreover, for the UI an application is going to be developed or a small display will be connected to the Pi, i.e. a LED matrix panel. All together we call it the “Smart Home Lighting System”. The product will serve the purpose of an entertainment system which can be turned on/off by a photoresistor, i.e. during the day the system is turned off, but with the fall of the night it turns automatically on. Once it is on, the user can synchronize the lightning of the LED strip to the music he listens to or connect it to a display for backlight lightning.
## Product Requirements
### Functional requirements
* Turn the light on/off according to how bright it is in the room. 
* Synchronize colors of the LEDs with the most popular color of the picture/video on the display.
* The system needs to be able to turn on and off the LED lights from the user interface.
* The LED lights need to be activated according to the light level in the room via LDR sensor.
* The light level of the LED lights need to be arranged from the user interface.
* The LED lights need to have different modes of colors which can be controlled by the user interface.
* The system can synchronize the LED lights with the music playing in the background.
* The user needs to be able to determine the lengths of the display.
* Lights have to move, flow, pulsate according to the music beat. 
* System has to be responsive to the user’s choice of a color.
* The system should be suitable for any type of display, either small laptop monitors or wide TV screens.
### Nonfunctional requirements
* The LED lights should be quite bright so it creates a cozy atmosphere in a dark room.
* The LED strip should be thin so it does not look bulky on the back side of the monitor/display.
* The response time of the LDR sensor should be from 1 to 5 seconds.
## Conclusion
In the end the user should have a smart interactive light system for desktop/laptop displays. This system should have several modes (such as color simulation of image and video, intensity of light, etc.), which the user can choose according to his/her preferences. The purpose of the project is to create a portable, easy to use system of devices that can bring comfortable ambience and provide a nonuniform level of illumination to the house of the user.
## References
* https://pimylifeup.com/raspberry-pi-light-sensor/
* https://www.instructables.com/id/Raspberry-Pi-GPIO-Circuits-Using-an-LDR-Analogue-S/
* https://www.instructables.com/id/DIY-Ambilight-With-Raspberry-Pi-and-NO-Arduino-Wor/