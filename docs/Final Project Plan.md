# Final Project Plan report


|                  | **Names/Ids**  |
|-----------------:|:---------------|
| *Team ID:*  | 22 |
| *Team Members:*  | Bugra Veysel Yildiz, Jasper van Amerongen, Alexandru Matcov, Lola Solovyeva, Albina Shynkar, Illya Averchenko |
| *Scrum  Mentors:* | Chandni Raghuraman, Andrew Heath |


**Project Title:**  Smart Home Lightning System


## 1. Introduction and Background
This product lets users personalize their addressable LED strips with multiple animation and preset options and besides that effects, 16 million solid hues of colors. There is also an option to dim the lightning. The web application can be used to tailor the lightning experience. Users can get a relaxed lounge environment at their comfort zone with that product in no time. It is fully customizable that users are able to turn on and off their LED lights automatically according to the Light Dependent Resistor (LDR) that is mounted on the Raspberry Pi. It is a well-designed product that will turn your gaming into experience and let the users feel themselves as part of the scene while they are watching a movie. We aimed to create the best lighting experience which can be suitable for every space.


## 2. Project Scope
This project has been designed with many components to give the best experience to the user. First of all, we decided to use addressable LED strips to create presets and animations such as fireplace, fade and strobe. We have used Light Dependent Resistor to let users turn their lights on automatically and besides that, we have used a Raspberry Pi 4 to power the lights and control the LED strip with its GPIO pins. Besides that, it was necessary to run the web server which is the midpoint of our back-end and front-end. We also have a user-friendly user interface which lets the user customize his/her lights. 


## 3. Brief description of the hardware (Raspberry Pi) and software
### 3.1 Hardware 


This project required a vast amount of hardware components. At the basis of this project stands the Raspberry Pi 4 which hosts the web server and runs all the necessary applications for catching the data from the sensor, pipelining it to the user interface through back end and as well handling and processing the user input. We chose to use a Light Dependent Resistor as our sensor for the project. The implementation for the LDR is to retrieve data from the outside world in real time and feed it to the back end of the system. In the back end this data is processed to turn on or off the LED strip. The key feature of our project is the LED strip which we attached to our Raspberry Pi 4 by soldering. This LED is controlled through our built web application. The user would be able to control the strip in the UI, preselecting different cycling modes for lighting. The LED strip that we used for our project is WS2812B/NeoPixel. This is an intelligent control LED source with integrated control circuit and a RGB chip. Upon choosing a LED strip for our project this exact model fitted the best as its light modules are independent from each other, therefore, providing us the possibility to manipulate each individual light module in order to set it the desired criteria. 


### 3.2 Software


Deciding to work with Java for this project we had to adapt to the required libraries for GPIO libraries present for the aforementioned programming language. Thus, we had to work with Pi4J  which is an object-oriented I/O API and implementation library for Java that lets the Java programmers access the full I/O capabilities of the Raspberry Pi. As Pi4J is dependent on the WiringPi native library which is a 32-bit library, we had to choose an operating system of 32-bit for our Raspberry Pi 4. We are running 32-bit Raspberry Pi OS. Our project’s development environment is Intellij IDEA. This is the most comfortable development environment for all of us as we worked numerous times during the previous modules for writing and deploying Java code. For our web application deployment we are using Apache Tomcat which is an open-source implementation for the Java Servlet, JavaServer Pages, Java Expression Language and WebSocket technologies.








