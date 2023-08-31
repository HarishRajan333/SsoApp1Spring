package com.mycompany.application1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App1Controller {

    @GetMapping("/admin/getAts-data")
    public String getApp1AdminData() {
        return "Login as Applicant Tracking System's Admin.";
    }

    @GetMapping("/recruiter/getAts-data")
    public String getApp1RecuriterData() {
        return "Login as Applicant Tracking System's Recruiter .";
    }

    @GetMapping("/interviewers/getAts-data")
    public String getApp1InterviewerData() {
        return "Login as Applicant Tracking System's Interviewer .";
    }

}
