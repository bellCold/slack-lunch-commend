package com.project.lunch.controller;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

public class SlackController extends SlackAppServlet {
    public SlackController(App app) {
        super(app);
    }
}
