package org.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "query")
public class MyQueryMojo
    extends AbstractMojo
{
  @Parameter(property = "query.url", required = true)
  private String url;

  @Parameter(property = "timeout", required = false, defaultValue = "50")
  private int timeout;

  @Parameter(property = "options")
  private String[] options;

  public void execute()
      throws MojoExecutionException
  {
        System.out.println("query.url value: " + url);
  }
}