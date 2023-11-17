package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam(name = "searchType", defaultValue = "all") String searchType, @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        List<Job> jobs;

        if ("all".equalsIgnoreCase(searchTerm) || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columnChoices", ListController.columnChoices);
        return "search";
    }

//    private List<SearchResult> performSearch(String searchQuery) {
//        // Your implementation to perform the search and return results
//        // Replace this with your actual search logic
//        List<SearchResult> results = new ArrayList<>();
//        // Perform the search and populate the results
//        return results;
//    }

}

