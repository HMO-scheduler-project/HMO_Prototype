# HMO Appointments Scheduler
The scheduler was implemented using java, javaFX for the Frontend and Hibernate for Communication with MySQL database.

## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and various controllers.)
2. **server** - a simple server built using OCSF. All the Communication to the database occurs between the server and the database. 
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!

Choose device screen:

![image](https://user-images.githubusercontent.com/84031027/148525817-05cf6146-66a9-4a9c-a7f5-d3625592fe47.png)

**PC:**

login screen:


![image](https://user-images.githubusercontent.com/84031027/148526063-2eef8289-341b-42ff-a3eb-c3b64b0ffd37.png)

Patient main page:

![image](https://user-images.githubusercontent.com/84031027/148527859-efeb9ad5-28f1-4bde-9820-4e0dfa9b1522.png)

Employee main page:

![image](https://user-images.githubusercontent.com/84031027/148526573-8576b596-776d-4cce-afc9-ff4e8f2c60c6.png)

Manager main page:

![image](https://user-images.githubusercontent.com/84031027/148528934-61cfb91f-f478-492c-bcba-4cdf2dbd69c9.png)

clinic hours screen:

![image](https://user-images.githubusercontent.com/84031027/148528020-3e3cbe00-5a92-40cc-80da-f08b7e710fcd.png)
![image](https://user-images.githubusercontent.com/84031027/148528063-30e9ef91-5261-4e8a-9849-2cbee0d37893.png)

clinic contact information screen:

![image](https://user-images.githubusercontent.com/84031027/148528158-3f8ee660-18a6-4bcb-b75e-2c0279531a3a.png)

update clinic and clinic's services hours screen:
![image](https://user-images.githubusercontent.com/84031027/148689655-ca162613-4044-4987-9e3e-aa60f3c13be9.png)

![image](https://user-images.githubusercontent.com/84031027/148689673-fdc918a9-da8e-44cf-af5e-034f988f7126.png)

![image](https://user-images.githubusercontent.com/84031027/148689691-4fd83ea2-adc6-4a63-91ee-4d581752d9d0.png)

![image](https://user-images.githubusercontent.com/84031027/148689702-f4999d7f-d72f-4bc9-932e-57b9109ee3aa.png)

![image](https://user-images.githubusercontent.com/84031027/148692211-05b22323-9672-4a8e-814c-a84592c4ebbc.png)

**Appointment station:** 

choose clinic screen:

![image](https://user-images.githubusercontent.com/84031027/148946749-c3f59785-40fd-411c-b218-b16c87b0121e.png)

login with card:

![image](https://user-images.githubusercontent.com/84031027/148946865-c51ee7c9-47fe-4052-a5f7-b5cacaa99149.png)

main page:

![image](https://user-images.githubusercontent.com/84031027/148952748-1b08c3df-d0f5-48fa-9c7d-92ca0ea49e4b.png)

![image](https://user-images.githubusercontent.com/84031027/148952781-5171bdd1-16ea-47c4-b7e0-a9a507317c52.png)

![image](https://user-images.githubusercontent.com/84031027/148952859-239099a4-2955-438f-8c0b-402ce40dfcc3.png)

![image](https://user-images.githubusercontent.com/84031027/148954897-29a0f020-104d-4984-8de0-3902a13209b9.png)

![image](https://user-images.githubusercontent.com/84031027/148954950-0847b773-4b8c-41c8-8ca6-53b18335208d.png)




