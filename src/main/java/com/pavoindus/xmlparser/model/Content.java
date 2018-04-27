package com.pavoindus.xmlparser.model;

import java.util.Map;

/**
 * Created 4/27/2018 19:09
 *
 * @author Deepankar Sharma
 */
public class Content {

  private String classDefinition;
  private Map<String, Diagram> diagrams;
  private Map<String, Symbol> symbols;
  private Boolean isImmediate;
  private String name;
  private String displayName;
  private String source;
  private String target;
  private String points;
  private String sourceSymbol;
  private String targetSymbol;
  private String object;
  private Boolean isEventBased;
  private Integer startQuantity;
  private Integer completionQuantity;
  private Boolean isForCompensation;
  private Double x;
  private Double y;
  private Integer width;
  private Integer height;

  public String getClassDefinition() {
    return classDefinition;
  }

  public void setClassDefinition(String classDefinition) {
    this.classDefinition = classDefinition;
  }

  public Map<String, Diagram> getDiagrams() {
    return diagrams;
  }

  public void setDiagrams(Map<String, Diagram> diagrams) {
    this.diagrams = diagrams;
  }

  public Map<String, Symbol> getSymbols() {
    return symbols;
  }

  public void setSymbols(Map<String, Symbol> symbols) {
    this.symbols = symbols;
  }

  public Boolean getIsImmediate() {
    return isImmediate;
  }

  public void setIsImmediate(Boolean immediate) {
    isImmediate = immediate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getPoints() {
    return points;
  }

  public void setPoints(String points) {
    this.points = points;
  }

  public String getSourceSymbol() {
    return sourceSymbol;
  }

  public void setSourceSymbol(String sourceSymbol) {
    this.sourceSymbol = sourceSymbol;
  }

  public String getTargetSymbol() {
    return targetSymbol;
  }

  public void setTargetSymbol(String targetSymbol) {
    this.targetSymbol = targetSymbol;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public Boolean getIsEventBased() {
    return isEventBased;
  }

  public void setIsEventBased(Boolean eventBased) {
    isEventBased = eventBased;
  }

  public Integer getStartQuantity() {
    return startQuantity;
  }

  public void setStartQuantity(Integer startQuantity) {
    this.startQuantity = startQuantity;
  }

  public Integer getCompletionQuantity() {
    return completionQuantity;
  }

  public void setCompletionQuantity(Integer completionQuantity) {
    this.completionQuantity = completionQuantity;
  }

  public Boolean getIsForCompensation() {
    return isForCompensation;
  }

  public void setIsForCompensation(Boolean forCompensation) {
    isForCompensation = forCompensation;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }
}
