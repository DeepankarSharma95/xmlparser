package com.pavoindus.xmlparser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavoindus.xmlparser.model.Diagram;
import com.pavoindus.xmlparser.model.Content;
import com.pavoindus.xmlparser.model.JsonInput;
import com.pavoindus.xmlparser.model.Tree;

/**
 * Created 4/27/2018 18:05
 *
 * @author Deepankar Sharma
 */
public class XMLParser implements Runnable {

  private static final String flowClass = "sap.modeling.cdt.transaction.Flow";
  private static final String modelClass = "sap.modeling.cdt.transaction.Model";
  private static final String inputString =
      "{" + "  \"contents\": {" + "    \"c1a45d82-93eb-4328-b092-bf74fc3e2781\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Model\","
          + "      \"diagrams\": {"
          + "        \"b9dc8350-1706-41c6-a6cf-643169f07a7c\": {},"
          + "        \"1288854c-ba78-48f3-b3cb-8b57b6c68af1\": {"
          + "          \"name\": \"Task1\"" + "        },"
          + "        \"b9ac00e7-5e23-4bf0-8ec7-e8fbd0d697e2\": {"
          + "          \"name\": \"Task2\"" + "        },"
          + "        \"db89ffbd-46d5-4760-80d2-fb340f698873\": {"
          + "          \"name\": \"Task3\"" + "        },"
          + "        \"ffba8b27-8ffc-4f3c-aa45-aacf4e6385a9\": {"
          + "          \"name\": \"Task4\"" + "        },"
          + "        \"f3a95889-5c47-4564-bc34-c525191ae48b\": {"
          + "          \"name\": \"Task5\"" + "        },"
          + "        \"9e092a09-4dda-4425-b418-024d62ba4eb5\": {"
          + "          \"name\": \"Task6\"" + "       }," + "        "
          + "        \"ca83d590-9b66-423e-b493-badb06c277aa\": {"
          + "          \"name\": \"Task7\"" + "        }" + "      }" + "    },"
          + "    \"b9dc8350-1706-41c6-a6cf-643169f07a7c\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.Diagram\","
          + "      \"symbols\": {"
          + "        \"0579309b-2b2c-41ad-a7e6-46f016dffe7a\": {},"
          + "        \"e1f2702c-ec0e-40ee-bb56-a1bbf86e8d39\": {},"
          + "        \"bb564895-65ec-448d-9211-a2090896c652\": {},"
          + "        \"ffbeb1bc-d185-468a-af04-b3892f9835ae\": {},"
          + "        \"7c0fce39-62fc-46ed-91c1-5dd00f7b6a98\": {},"
          + "        \"d6a924e9-53e4-48a8-aa9f-25a9d4faec44\": {},"
          + "        \"5dd2a466-b6ae-4408-8abb-f0f0b4d829d4\": {},"
          + "        \"a5e55f59-303e-409a-bff5-052fbb11663a\": {},"
          + "        \"5e198272-34f7-4b8f-9f7d-1fd9c6679771\": {},"
          + "        \"f121a18e-e47a-488c-81bb-ee121928f6f8\": {},"
          + "        \"332a61c2-ef0b-4fbe-967a-718f3c9209b8\": {},"
          + "        \"48e8e9fc-2c3d-4272-beeb-05402cff16a3\": {},"
          + "        \"86b74abd-8190-4030-a5d8-a73922878579\": {}" + "      }" + "    },"
          + "  " + "    \"fee2883e-6da3-4a10-ab6d-61db7cd8907d\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow1\","
          + "      \"displayName\": \"Sequence Flow 1\","
          + "      \"source\": \"b9ac00e7-5e23-4bf0-8ec7-e8fbd0d697e2\","
          + "      \"target\": \"1288854c-ba78-48f3-b3cb-8b57b6c68af1\"" + "    },"
          + "    \"241cc1f9-70ec-4575-8be2-7c3797731c94\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow2\","
          + "      \"displayName\": \"Sequence Flow 2\","
          + "      \"source\": \"1288854c-ba78-48f3-b3cb-8b57b6c68af1\","
          + "      \"target\": \"db89ffbd-46d5-4760-80d2-fb340f698873\"" + "    },"
          + "    \"7c0fce39-62fc-46ed-91c1-5dd00f7b6a98\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.FlowSymbol\","
          + "      \"points\": \"578.4991202490878,488 578.4991202490878,557.9999988079071 431.6919100600322,557.9999988079071 431.6919100600322,627.9999976158142\","
          + "      \"sourceSymbol\": \"0579309b-2b2c-41ad-a7e6-46f016dffe7a\","
          + "      \"targetSymbol\": \"ffbeb1bc-d185-468a-af04-b3892f9835ae\","
          + "      \"object\": \"241cc1f9-70ec-4575-8be2-7c3797731c94\"" + "    },"
          + "    \"ffba8b27-8ffc-4f3c-aa45-aacf4e6385a9\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Task\","
          + "      \"isEventBased\": false," + "      \"startQuantity\": 0,"
          + "      \"completionQuantity\": 0," + "      \"isForCompensation\": false,"
          + "      \"name\": \"Task4\"," + "      \"displayName\": \"Sequence 4\""
          + "    }," + "    \"d6a924e9-53e4-48a8-aa9f-25a9d4faec44\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.TaskSymbol\","
          + "      \"x\": 828.0000000000003," + "      \"y\": 627.9999976158142,"
          + "      \"width\": 308," + "      \"height\": 188,"
          + "      \"object\": \"ffba8b27-8ffc-4f3c-aa45-aacf4e6385a9\"" + "    },"
          + "    \"1ccdf5bc-16f0-4983-8e05-689086b994b2\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow3\","
          + "      \"displayName\": \"Sequence Flow 3\","
          + "      \"source\": \"1288854c-ba78-48f3-b3cb-8b57b6c68af1\","
          + "      \"target\": \"ffba8b27-8ffc-4f3c-aa45-aacf4e6385a9\"" + "    },"
          + "    \"5dd2a466-b6ae-4408-8abb-f0f0b4d829d4\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.FlowSymbol\","
          + "      \"points\": \"681.1657869157544,488 681.1657869157544,557.9999988079071 982.0000000000003,557.9999988079071 982.0000000000003,627.9999976158142\","
          + "      \"sourceSymbol\": \"0579309b-2b2c-41ad-a7e6-46f016dffe7a\","
          + "      \"targetSymbol\": \"d6a924e9-53e4-48a8-aa9f-25a9d4faec44\","
          + "      \"object\": \"1ccdf5bc-16f0-4983-8e05-689086b994b2\"" + "    },"
          + "    \"f3a95889-5c47-4564-bc34-c525191ae48b\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Task\","
          + "      \"isEventBased\": false," + "      \"startQuantity\": 0,"
          + "      \"completionQuantity\": 0," + "      \"isForCompensation\": false,"
          + "      \"name\": \"Task5\"," + "      \"displayName\": \"Sequence 5\""
          + "    }," + "    \"a5e55f59-303e-409a-bff5-052fbb11663a\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.TaskSymbol\","
          + "      \"x\": 12," + "      \"y\": 955.9999952316284,"
          + "      \"width\": 308," + "      \"height\": 188,"
          + "      \"object\": \"f3a95889-5c47-4564-bc34-c525191ae48b\"" + "    },"
          + "    \"1e9178d9-ff70-43c2-9fae-991257af3dba\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow4\","
          + "      \"displayName\": \"Sequence Flow 4\","
          + "      \"source\": \"db89ffbd-46d5-4760-80d2-fb340f698873\","
          + "      \"target\": \"f3a95889-5c47-4564-bc34-c525191ae48b\"" + "    },"
          + "    \"5e198272-34f7-4b8f-9f7d-1fd9c6679771\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.FlowSymbol\","
          + "      \"points\": \"380.35857672669886,815.9999976158142 380.35857672669886,885.9999964237213 166,885.9999964237213 166,955.9999952316284\","
          + "      \"sourceSymbol\": \"ffbeb1bc-d185-468a-af04-b3892f9835ae\","
          + "      \"targetSymbol\": \"a5e55f59-303e-409a-bff5-052fbb11663a\","
          + "      \"object\": \"1e9178d9-ff70-43c2-9fae-991257af3dba\"" + "    },"
          + "    \"9e092a09-4dda-4425-b418-024d62ba4eb5\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Task\","
          + "      \"isEventBased\": false," + "      \"startQuantity\": 0,"
          + "      \"completionQuantity\": 0," + "      \"isForCompensation\": false,"
          + "      \"name\": \"Task6\"," + "      \"displayName\": \"Sequence 6\""
          + "    }," + "    \"f121a18e-e47a-488c-81bb-ee121928f6f8\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.TaskSymbol\","
          + "      \"x\": 420," + "      \"y\": 955.9999952316284,"
          + "      \"width\": 308," + "      \"height\": 188,"
          + "      \"object\": \"9e092a09-4dda-4425-b418-024d62ba4eb5\"" + "    },"
          + "    \"583db2f5-f626-4aba-8df8-51c08908fd41\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow5\","
          + "      \"displayName\": \"Sequence Flow 5\","
          + "      \"source\": \"db89ffbd-46d5-4760-80d2-fb340f698873\","
          + "      \"target\": \"9e092a09-4dda-4425-b418-024d62ba4eb5\"" + "    },"
          + "    \"332a61c2-ef0b-4fbe-967a-718f3c9209b8\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.FlowSymbol\","
          + "      \"points\": \"483.0252433933656,815.9999976158142 483.0252433933656,885.9999964237213 574,885.9999964237213 574,955.9999952316284\","
          + "      \"sourceSymbol\": \"ffbeb1bc-d185-468a-af04-b3892f9835ae\","
          + "      \"targetSymbol\": \"f121a18e-e47a-488c-81bb-ee121928f6f8\","
          + "      \"object\": \"583db2f5-f626-4aba-8df8-51c08908fd41\"" + "    },"
          + "    \"ca83d590-9b66-423e-b493-badb06c277aa\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Task\","
          + "      \"isEventBased\": false," + "      \"startQuantity\": 0,"
          + "      \"completionQuantity\": 0," + "      \"isForCompensation\": false,"
          + "      \"name\": \"Task7\"," + "      \"displayName\": \"Sequence 7\""
          + "    }," + "    \"48e8e9fc-2c3d-4272-beeb-05402cff16a3\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.TaskSymbol\","
          + "      \"x\": 828.0000000000003," + "      \"y\": 955.9999952316284,"
          + "      \"width\": 308," + "      \"height\": 188,"
          + "      \"object\": \"ca83d590-9b66-423e-b493-badb06c277aa\"" + "    },"
          + "    \"cdd7ea09-4184-4f27-a6d3-720f47e06d9e\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.Flow\","
          + "      \"isImmediate\": false," + "      \"name\": \"Flow6\","
          + "      \"displayName\": \"Sequence Flow 6\","
          + "      \"source\": \"ffba8b27-8ffc-4f3c-aa45-aacf4e6385a9\","
          + "      \"target\": \"ca83d590-9b66-423e-b493-badb06c277aa\"" + "    },"
          + "    \"86b74abd-8190-4030-a5d8-a73922878579\": {"
          + "      \"classDefinition\": \"sap.modeling.cdt.transaction.ui.FlowSymbol\","
          + "      \"points\": \"982.0000000000003,815.9999976158142 982.0000000000003,955.9999952316284\","
          + "      \"sourceSymbol\": \"d6a924e9-53e4-48a8-aa9f-25a9d4faec44\","
          + "      \"targetSymbol\": \"48e8e9fc-2c3d-4272-beeb-05402cff16a3\","
          + "      \"object\": \"cdd7ea09-4184-4f27-a6d3-720f47e06d9e\"" + "    }" + "  }"
          + "}";

