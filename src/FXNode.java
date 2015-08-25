import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bradley on 25/08/15.
 */
public class FXNode {
    private String type;
    private Map<String, String> attributes = new HashMap<String, String>();
    private List<FXNode> children = new ArrayList<FXNode>();
    private List<FXNode> padding = new ArrayList<FXNode>();

    public FXNode(String type) {
        this.type = type;
    }

    public void addChild(FXNode node) {
        children.add(node);
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public List<FXNode> getChildren() {
        return children;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public List<FXNode> getPadding() {
        return padding;
    }

    @Override
    public String toString() {
        return "FXNode{" +
                "type='" + type + '\'' +
                ", attributes=" + attributes +
                ", children=" + children +
                ", padding=" + padding +
                '}';
    }
}