package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wdMethods.ProjMethods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class webElements extends ProjMethods {

    static String[][] data = null;
    List<WebElementInfo> webElementsInfo = new ArrayList<>();

    public void webElements() throws IOException {

        ReadExcel read = new ReadExcel();
        data=read.getSheet("testsheet.xlsx","xpaths");

        //HashMap<String, String> map = new HashMap<>();

        //System.out.println(data.length);

        for (int i=0;i< data.length;i++) {

            String name = data[i][0].trim();
            String xpath = data[i][1].trim();

            if (xpath != null && name != null) {

                By locator = By.xpath(xpath);

                WebElement webElement = driver.findElement(locator);

                webElementsInfo.add(new WebElementInfo(name, webElement));
            }
            //map.put(name, xpath);

        }
        //System.out.println(map);

        generateJavaFile(webElementsInfo);


    }


    private static void generateJavaFile(List<WebElementInfo> webElementsInfo) throws IOException {
        StringBuilder javaCode = new StringBuilder();

        javaCode.append("import org.openqa.selenium.WebElement;\n");
        javaCode.append("import org.openqa.selenium.support.FindBy;\n\n");
        javaCode.append("public class WebElements {\n");

        for (WebElementInfo info : webElementsInfo) {
            javaCode.append(String.format("    @FindBy(xpath = \"%s\")\n", info.getXpath()));
            javaCode.append(String.format("    public WebElement %s;\n\n", info.getName()));
        }

        javaCode.append("}");

        // Write javaCode to a .java file
        // You can use FileWriter, PrintWriter, etc. to write the code to a file
        FileWriter writer = new FileWriter("./src/main/java/pages/Elements.java");
         writer.write(javaCode.toString());
         writer.close();
        System.out.println(javaCode.toString()); // For demonstration purposes, print to console
    }
    public static class WebElementInfo {
        private final String name;
        private final String xpath;

        public WebElementInfo(String name, WebElement element) {
            this.name = name;
            this.xpath = element.toString();
        }

        public String getName() {
            return name;
        }

        public String getXpath() {
            return xpath;
        }
    }
}

