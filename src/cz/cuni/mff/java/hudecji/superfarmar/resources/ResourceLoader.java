package superfarmar.resources;

import java.io.InputStreamReader;
import java.io.Reader;

public class ResourceLoader {

  private static final String RESOURCE_PREFIX = "/superfarmar/resources/";
  private static final String RESOURCE_IMAGE = RESOURCE_PREFIX + "images/";
  private static final String RESOURCE_HELP = RESOURCE_PREFIX + "text/help.txt";

  /**
   * Load help from file
   * 
   * @return Reader from which provides help.
   */
  public static Reader getHelp() {
    return getResourceAsStream( RESOURCE_HELP);
  }
  
  /**
   * Load one image inte stream
   * 
   * @param imageName Name of image (without extension).
   * @return Reader from which provides image.
   */
  public static Reader getImage(String imageName) {
    return getResourceAsStream( RESOURCE_IMAGE + imageName + ".jpg");
  }
  
  private static Reader getResourceAsStream(String resourceName) {
    return new InputStreamReader(ResourceLoader.class.getResourceAsStream(resourceName));
  }
}
