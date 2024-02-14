package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

public class CreateSudokuPad {

    public void createSudokduPadLink(String data) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        WebDriver driver = new FirefoxDriver(options);
        String finalURL = "";
        try {
            driver.get("https://swaroopg92.github.io/penpa-edit/");

            // Deals with the new-grid/update button and consequent pop-up menus
            clickElement(driver, "newboard");
            selectSudokuGrid(driver);
            clickElement(driver, "closeBtn_nb1");
            clickElement(driver, "swal2-confirm");
            clickElement(driver, "input_sudoku");

            // Deals with the I/O Sudoku Button
            insertTextToElement(driver, "iostring", data);
            clickElement(driver, "load_input");

            // Gets out of the I/O Sudoku pop-up menu
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.id("modal-input-content")), 0,0);
            actions.moveByOffset(0,400).click().build().perform();

            // clicks the clone button
            clickElement(driver, "duplicate");
            String penpaURL = getURLOfNewTab(driver);

            // Go to the conversion website and convert the link
            driver.get("https://marktekfan.github.io/sudokupad-penpa-import/");
            insertTextToElement(driver, "input-url", penpaURL);
            clickElement(driver, "btnconvert");
            // Let the new tab load before getting the URL, otherwise auto-blank is obtained
            Thread.sleep(2000);
            finalURL = getURLOfNewTab(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
        System.out.println(finalURL);
        StringSelection stringSelection = new StringSelection(finalURL);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void clickElement(WebDriver driver, String ElementID) {
        WebElement element = null;
        try {
            element = driver.findElement(By.id(ElementID));
        } catch (NoSuchElementException e) {
            element = driver.findElement(By.className(ElementID));
        }
        element.click();
    }

    public void insertTextToElement(WebDriver driver, String ElementID, String inputText) {
        WebElement textArea = driver.findElement(By.id(ElementID));
        textArea.sendKeys(inputText);
    }
    public void selectSudokuGrid(WebDriver driver) {
        WebElement selectGridType = driver.findElement(By.id("gridtype"));
        Select gridType = new Select(selectGridType);
        gridType.selectByValue("sudoku");
    }

    public String getURLOfNewTab(WebDriver driver) {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String newTabHandle = windowHandles.get(windowHandles.size() - 1);
        driver.switchTo().window(newTabHandle);
        String newTabURL = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(windowHandles.get(0));
        return newTabURL;
    }

}