  private void generateXML(Tree tree, Document doc, Element element) {
    Element rootElement = doc.createElement(tree.getNode());
    rootElement.setAttribute("id", tree.getId());
    if (!doc.hasChildNodes() || element == null) {
      doc.appendChild(rootElement);
    } else {
      element.appendChild(rootElement);
    }
    if (!tree.getChildren().isEmpty()) {
      for (Tree childTree : tree.getChildren()) {
        generateXML(childTree, doc, rootElement);
      }
    }
  }

  private void writeXML(Tree tree)
      throws ParserConfigurationException, TransformerException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.newDocument();
    generateXML(tree, doc, null);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(System.out);
    transformer.transform(source, result);
  }

  private Tree getTree(Map<String, Content> flowNodes, Content modelNode) {
    Map<String, Tree> diagramTree = new HashMap<>();
    for (String diagramKey : modelNode.getDiagrams().keySet()) {
      diagramTree.put(diagramKey,
          new Tree(modelNode.getDiagrams().get(diagramKey).getName(), diagramKey));
    }
    for (String flowKey : flowNodes.keySet()) {
      Content flow = flowNodes.get(flowKey);
      String modelSourceKey = flow.getSource();
      String modelTargetKey = flow.getTarget();
      Tree sourceTree = diagramTree.get(modelSourceKey);
      Tree targetTree = diagramTree.get(modelTargetKey);
      sourceTree.getChildren().add(targetTree);
    }
    String rootNode = null;
    diagram:
    for (String diagramKey : modelNode.getDiagrams().keySet()) {
      for (String flowKey : flowNodes.keySet()) {
        Content flow = flowNodes.get(flowKey);
        if (flow.getTarget().equals(diagramKey)) {
          continue diagram;
        }
      }
      rootNode = diagramKey;
      break;
    }
    return diagramTree.get(rootNode);
  }

  private Map<String, Content> getFlowNodes(JsonInput input) {
    Map<String, Content> flowNodes = new HashMap<>();
    Map<String, Content> contents = input.getContents();
    for (String key : contents.keySet()) {
      Content object = contents.get(key);
      if (flowClass.equals(object.getClassDefinition())) {
        flowNodes.put(key, object);
      }
    }
    return flowNodes;
  }

  private void filterModel(Content modelNode) {
    Map<String, Diagram> filteredDiagrams = new HashMap<>();
    for (String diagramKey : modelNode.getDiagrams().keySet()) {
      Diagram diagram = modelNode.getDiagrams().get(diagramKey);
      if (StringUtils.isNotBlank(diagram.getName())) {
        filteredDiagrams.put(diagramKey, diagram);
      }
    }
    modelNode.setDiagrams(filteredDiagrams);
  }

  private Content getModelNode(JsonInput json) {
    Map<String, Content> contents = json.getContents();
    for (String key : contents.keySet()) {
      Content object = contents.get(key);
      if (modelClass.equals(object.getClassDefinition())) {
        return object;
      }
    }
    return null;
  }

  private void execute() {
    ObjectMapper mapper = new ObjectMapper();
    JsonInput inputObject = null;
    try {
      // Convert JSON String to JSON Input Model
      inputObject = mapper.readValue(inputString, JsonInput.class);
    } catch (Exception e) {
      System.out.println("Uncaught exception");
      e.printStackTrace();
    }
    // Get the model names based on modelClass
    Content modelNode = getModelNode(inputObject);
    // Filter the model nodes
    filterModel(modelNode);
    // Get the flow vector nodes to establish parent-child relationship
    Map<String, Content> flowNodes = getFlowNodes(inputObject);
    // Generate the tree from model and flow nodes.
    Tree tree = getTree(flowNodes, modelNode);
    try {
      // generate the XML from the tree
      writeXML(tree);
    } catch (ParserConfigurationException | TransformerException e) {
      System.out.println("Unable to generate XML" + e);
      e.printStackTrace();
    }
  }

  @Override public void run() {
    this.execute();
  }

  public static void main(String[] args) {
    Thread t1 = new Thread(new XMLParser());
    t1.start();
  }
}
