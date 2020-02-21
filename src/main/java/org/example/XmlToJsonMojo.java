package org.example;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.json.JSONObject;
import org.json.XML;

@Mojo(name = "XmlToJson")
public class XmlToJsonMojo extends AbstractMojo {
  /**
   * XML document absolute path
   */
  @Parameter(property = "sourceXmlFilePath", required = true)
  private String sourceXmlFilePath;

  /**
   * JSON document absolute path
   */
  @Parameter(property = "destinationJsonFilePath", required = true)
  private String destinationJsonFilePath;

  private static int PRETTY_PRINT_INDENT_FACTOR = 4;

  /**
   * This is the overridden method that converts the XML
   * document to an equivalent JSON document
   * @throws MojoExecutionException - if an unexpected problem occurs. Throwing this exception causes a "BUILD ERROR" message to be displayed.
   * @throws MojoFailureException - if an expected problem (such as a compilation failure) occurs. Throwing this exception causes a "BUILD FAILURE" message to be displayed.
   */
  public void execute() throws MojoExecutionException, MojoFailureException {
    try {
      String xmlContent = FileUtils.readFileToString(new File(sourceXmlFilePath), "UTF-8");
      JSONObject xmlJSONObj = XML.toJSONObject(xmlContent);
      String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
      FileUtils.writeStringToFile(new File(destinationJsonFilePath), jsonPrettyPrintString, "UTF-8");
    } catch (IOException e) {
      throw new MojoExecutionException("Failed to execute plugin", e);
    }
  }
}
