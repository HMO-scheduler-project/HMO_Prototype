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

Connection to server setup screen:

![image](https://user-images.githubusercontent.com/84031027/150212629-6aced095-d3af-43ee-9a70-545b2fe4659a.png)


Choose device screen:

![image](https://user-images.githubusercontent.com/84031027/148525817-05cf6146-66a9-4a9c-a7f5-d3625592fe47.png)

**PC:**

login screen:

![image](https://user-images.githubusercontent.com/84031027/148526063-2eef8289-341b-42ff-a3eb-c3b64b0ffd37.png)

Patient main page:

![image](https://user-images.githubusercontent.com/84031027/150212102-7e3d4370-ad0e-40c2-8581-91641fbcf4aa.png)

Employee main page:

![image](https://user-images.githubusercontent.com/84031027/150212290-584eff8c-bfe6-4d75-8414-4d17fb1345c1.png)

Manager main page:

![image](https://user-images.githubusercontent.com/84031027/150212189-d5a29046-6dfa-4256-a2e9-7dbd306bb372.png)

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

reports:

![image](https://user-images.githubusercontent.com/84031027/150212767-ee9f6cee-9184-48e8-aca3-6c64c6abfc43.png)

![image](https://user-images.githubusercontent.com/84031027/150212818-56d6613a-3206-4b24-92b5-2349e66684d4.png)

![image](https://user-images.githubusercontent.com/84031027/150212937-5e10ffb9-4fc1-43d3-a302-a1692bc02ea7.png)

add new appointment menu:

![image](https://user-images.githubusercontent.com/84031027/150213163-c85c0c79-8b92-4aa8-b824-656a783f6324.png)

Family/children doctor appointment screen:

![image](https://user-images.githubusercontent.com/84031027/150213213-da242abc-d855-4c2d-99f7-ecabf99e4dbc.png)

![image](https://user-images.githubusercontent.com/84031027/150213371-7703eefb-a89e-4206-ba8a-a35b7caaa9b2.png)

After saving a doctor appointment - we are back to view appointments screen and this message is showing:

![image](https://user-images.githubusercontent.com/84031027/150213571-0d7e6ebb-9fda-4fb1-9da1-cd6d64d7696e.png)

Specialty doctor appointment:

![image](https://user-images.githubusercontent.com/84031027/150213627-9c8fe1dc-e9f4-463e-b9b0-bb97ef61fdc3.png)

![image](https://user-images.githubusercontent.com/84031027/150213687-8e06524b-4556-46d2-887e-5e7a54d8e1b6.png)

![image](https://user-images.githubusercontent.com/84031027/150213737-dee6509c-9a40-4ad3-b5c7-28b37287827a.png)

![image](https://user-images.githubusercontent.com/84031027/150213767-fb9df876-557a-4e82-b6f1-66bbea0256df.png)

after saving the appointment:

![image](https://user-images.githubusercontent.com/84031027/150213827-65de7942-ce7e-48ad-b7f6-214f52e8baee.png)

Vaccine new appointment:

![image](https://user-images.githubusercontent.com/84031027/150213913-a6c711fd-a7a2-4d86-86df-59566694c5cc.png)

![image](https://user-images.githubusercontent.com/84031027/150213958-23ffb2a1-08e1-4619-b2b5-c994bb2f2c55.png)

![image](https://user-images.githubusercontent.com/84031027/150214004-c59e9ced-647c-4da1-9465-f6d1bc102954.png)

![image](https://user-images.githubusercontent.com/84031027/150214099-af84d986-d2ff-4548-88ec-96c2434b2660.png)

Covid 19 test appointment:

![image](https://user-images.githubusercontent.com/84031027/150214181-ccfae802-c34b-4a90-bb68-8bea6d10cca5.png)

![image](https://user-images.githubusercontent.com/84031027/150214205-eb9b80b4-0e31-4934-ba3c-8f0f7a75117d.png)

![image](https://user-images.githubusercontent.com/84031027/150214268-8ffb759a-54a2-4d21-8866-e2ec1271d262.png)

![image](https://user-images.githubusercontent.com/84031027/150214309-c36a9c7d-5778-4317-8537-0fd35a7ba7d9.png)

![image](https://user-images.githubusercontent.com/84031027/150214341-49d86e50-79f2-42c0-a58b-04fdb95da60b.png)

Cancel appointment:

![image](https://user-images.githubusercontent.com/84031027/150214450-d27c5873-07bc-4323-947f-ae9e22913b57.png)

![image](https://user-images.githubusercontent.com/84031027/150214492-55984054-b6f8-4884-94ff-5d348b461403.png)

view appointments:

![image](https://user-images.githubusercontent.com/84031027/150214579-b1edb3e8-2264-462f-b2d1-3a13d657aa44.png)

get green pass:

![image](https://user-images.githubusercontent.com/84031027/150214642-651f4666-eb09-4416-b2eb-bd8ed8f37c96.png)

Sends Emails to patients after scheduling new appointment with appointment details and sends reminders to patients a day before their appointment:

![image](https://user-images.githubusercontent.com/84031027/150214993-7451f804-145d-474d-a2f5-2a5bddbac5ec.png)

![image](https://user-images.githubusercontent.com/84031027/150215038-fcd8120a-6e4b-4508-9517-06d3b25377ed.png)


**Appointment station:** 

choose clinic screen:

![image](https://user-images.githubusercontent.com/84031027/148946749-c3f59785-40fd-411c-b218-b16c87b0121e.png)

login with card:

![image](https://user-images.githubusercontent.com/84031027/148946865-c51ee7c9-47fe-4052-a5f7-b5cacaa99149.png)

main page:

![image](https://user-images.githubusercontent.com/84031027/148952748-1b08c3df-d0f5-48fa-9c7d-92ca0ea49e4b.png)

provide ticket for existing appointment:

![image](https://user-images.githubusercontent.com/84031027/150215429-ec64e4a9-8d39-40f4-9d66-71f0656670cd.png)

schedule new nurse appointmant:

![image](https://user-images.githubusercontent.com/84031027/150215610-b9419017-7840-4c2e-bdc8-126e053e0ddf.png)

schedule new lab appointment:

![image](https://user-images.githubusercontent.com/84031027/150215749-a32569c9-b885-47fe-8c05-115c4268205c.png)

waiting room screen:

![image](https://user-images.githubusercontent.com/84031027/152314314-4a688999-93f3-400a-9eb7-e2988d23f173.png)






