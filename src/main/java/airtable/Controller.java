package airtable;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private AirtableApiService airtableApiService;

    @RequestMapping("/participants")
    public String listParticipants() {
        return airtableApiService.makeApiRequest("Participants");
    }

    @RequestMapping("/organizations")
    public String listOrganizations() {
        return airtableApiService.makeApiRequest("Organizations");

    }

}