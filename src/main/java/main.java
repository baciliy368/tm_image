import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserManager;
import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class main {
    public static void main(String[] args) {
        IButton b = new ElementFactory().getButton(By.xpath("//button[@class='colored_button page-switcher-r']"), "zxc");
        BrowserManager.getBrowser().goTo("https://market.csgo.com/?s=name&r=&q=Factory%20New,%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%20%D1%81%20%D0%B7%D0%B0%D0%B2%D0%BE%D0%B4%D0%B0&h=&fst=0");
        List<IButton> elements = new ElementFactory().findElements(By.xpath("//div[@id='applications']//a[contains(@class,'item')]"), ElementType.BUTTON);
        for (int i = 0; i < elements.size(); i++) {
            try {
                IButton element = elements.get(i);
                String attribute = element.findChildElement(By.className("image"), ElementType.BUTTON).getAttribute("style");
                String url = (attribute.replaceAll(".+url\\('", "")).replaceAll("'.+$", "");
                IButton childElement = element.findChildElement(By.xpath("//div[@class='name']"), ElementType.BUTTON);
                String nameAt = childElement.getText();
                String name =  nameAt.replaceAll("\\(.+", "").trim();
                String link = url.replaceAll(".+url\\(", "").replaceAll("'.+$", "").replaceAll("\"", "")
                        .replaceAll("\\)$", "").replaceAll(";", "").replaceAll("'", "").trim();
                try{
                    String s = link.replaceAll("\\)$", "").replaceAll("/100.png", "/300.png");
                    System.out.println(s);
                    URLConnection openConnection = new URL(s).openConnection();
                    openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                    InputStream in  = openConnection.getInputStream();
                    Files.copy(in, Paths.get("img/"+name.toLowerCase() + ".png"));
                } catch (Exception e){
                    e.printStackTrace();

                }
            } catch (Exception ignored){


            }
            if(i==elements.size()-1){
                b.click();
                i=1;
            }
        }


    }
}
