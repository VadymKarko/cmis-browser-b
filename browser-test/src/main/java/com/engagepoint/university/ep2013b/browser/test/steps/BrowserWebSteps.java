package com.engagepoint.university.ep2013b.browser.test.steps;

import com.engagepoint.university.ep2013b.browser.test.pages.Pages;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class BrowserWebSteps {

    private final Pages pages;


    public BrowserWebSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on Home page")
    public void userIsOnHomePage(){
        pages.home().open();
    }

    @Then("Find title $title user")
    public void findTitle(String title){
        pages.home().found(title);
    }

    @Given("user is on Index page")
    public void userIsOnIndexPage(){
        pages.index().open();
    }

    @Then("Find text $title")
    public void findText(String title){
        pages.index().found(title);
    }

    @When("user clicks tree root")
    public void userClickTreeRoot(){
        pages.home().treeClick();
    }

    @Then("node $node is found")
    public void treeNodeIsSelected(String node){
        pages.home().found(node);
    }

    @Then("root is selected")
    public void treeRootSelected(){
        pages.home().treeClickCheck();
    }

    @When("user clicks root expand $strPath")
    public void userClickRootChild(String strPath){
        pages.home().treeExpand(strPath);
    }

    @Then("root is expanded by path '$strPath'")
    public void treeRootChildSelected(String strPath){
        pages.home().treeExpandCheck(strPath);
    }


}