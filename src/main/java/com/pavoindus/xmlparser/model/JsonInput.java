package com.pavoindus.xmlparser.model;

import java.util.Map;

/**
 * Created 4/27/2018 18:56
 *
 * @author Deepankar Sharma
 */
public class JsonInput {

  private Map<String, Content> contents;

  public Map<String, Content> getContents() {
    return contents;
  }

  public void setContents(Map<String, Content> contents) {
    this.contents = contents;
  }

  private class Symbols {

  }
}
