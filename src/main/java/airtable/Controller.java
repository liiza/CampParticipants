package airtable;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private AirtableApiService airtableApiService;

    @RequestMapping("/participants")
    public String listParticipants() {
        return airtableApiService.makeGETApiRequest("Participants");
    }

    @RequestMapping("/organizations")
    public String listOrganizations() {
        return airtableApiService.makeGETApiRequest("Organizations");
    }

    @RequestMapping(value = "/participant", method = RequestMethod.POST)
    public ResponseEntity<String> addParticipant(@RequestBody String participant){
        String s;
        try {
            s = airtableApiService.makePOSTApiRequest("Participants", participant);
        } catch (AirtableApiError airtableApiError) {
            airtableApiError.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(s, HttpStatus.ACCEPTED);
    }

}