## 4. Application Specification
### 4.1 Front-End 
#### 4.1.1 Libraries and APIs
One thing that we decided upon is that we wanted to use a more complete CSS/JS framework than something like [Bootstrap](https://getbootstrap.com/), which is great for layout, but not so for designing. In the end, we settled on Google’s [Material Design](https://material.io/). Material Design is a language rather than a library, so when mentioning Material, we refer to Material Design Components for Web (MDC)](https://material.io/develop/web). The power of Material is in its buildability; it is just like Lego! For example, adding a button or a navigation bar is just a matter of copying the HTML. This made sure that developing the user interface was quite easy and that we did not have to worry ourselves with complicated CSS code and the like. Another major benefit from MDC is that it provides a path to implementing one of the most praised designs by arguably the best designers. This ensures a great user experience.


Another Google API we heavily rely on is [Google’s SSO for web](https://developers.google.com/identity/sign-in/web/sign-in). This allows us to authenticate and authorize (a bit concisely implemented here) our users and protect all our RESTful endpoints. This API is an essential part of our loading sequence, which is discussed in 4.1.2 Structure. This API was also used in module 4, so we were able to apply our prior knowledge very effectively.


Next to MDC and SSO,  we needed a library for our color picker. There are many contestants, but we settled on an API called [IRO.js](https://iro.js.org/). This is a very easy system to set up, but complex enough that we can customise it to our wishes to ensure that the application is as complete as possible.


For the selection of the animations, we found an API, called [Swiper.js](https://swiperjs.com/), which is very focussed on touch inputs. This is something that may be quite difficult to get right with JavaScript, but luckily this API allows us to implement this feature flawlessly. Again, its power comes through its adaptability and customizability. 


For generating the graph on our LDR page, we used the popular [Chart.js](https://www.chartjs.org/). It’s famous for its customizability and with that, we were able to make it as closely to the Material Design Language as possible.
#### 4.1.2 Structure
The web application is adhering a strict structure. This is a structure we used in previous web applications as well and this fact allowed us to reuse a lot of knowledge from last year. We identify two windows within our application. The app and the welcome window. The app is the window that you will only see when you have been authorized. The welcome windows are used for a sign in page and the page which prompts for the display size, expressed in pixels.


The app window serves as a  frame. This frame consists of a navigation bar and a canvas for pages to be displayed upon. Switching to a page is a matter of showing the target and hiding its siblings. Within the pages, there is a more custom structure to align components responsively. This is most prominent on the Palette page.


Next to the app window, there is the welcome window. Like mentioned briefly, this is made up of two pages: the sign in page and the set up page. On load, the system will check whether the user has already been logged in and if so, the app window will be shown and the aforementioned components are rendered. If the user is not signed in yet, they will be forced to the sign in page, after which the system will check whether the display size is known. If not, the set up page will be prompted as well. If this is passed properly, the app will start.


All these structures are administered by CSS Grid, which is implemented by MDC as well. Since this layout tool is already integrated into MDC, it makes it even easier for us to make our application behave as we want.
#### 4.1.3 Responsiveness
Like mentioned before, the responsiveness is also something that is handled by Material. Since it is based on the CSS grid, the only thing we had to worry about was the placement design. This follows an easy pattern. Up until `600px`, the app will behave as mobile. From `601px` to `840px`, the app will behave as a tablet and from `841px`, the app is in full desktop mode. Tablet mode and desktop mode share the same layout, where the app window is maxed out at `1000px`. This reveals the background banner, which adapts to the user’s selected theme. In mobile mode, the elements on the screen will be stacked vertically.
### 4.2 Back-end
To implement our product a decision to stick to MVC pattern was made, which stands for Model-View-Controller. These three components are very important to interconnect the user interface with the system that controls the LED strip. Following points are going to fully describe the purpose and functionality of the parts of the system. 


#### 4.2.1 Model
All implemented model classes do not contain any logic on how to present data to a user. It is merely an object that carries data to other parts of the system. They are also responsible for setting each LED light on the strip, controlling animations and presets, that are chosen by the user. Currently, the system contains separate classes for objects such as a single led, strip, animation, preset, palette, data of LDR, configuration of the size of a display and color of LEDs. `Class LED()` consists of data for RGB values. Each `LED()` is going to be a part of `Strip()` class, that is in charge of the number of `LED()`’s to be used on the `Strip()`. To configure the number of `LED()`’s used, a `class SetUp()` will give an opportunity to set a width and a length of the display, where the LED strip is going to be attached. Besides configuration, it is going to save a chosen color that the user picked during a first time logging in to the system. Furthermore, the system provides animation and presets. Information about them such as `id`, `name` and `icon` is stored in `Animation()` and `Preset()` classes respectively, which are a part of the `Palette()` class, since it carries a list of all `Animations()` and `Presets()`. Our system not only controls the LED strip, but also gathers data from the LDR sensor. It checks the level of brightness in the room, which was decided to also be stored in the database to present the graph that shows brightness change with time. `LDRData()` class contains a list of integers for this data, that also can be set if it is needed. The graph can be changed according to time or day, which will be called a label. The `LDRData()` class also contains all the labels as a list of strings.  


#### 4.2.2 DAO
DAO stands for a Data Access Object, so all of the DAO classes in the system  were implemented with the purpose of retrieving data from the database. The data from the LDR sensor is stored in the database, to make any modifications on this data we created an `LDRDataDAO()`. How it is exactly stored can be found in section 4.3 Database. However, data can be retrieved or updated according to day, week or month. It is going to be an `LDRData()` object that is going to be passed to a controller to actually show it in graphical form. Besides storing level of brightness, all information about animations and presets is also present in the database. Classes `PresetDAO()` and `AnimationDAO()` were implemented in order to retrieve their ids, names and icons to be able to show it on the user interface. There is a possibility to receive a list of all objects, belonging to `Animation()` or `Preset()`, or retrieve a single one according to a specified id. The last thing, that has to be stored, is the user’s choice of color and brightness. After the system is off, all the data might be lost. In order to avoid it, it was decided to store RGB values and brightness in the database, so when the system is on again, all the previous information can be loaded back with `SetUpDAO()` class. 


#### 4.2.3 Controller
Controller can be perceived as a bridge between user interface and  a model. It gets requests sent by the user (e.g enable the animation, change the color or update width and length of the screen) and transfers it to a hardware or a database. In our system there are only three controllers: `SetUpCotroller()`, `LDRDataController()` and `PaletteController()`. For the page that shows a level of brightness on the graph has to constantly send requests to fill the graph with the data from the LDR sensor, so class `LDRDataController()` was implemented. It sends requests to retrieve data gathered in one day, one week or one month.  To set up the system, it should request the information about the color, brightness and size of the display, so class SetUpController was made. Also, it can send `POST` requests to update previously mentioned values. All it does is send `JSON` objects consisting of RGB value, brightness level, size of display and boolean of power state. During receiving, it converts it to a `SetUp()` object and does  the purpose of request. The last controller is a PaletteController, that is responsible for requests of `Animation()` and `Preset()` objects. It retrieves a list of objects and presents them on the user interface. 


#### 4.2.4 GPIO 
We chose to use a Light Dependent Resistor as our sensor for the project. The photoresistor is connected to the Pi’s GPIOs with 3 female-to-male cables and a breadboard. Moreover, we used a jumper cable and a 10µF capacitor. The wiring was done the following way: 1 female-to male cable is connected to the pin number 1 of the Pi’s GPIO. This is the 3v3 power pin which powers the entire circuit. Another female-to-male cable is connected to the 6th pin which is the ground pin on the Raspberry Pi 4. The last female-tomale wire is connected to the pin number 7 that reads the data from the LDR. A capacitor is used in this circuit so we’re able to measure the resistance of the LDR sensor.
#### 4.2.5 LED strip


In order to wire the LED strip to the raspberry pi we had to use a power supply. Exploitation of a power supply is needed to convert the voltage of the LED which is 5v to 3.3v of Pi’s GPIO.
We use a power supply of 5v and 20A, thus it provides enough power for the LED pixels as far as each takes 60mA and our LED strip is 60leds/m and has length of 5m and thus contains 300 pixels. WS2812B has a high frequency of led pixels which gives solid colours and enough brightness.
Thus, we directly connected the 5v wire of the LED strip to the power supply’s DC output terminal and NeoPixel GND to the power supply’s ground. In addition, we connected the NeoPixel’s data connection (Din) to the pi’s GPIO 18 and the second NeoPixel’s ground cable to the Pi’s GND. In order to give a smooth representation of colours and decrease the intensity of diodes we used silicon tubes that are transparent and spread the light uniformly. The LED strip was mounted on the TV monitor by means of wire holders that were attached to the back side of the monitor. This approach makes the set up of the led strip on the monitor quite fast and easy.  
### 4.3 Database
The database for this project, that is called lightingsystem, stores information about setup, ldr data, presets and animation. THe setup table stores information about p and q, which are width and length of the screen(stored as smallint), r,g,b which are well-known parameters for identifying color(stored as smallint, actually is processed as an integer between 0 and 255), a, which is brightness parameter(stored as real, so processed as value between 0 and 1) and anim_id and preset_id, smallint parameters which connect setup to animation and preset table. In addition, the setup table contains boolean power, which is basically information whether the system is on or off. The animation table stores data of each animation for the LED strip. Each animation has its own unique id, the name and the icon. The preset table stores data of each preset for the LED strip. Rach preset has the same parameters as amination: id, name, icon. Regarding the ldr_data table, it stores the actual data retreived from the LDR sensor, which is the brightness retrieved from LDR every 1 hour every day by default if the system is constantly working. So the data and the timestamp here are stored. Furthermore, we created several views for our database, which are helping to sort the info for the LDR data graph in the user interface. Three views, ldr_data_day, ldr_data_week, ldr_data_month are showing data from LDR during a day, average value per each day in a week, average value per each day in a month. With this information our LDR data graph is easily built. The database was tested by inputting the data manually and sorting it accordingly, also tested from the back end side.
## 5. Design (How to map application onto Hardware and Software)
Hardware and software mapping is described to indicate various hardware devices and equipment used in our system and its interaction with software components. During the design stage, we created Class and Activity diagrams to visually explain how different parts of our system are going to interact between each other, specifically hardware, software, and user interface. All the information about design and how different parts of the system are interconnected can be found in the separate document called “Software Design Document”. 
## 6. Implementation (How to implement the different parts)
A complex description of every part of our system is described in the ### 4.1 Front-End and ### 4.2 Back-end subparagraphs of Application specification.
## 7. Testing
In order to make sure the code works properly testing is required. With testing, some important vulnerabilities can be found as well as internal errors that have to be fixed before the delivery of the final product. To test the data flow between front-end, back-end and hardware Postman application was used to capture and send requests with JSON objects. Database could be accessed with Postman with GET or POST requests and our system did not require any other  requests. Database was updated every hour with data from the LDR sensor. To check it,  POST requests were sent from Postman in order to see that data is stored with a proper type and time. To retrieve the data and show it on the user interface, a GET request was made to make sure that the JSON object was assembled and delivered correctly.  Following the same logic every implemented controller was checked the same way. The problems that were encountered while this testing showed that there is the problem either in the back - or front-end side. These can be the problems with the connection to the database due to some database modifications, or with wrong reference to database. Back-end and front-end wise problems could be regarding errors in methods, so further debugging was required. 


## 8. Planning and definition of tasks for the individual members
### 8.1 Team Meeting
While we were working on this project, we could not meet physically regularly. Covid-19 measurements prevent us from meeting on campus as a group. That is why we tried to meet through Google Meets as much as possible. During the meetings, we were able to help each other with the obstacles that we had and discuss the ideas that we wanted to add on the project. Besides that, we were always setting the objectives for the next meeting that we are going to have. We tried to set our SMART objectives which stands for Specific, Measurable, Achievable, Relevant and Timely in full measure.That is one of the most important points of the team meetings.        
### 8.2 Cost Calculation
Since our project was not only with Raspberry Pi and a sensor, we had to organise a few meetings to find the most suitable hardware tools for the project. That is why, as a first step, we created a requirement list which includes the tools and the equipment that we were going to need during the process. Since we had the requirement list, we set a budget that fits every member within the team. Being 6 people for this project allowed us to have a good amount of budget that increased the quality of the products that we used. As a direct cost, we had to obtain an Addressable RGB LED Strip to access every LED individually and a Power Supply for it since Raspberry Pi’s voltage was enough to handle.


### 8.3 Functional and Non-Functional Requirements 
Once we decide on the idea that we are going to make, we set the functional and non-functional requirements which means what the system does or must not do and specify how the system should do respectively. Once we chose the project idea, we basically had most of the requirements of the project and we tried to add more ideas to expand our project in an effective way. At first, we got all the stakeholders involved and we brainstormed for four different groups which are business requirements, administrative functions, user requirements and system requirements to improve our functional requirements. For the non-functional requirements, the most important points that we considered were the usability, reliability and scalability.


### 8.4 Document Management
One of the most important points within the project management is keeping the documentation safe and organised. That’s why, within the process, we tried to shrink away from chaotic structures and processes. Poor document management has many negative effects on many aspects of the project. All of the group members were aware that lack of document management can cause the reduction of the quality, fritter away time and missing documents. We agreed upon to use Google Drive and GitLab to achieve this goal. In that way, we were able to have enhanced security, better collaboration and product development lifecycle support. Since we used version control, we always had backups of our works and were also able to work more efficiently and have traceability which means that being able to trace each change made to the software.






### 8.5 Task Division
Task division is the most important point for the group projects. Everyone needs to be able to handle their responsibilities to complete astonishing works. We wanted to fit in the Belbin Team Roles. We all agreed upon that it is a convenient way to assign roles. After we were done with our planning and dividing the functionalities into the tasks, we had a meeting where everyone can express their strengths and weaknesses to get responsible for the most suitable part of the project for themselves. That was mostly based on the roles that we got for the previous projects. Since we were six people in the group, we decided to divide into three different groups where every group can work on different tasks. The groups within the team were responsible for the front-end, back-end and physical part. However, when someone had a problem, we figured out to work on different groups as well to help each other which made us multi-disciplinary.
## 9. Conclusion
In conclusion, it took 8 weeks to finish the project Smart Home Lightning System. During these 8 weeks, we tried to adapt to the project phases as much as possible. They were choosing project idea, identifying the requirements, planning, design and development phases. At first, we wanted to make sure that we have the most reliable and impressive one and within the first sprint, we also set our GitLab repository. We continued with setting the functional and non-functional requirements which were the essential points for the requirement analysis document which were essential points for the project. According to the requirements that we set, we made our mockup and started developing the project based on the mockup and the functional requirements. Our main aim within this project period was to be positive about having a unique and reliable product. Hard work and this aim brought us to the product that we all are proud of. It was a great opportunity to learn more about the project which involves software and hardware concepts.

## 10. Reflection
What we have learned from this project has irreplaceable value for all of the team members of the project. It has many benefits to ourselves that we assess how to make a connection between both the frontend and backend and the physical part of the project. We had new experiences with new tools which are both hardware and software. Beside our academic knowledge, the team collaboration, communication and problem solving are the only few of the skills that we improved with it. It showed us how to deal with the problems with helping one another to understand the materials. That also made us understand our strengths and weaknesses that steered our tasks accordingly. As a team, we were unlucky with the customizing the sources that we used which did not let us to take further to the project frequently. Understanding the concept of the materials was complex and took a lot of time. In retrospect, our team collaboration and team working is well planned that brought us an amazing project with brilliant features. 

##  Reference
**NOTE**: all links and references have been mentioned IN APA STYLE in the text.


* Atlassian. (n.d.). What is version control | Atlassian Git Tutorial. https://www.atlassian.com/git/tutorials/what-is-version-control#:%7E:text=non%2Dsoftware%20projects.-,Benefits%20of%20version%20control%20systems,scales%20to%20include%20more%20developers.


* Chaouni, M. (n.d.). 7 Powerful Advantages of Using a Document Management System. Business 2 Community. https://www.business2community.com/tech-gadgets/7-powerful-advantages-using-document-management-system-01148648


* Islam, T. (2017). Project Report of Website Development Project management. Academia.Edu. https://www.academia.edu/35250128/Project_Report_of_Website_Development_Project_management


* M. (2020, October 27). Functional vs Non-Functional Requirements: The Definitive Guide. QRA Corp. https://qracorp.com/functional-vs-non-functional-requirements/#:%7E:text=Functional%20requirements%20define%20the%20basic,data%20input%2C%20and%20business%20processes.


* Marker, A. (n.d.). The Ultimate Guide to Project Cost Estimating. Smartsheet. https://www.smartsheet.com/ultimate-guide-project-cost-estimating#:%7E:text=To%20use%20parametric%20estimating%2C%20first,to%20estimate%20the%20total%20cost.