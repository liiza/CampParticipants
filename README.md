# CampParticipants
Building simple web client against Airtable -api.
https://airtable.com/

A form where kids can sign in to a scout camp.
The participant information is saved directly to the airtable spreadsheet, which is easy to use for everyone. 
Camp organizers e.g. a person cooking for the camp can easily browse through the participant data. And even better the 
connection works two ways. The form fetches the organizations from api. Someone can easily modify the
organization list in the airtable spreadsheet and changes are updated directly to the form.

The backend is Spring Boot and front end AngularJS app.

The backend works as a proxy server, that signs the requests against actual Airtable-api.
That way, we don't need to keep the api key in front end.

![Screenshot] (https://github.com/liiza/CampParticipants/blob/master/screenshots/campform.png)
