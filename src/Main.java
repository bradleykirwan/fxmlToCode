import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradley on 25/08/15.
 */
public class Main {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("test.fxml"));

            FXNode parentNode = parse(doc.getDocumentElement());
            System.out.println(CodeWriter.writeCode(parentNode).getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FXNode parse(Node node) {
        FXNode node1 = new FXNode(node.getLocalName());

        // Parse attributes
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node attributeNode = node.getAttributes().item(i);
            node1.addAttribute(attributeNode.getLocalName(), attributeNode.getNodeValue());
        }

        // Parse children
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node childNode = node.getChildNodes().item(i);
            String name = childNode.getLocalName();
            if (childNode.getLocalName() != null) {
                if (name.equals("children")) {
                    // Set children if appropriate
                    node1.getChildren().addAll(getChildFXNodes(childNode));
                } else if (name.equals("padding")) {
                    // Set padding if appropriate
                    node1.getPadding().addAll(getChildFXNodes(childNode));
                }
            }
        }

        return node1;
    }

    public static List<FXNode> getChildFXNodes(Node node) {
        List<FXNode> childNodes = new ArrayList<FXNode>();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node childNode = node.getChildNodes().item(i);
            if (childNode.getLocalName() != null) {
                childNodes.add(parse(node.getChildNodes().item(i)));
            }
        }
        return childNodes;
    }
}
