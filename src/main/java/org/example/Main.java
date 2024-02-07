package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.nytimes.com/puzzles/sudoku/hard")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0")
                        .header("Accept-Language", "*")
                        .get();
        Elements scripts = doc.getElementsByTag("script");
        String gameData = "";
        for(Element script : scripts) {
            if(script.html().contains("window.gameData")) {
                gameData = script.html();
            }
        }
        String JSONGameData = gameData.substring(gameData.indexOf("{"));
        System.out.println(JSONGameData);
        ObjectMapper objectMapper =  new ObjectMapper();
        NYTGameData nytGameData = objectMapper.readValue(JSONGameData, NYTGameData.class);
        String easyPuzzleData = nytGameData.getEasy().getPuzzle_data().toString();
        String mediumPuzzleData = nytGameData.getMedium().getPuzzle_data().toString();
        String hardPuzzleData = nytGameData.getHard().getPuzzle_data().toString() ;
        puzzleData easy = objectMapper.readValue(easyPuzzleData, puzzleData.class);
        puzzleData medium = objectMapper.readValue(mediumPuzzleData, puzzleData.class);
        puzzleData hard = objectMapper.readValue(hardPuzzleData, puzzleData.class);
        System.out.println(easy.createImportString());
        System.out.println(medium);
        System.out.println(hard);

        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://swaroopg92.github.io/penpa-edit/");
            WebElement newGrid = driver.findElement(By.id("newboard"));
            newGrid.click();
            WebElement selectGridType = driver.findElement(By.id("gridtype"));
            Select gridType = new Select(selectGridType);
            gridType.selectByValue("sudoku");
            WebElement applyNewGrid = driver.findElement(By.id("closeBtn_nb1"));
            applyNewGrid.click();
            WebElement confirmUpdateButton = driver.findElement(By.className("swal2-confirm"));
            confirmUpdateButton.click();
            WebElement inputSudoku = driver.findElement(By.id("input_sudoku"));
            inputSudoku.click();
            WebElement importTextArea = driver.findElement(By.id("iostring"));
            importTextArea.sendKeys(hard.createImportString());
            WebElement importSudokuButton = driver.findElement(By.id("load_input"));
            importSudokuButton.click();
            WebElement getOutOfImportWindow = driver.findElement(By.id("modal-input"));
            getOutOfImportWindow.click();
            WebElement clone = driver.findElement(By.id("duplicate"));
            clone.click();
            List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
            String newTabHandle = windowHandles.get(windowHandles.size() - 1);
            driver.switchTo().window(newTabHandle);
            String sudokuURL = driver.getCurrentUrl();
            System.out.println(sudokuURL);
            driver.close();
            driver.switchTo().window(windowHandles.get(0));
            driver.get("https://marktekfan.github.io/sudokupad-penpa-import/");
            WebElement inputURL = driver.findElement(By.id("input-url"));
            inputURL.sendKeys(sudokuURL);
            WebElement convertButton = driver.findElement(By.id("btnconvert"));
            convertButton.click();
            Thread.sleep(2000);
            windowHandles = new ArrayList<>(driver.getWindowHandles());
            String sudokuPadHandle = windowHandles.get(windowHandles.size() - 1);
            driver.switchTo().window(sudokuPadHandle);
            String finalURL = driver.getCurrentUrl();
            System.out.println(finalURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

//        try {
//            Document doc = Jsoup.connect("https://nine.websudoku.com/?level=4")
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0")
//                    .header("Accept-Language", "*")
//                    .get();
//            Elements allElements = doc.getAllElements();
//            String answer = doc.getElementById("cheat").attr("value");
//            String editMask = doc.getElementById("editmask").attr("value");
//            String pid = doc.getElementById("pid").attr("value");
//
//
//            StringBuilder unsolvedBoard = new StringBuilder();
//            for (int i = 0; i < editMask.length(); i++) {
//                if (editMask.charAt(i) == '0') {
//                    unsolvedBoard.append(answer.charAt(i));
//                } else {
//                    unsolvedBoard.append(0);
//                }
//            }
//            System.out.println(answer);
//            System.out.println(editMask);
//            System.out.println(unsolvedBoard);
//            System.out.println(pid);
////            for (Element element : allElements) {
////                System.out.println(element);
////                System.out.println("..............................");
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}