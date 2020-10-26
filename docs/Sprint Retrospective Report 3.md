# Sprint Retrospective Report 3

**Note:** Elaborate every answer in your own words, based on your team performance and experience. You are required to submit a md file (1000-1500 words) for this assignment.
 
| **Team  ID:** | 22 |
| -----------------: | :--- |
| **Group  ID:** | Pi8 |
| **Team  Members:** | Lola Solovyeva, Albina Shynkar, Illya Averchenko, Alexandru Matcov, Jasper van Amerongen, Bugra Veysel Yildiz |
| **Scrum  Master:** | Illya Averchenko |
| **Scrum  Mentor:** | Chandni Raghuraman, Andrew Heath |
 ## Q1: What are the things that went well during the last sprint? 
* **Major progress regarding user interface**
Front-end team worked on improving the main page, which contains the color palette  and brightness setter. Furthermore Google Single Sign On was implemented, so students with UT accounts can log in for now.  Also, from now on the web-page can be used on tablets and laptops. 

* **We managed to light up the led strip** 
Physical team did a great job. The team fulfilled one of the important requirements for the project, which formed our MVP. 

* **Online meetings**
Since we could not meet up physically due to COVID-19, we still could manage online meetings regularly to keep progress on our project. 

* **Raspberry Pi and Tomcat server**
Back-end was focusing on setting up a server on Raspberry Pi. We agreed on using Tomcat, since Java is the main language we use. 

* **Workload**
Even when the workload was pretty intense(assignments, exams and resits), we managed to keep up with all project requirements and remain focused.

## Q2: What are the things that could have gone better during the last sprint?
* **Meetings**
We could have met more often and sustained the communication, however the focus of the group during the last week shifted towards the exams and other assignments.

* **Spend more time on the project**
We could have spent more time working on the project, but we could not because of exams or resits we had

* **The amount of progress we had**
We did some really valuable steps for our project, however we could have done more progress

* **Better productivity**
Due to the fact that we had to pay attention to other study parts, we could not manage major progress on the project, despite the fact that we did important steps

* **Fix issues**
During the sprint we faced a problem with libraries, that we still can not fix. Next time, we should ask for help as soon as we realize it is something we can not figure out ourselves. 

## Q3: What did you learn during the last sprint on a….
### a) Domain specific level?
* **Lighting up LED strip**
In the beginning of the Sprint some team members went to the electrical engineer’s lab in order to solder the wires. So, we have learned how to correctly and safely connect copper and aluminium wires, how to use the solder and how to isolate the wires by means of heating the extra layer of isolation material on the top of the connection that was soldered before. Isolating the wires helps to avoid current leakages and preventing accidental current from reaching ground through a person’s body.

* **Setting up a Tomcat server**
One of the goals for us was figuring out how to set up a server on the raspberry pi. We chose Apache Tomcat,  since we already have experience using it  from module 4. Goal was to figure out how to use what we already know to Raspberry Pi. Installation was not the hardest part, but creating an extra user with manager privileges took us time. However, after that was done, WAR file of the project was uploaded and deployed. Right after we could see our web-page up and running.

* **Google Single Sign-On**
In module four we used Google SSO for all our security needs and this sprint we learned how to integrate and moreover reuse our code to set up a quite good security system.
### b) Intra-personal level?
* **Individual tasks**
We have came up with an agreement that during the past Sprint we focus more on each one’s individual tasks and leave the project work for later

* **Solving conflicts within the team**
We were able to discuss and solve the problems that we had within the team via communication.
## Q4: What are your plans for the next sprint?
* **Better progress on backend**
We have to find a proper library to use it to implement different modes and presets and actually develop all modes and presets for MVP.

* **Connect UI to physical layer with backend**
By the end of the sprint we should have all three subteams of the project working  together in good order.

* **UI progress**
We have to finish all features for MVP regarding user interface.

* **Physical layer**
WE should have our LDR working properly and the information from it should be sent to the backend.

* **MVP**
In general, our minimal viable product should be done and fully working by the end of the sprint.
## Q5: Select your team performance and satisfaction score from the following. Don’t forget to justify your answer.

| **Satisfaction-level  and Value** | **Justification** |
| -----------------------| ----------------- |
| Very  Happy (5)  | |
| Happy  (4)  | |
| **Okay  (3)**  | We planned on finishing our MVP, but due to unforeseen circumstances, we could not finish what we had planned. |
| Sad  (2) | |
| Very  Sad (1) | |

## Q6: Which team member was most appreciated during the last sprint (only list 1) and why?
**Illya Averchenko**: we appreciate him for being a scrum master and managing to light up the LED strip.
## Q7:  Would you like to specify any other priority detail(s)/points which could be useful for the development of your product (if any)?
We have some points in our project which have higher priority than others. We want to connect the tools that we have collected for our project to the Raspberry Pi. We need to figure out how to make LDR transfer data to the backend, the information flow from UI to backend and from backend giving instructions to pi. After that, we want to finish our minimal viable product with all features on UI, all modes and patterns on backend. Also, we can examine the data that the sensor reads and then have a solid idea on how to use that data for the other features that we are going to implement.